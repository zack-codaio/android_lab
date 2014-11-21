package ssuimobile.gameengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ssuimobile.gameengine.event.ButtonPressedEvent;
import ssuimobile.gameengine.event.DragEndEvent;
import ssuimobile.gameengine.event.DragMoveEvent;
import ssuimobile.gameengine.event.FSMEvent;
import ssuimobile.gameengine.event.TouchMoveEvent;
import ssuimobile.gameengine.event.TouchPressEvent;
import ssuimobile.gameengine.event.TouchReleaseEvent;
import ssuimobile.gameengine.event.XYEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

public class GameEngineBase extends GameEnginePreBase {


	public GameEngineBase(int xmlFileID, Context ctx) {
		super(xmlFileID, ctx);
		sendInitMessages();
		
		
	}

	@Override
	protected List<GameCharacter> charactersUnder(RectF area) {
		List<GameCharacter> charsunder = new ArrayList<GameCharacter>();

		// look at all characters
		for (int i = 0; i < _characters.length; i++) {
			// check bounds
			GameCharacter curChar = _characters[i];
			// have _x, _y, _w, _h for character
			// trivial reject using (_x, _y) and (_x+_w, _y+_h)
			RectF charRect = new RectF(curChar.getX(), curChar.getY(),
					curChar.getX() + curChar.getW(), curChar.getY()
							+ curChar.getH());

			// if intersecting, add to list
			if (RectF.intersects(area, charRect)) {
				charsunder.add(curChar);
//				Log.d("charactersUnder",
//						"character " + curChar.getCharacterIndex()
//								+ " is under rect");
				// this will return in draw order, so if you want to dispatch to
				// the last drawn, then dispatch to the end of the list

			}
		}
		Collections.reverse(charsunder); // reverse to be in reverse drawing
											// order
		return charsunder;
	}

	@Override
	protected boolean dispatchPositionally(XYEvent evt) {
		boolean consumed = false;
		// get characters under the XY position
		// create new RectF to pass to charactersUnder()
		RectF targetarea = new RectF(evt.getX(), evt.getY(), evt.getX(),
				evt.getY()); // rect with no width or height, just a point -- I
								// hope this works
		List<GameCharacter> chars = charactersUnder(targetarea);

		// for each, offer
		for (int i = 0; i < chars.size() && consumed == false; i++) {
			// deliver event
			boolean curconsume = chars.get(i).deliverEvent(evt);
			// if character returns true, set consumed to true
			if (curconsume) {
//				Log.d("dispatchPositionally", "character " + i
//						+ " consumed event " + evt);
				consumed = true;
			}
		}
		return consumed; // return whether or not the event was consumed at all
	}

	@Override
	protected boolean dispatchPositionally(RectF inArea, FSMEvent evt) {
		// i guess this could be used for collision detection?
		// when would you use this function over the other one?

		boolean consumed = false;
		// get characters under the XY position
		// create new RectF to pass to charactersUnder()
		RectF targetarea = inArea;
		List<GameCharacter> chars = charactersUnder(targetarea);

		// for each, offer
		for (int i = 0; i < chars.size() && consumed == false; i++) {
			// deliver event
			boolean curconsume = chars.get(i).deliverEvent(evt);
			// if character returns true, set consumed to true
			if (curconsume) {
				Log.d("dispatchPositionally (given RectF)", "character " + i
						+ " consumed event " + evt);
				consumed = true;
			}
		}
		return consumed; // return whether or not the event was consumed at all
	}

	@Override
	protected boolean dispatchDirect(int toChar, FSMEvent evt) {
//		Log.d("dispatchDirect",
//				"event " + evt + " dispatched directly to character "
//						+ Integer.toString(toChar));
		if(toChar >= 0){
			_characters[toChar].deliverEvent(evt);
			return true;
		}
		else{
			Log.e("dispatchDirect", "Tried to dispatch to character "+toChar);
		}
		return false;
		
	}

	@Override
	protected boolean dispatchToAll(FSMEvent evt) {
		// dispatch to all (regardless of consumption) to all characters in
		// reverse drawing order
		boolean consumed = false;
		for (int i = _characters.length - 1; i >= 0; i--) {
			boolean temp = _characters[i].deliverEvent(evt);
			if (temp == true) {
				consumed = true;
			}

			// Log.d("delivered event of (type "+Integer.toString(evt.getType())
			// +") to "+ Integer.toString(i), "consumed = "+ consumed);

		}

		return consumed;
	}

	@Override
	protected boolean dispatchTryAll(FSMEvent evt) {
		// dispatch to all (stopping on consumption) to all characters in
		// reverse drawing order
		boolean consumed = false;
		for (int i = _characters.length - 1; i >= 0; i--) {
			consumed = _characters[i].deliverEvent(evt);

			// Log.d("delivered event of (type "+Integer.toString(evt.getType())
			// +") to "+ Integer.toString(i), "consumed = "+ consumed);

			if (consumed == true) {
				break;
			}
		}
		return consumed;
	}

	@Override
	protected boolean dispatchDragFocus(FSMEvent evt) {
		// dispatch event to current drag focus

		if (_dragFocus >= 0) { // not -1
			// adjust position of XYEvent
			if (evt instanceof XYEvent) {
				float charX = _characters[_dragFocus].getX();
				float charY = _characters[_dragFocus].getY();
				// adjust

				// TODO adjustment of grabpoint
				// grabpoint is offset of evts x,y from the characters origin
				// do I need to set a grabpoint on set drag focus?
				// jk this is handled within the character itself

				dispatchDirect(_dragFocus, evt);
			} else {
				dispatchDirect(_dragFocus, evt);
			}
			return true;
		}
		else{
			return false;
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		// TODO add checking so touch events aren't triggered outside of play area

		// https://developer.android.com/training/gestures/movement.html
//		 Log.d("motion event", "x: "+evt.getX()+ " y: "+evt.getY());

		Float curX = evt.getX();
		Float curY = evt.getY();
		
		float vHeight = this.getLayoutParams().height;
		if(curY > 545){ //hardcoded bounds checking -- I know this is bad, but it's what I've got
			return false;
		}
		
		int index = evt.getActionIndex();
		int action = evt.getActionMasked();
		int pointerID = evt.getPointerId(index);

		// needs to handle - TouchMoveEvent, TouchPressEvent, TouchReleaseEvent,
		// DragMoveEvent, DragEndEvent

		

		boolean consumed = false;
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// Log.d("touch event", "action down");

			// dispatch drag focus
			// note which character currently has drag focus
			// make sure that drag focus is updated

			
			
			// TouchPressEvent
			TouchPressEvent tpe = new TouchPressEvent(curX, curY);
			// dispatch events
			consumed = dispatchPositionally(tpe);
			consumed = true;
			//returning true instead of consumed to account for a touch_press outside of char0 but a release within it
			//Matthew and Vivek were of the opinion that a release within char0 should trigger its event even if the initial touch wasn't within its bounds
			//so even if the first event ACTION_DOWN is not consumed I always want the stream of events to continue after the first touch press
			

			break;
		case MotionEvent.ACTION_MOVE:
//			 Log.d("touch event", "action move");
			
			// DragMoveEvent
			if (_dragFocus >= 0) {
				
				DragMoveEvent dme = new DragMoveEvent(curX - _grabPointX, curY - _grabPointY);
//				Log.d("drag focus", Boolean.toString(dispatchDragFocus(dme)));
				consumed = dispatchDragFocus(dme);
			} else {
				// TouchMoveEvent
				TouchMoveEvent tme = new TouchMoveEvent(curX, curY);
				// dispatch events
				consumed = dispatchPositionally(tme);
			}

			break;
		case MotionEvent.ACTION_UP:
			// Log.d("touch event", "action up");

			// remove drag focus if there is a current drag focus
			if (_dragFocus >= 0) {
				// DragEndEvent
				DragEndEvent dee = new DragEndEvent(curX - _grabPointX, curY - _grabPointY);
				consumed = dispatchDragFocus(dee);
			} else {
				// TouchReleaseEvent
				TouchReleaseEvent tre = new TouchReleaseEvent(curX, curY);
				consumed = dispatchPositionally(tre);
			}
			
			break;
		case MotionEvent.ACTION_CANCEL:
			// Log.d("touch event", "action cancel");
			// do nothing?

			// release drag focus at least
			releaseDragFocus();
			break;
		}

		return consumed;
//		return true;
		
	}

	@Override
	protected void onDraw(Canvas canv) {
		// clear canvas
		canv.drawColor(Color.WHITE);

		// draw characters
		for (int i = 0; i < numCharacters(); i++) {
			// draw each character at its position
			_characters[i].draw(canv);
		}

	}

	@Override
	protected void buttonHit(int buttonNum) {
		Log.d("button press", Integer.toString(buttonNum));
		ButtonPressedEvent buttonpress = new ButtonPressedEvent(buttonNum);
		dispatchToAll(buttonpress);

	}

}
