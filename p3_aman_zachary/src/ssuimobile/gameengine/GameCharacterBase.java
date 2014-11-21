package ssuimobile.gameengine;

import java.util.Arrays;

import ssuimobile.gameengine.action.ChangeImageAction;
import ssuimobile.gameengine.action.DebugAction;
import ssuimobile.gameengine.action.FSMAction;
import ssuimobile.gameengine.action.RunAnimAction;
import ssuimobile.gameengine.action.SendMessageAction;
import ssuimobile.gameengine.action.StringAction;
import ssuimobile.gameengine.action.XYAction;
import ssuimobile.gameengine.event.DragMoveEvent;
import ssuimobile.gameengine.event.FSMEvent;
import ssuimobile.gameengine.event.MessageEvent;
import ssuimobile.gameengine.event.TouchPressEvent;
import ssuimobile.gameengine.event.XYEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class GameCharacterBase extends GameCharacterPreBase {
	private Bitmap _image;
	private boolean debug = false;

	@Override
	public boolean deliverEvent(FSMEvent event) {
		//deliver event to state
		//check for match
		
		//how do I know which state I am currently in?
		
		
		FSMState curstate = _FSMStateTable[_currentState];
		
		//get FSMState for current state
		if(debug == true){
		Log.d("current state", Integer.toString(_currentState));
		Log.d("current state FSM", Integer.toString(_FSMStateTable[_currentState]._transitions.length));
		Log.d("current state name", curstate._name);
		}
		
		//get transitions for the current FSMState
		FSMTransition curtransition;
		Boolean consumed = false;
		for(int i = 0; i < curstate._transitions.length; i++){
			curtransition = curstate.getTransitionAt(i);
//			Log.d("examining transition: ", curtransition._matcher);
			//check for match on each of the transitions for the current FSMState
			if(curtransition.match(event)){
				consumed = true;
				makeFSMTransition(curtransition, event);
			}
		}
		
		//return true if consumed
		//false if not consumed
		return consumed;
	}

	@Override
	protected void makeFSMTransition(FSMTransition transition, FSMEvent evt) {
		//This method should be called by your code whenever a Transition is being followed.
		
		//This method should make sure that any Actions on the transition are carried out
		//(may require redraws)
		boolean dirty = false;
		
		FSMAction[] actions = transition.getAction();
		for(int i = 0; i < actions.length; i++){
			//execute each action
			FSMAction action = actions[i];
			switch(action.getType()){
				//public static final int NO_ACTION             = 0;
				case 0:
					//do nothing.
//					Log.d("char "+Integer.toString(getCharacterIndex()), "no action");
					break;
					
				//public static final int CHANGE_IMAGE          = 1;
				case 1:
					_image = ((ChangeImageAction)(action)).getImage();
//					Log.d("char "+Integer.toString(getCharacterIndex()), "changing image to" + _image);
					dirty = true;
					break;
					
				//public static final int MOVE_TO               = 2;
				case 2:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "move_to");
					_x = ((XYAction)(action)).getX();
					_y = ((XYAction)(action)).getY();
					dirty = true;
					break;
					
				//public static final int MOVE_INC              = 3;
				case 3:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "move_inc");
					_x += ((XYAction)(action)).getX();
					_y += ((XYAction)(action)).getY();
					dirty = true;
					break;
					
				//public static final int FOLLOW_EVENT_POSITION = 4;
				case 4:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "follow_event_position");
					_x = ((XYEvent)(evt)).getX();
					_y = ((XYEvent)(evt)).getY();
					dirty = true;
					break;
					
				//public static final int RUN_ANIM              = 5;
				case 5:
					//TODO
//					Log.d("char "+Integer.toString(getCharacterIndex()), "run animation");
					getOwner().startNewAnimation((RunAnimAction)(action));
					break;
					
				//public static final int GET_DRAG_FOCUS        = 6;
				case 6:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "get drag focus");
					getOwner().requestDragFocus(_characterIndex, ((XYEvent)(evt)).getX() - _x, ((XYEvent)(evt)).getY() - _y);
//					Log.d("drag focus should be ",Integer.toString(getCharacterIndex())); 
					break;
					
				//public static final int DROP_DRAG_FOCUS       = 7;
				case 7:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "drop drag focus");
					getOwner().releaseDragFocus();
					break;
					
				//public static final int SEND_MESSAGE          = 8;
				case 8:
					//get values from action
					String message = ((SendMessageAction)(action)).getMessage();
					int target = ((SendMessageAction)(action)).getTargetCharacter();
					//create new Message Event
					MessageEvent newmessage = new MessageEvent(message);
					//ask owner to dispatch message event
					_owner.dispatchDirect(target, newmessage);
//					Log.d("char "+Integer.toString(getCharacterIndex()), "send message: "+message+" to character: " +target);
					break;
					
				//public static final int DEBUG_MESSAGE         = 9;
				case 9:
//					Log.d("char "+Integer.toString(getCharacterIndex()), "debug message");
					Log.d("ssui", ((StringAction)(action)).getMessage());
					break;
			}
			
		}
		
		//and should maintain what state has been transitioned to
		_currentState = transition.getTargetState();
//		Log.d("makeFSMTransition", "transitioned to new state "+Integer.toString(_currentState));
		
		if(dirty == true){
			//redraw -> tell owner to redraw
			getOwner().postInvalidate();
		}

	}

	@Override
	public void draw(Canvas canv) {
		canv.drawBitmap(_image, _x,_y, null);
//		Log.d("char fsm",Arrays.deepToString(_FSMStateTable));
		

		

	}

	public GameCharacterBase(
			GameEnginePreBase owner,
			int index,
			int x, int y, 
			int w, int h, 
			FSMState[] states, 
			Bitmap img) 
	{
		super(owner, index, x, y, w, h, states, img);
		_image = img;
		}
}
