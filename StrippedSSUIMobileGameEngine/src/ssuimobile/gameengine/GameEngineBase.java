package ssuimobile.gameengine;

import java.util.List;

import ssuimobile.gameengine.event.FSMEvent;
import ssuimobile.gameengine.event.XYEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

public class GameEngineBase extends GameEnginePreBase {

	public GameEngineBase(int xmlFileID, Context ctx){
		super(xmlFileID, ctx);
		sendInitMessages();
	}

	@Override
	protected List<GameCharacter> charactersUnder(RectF area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean dispatchPositionally(XYEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean dispatchPositionally(RectF inArea, FSMEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean dispatchDirect(int toChar, FSMEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean dispatchToAll(FSMEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean dispatchTryAll(FSMEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean dispatchDragFocus(FSMEvent evt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(evt);
	}

	@Override
	protected void onDraw(Canvas canv) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void buttonHit(int buttonNum) {
		// TODO Auto-generated method stub

	}

}
