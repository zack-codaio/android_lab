package ssuimobile.gameengine;

import ssuimobile.gameengine.event.FSMEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * This abstract class represents a character in the game engine. It has 
 * methods to handle the finite state machine for this character. Students
 * should implement the abstract methods from this class.
 * @author sauvik
 *
 */
public abstract class GameCharacterPreBase implements GameCharacter {
	
	protected GameEnginePreBase _owner = null;
	public GameEnginePreBase getOwner() {return _owner;}
	
	protected int _characterIndex = -1;
	 
	public int getCharacterIndex() {return _characterIndex;}
	
	protected float _x = 0;
	
	public float getX() {return _x;}
	
	public void setX(float xv) {_x = xv; /*xx add damage here */}
	
	protected float _y = 0;
	
	public float getY() {return _y;}
	
	public void setY(float yv) {_y = yv;}
	
	protected float _w = 13;
	
	public float getW() {return _w;}
	
	public void setW(float wv) {_w = wv;}
	
	protected float _h = 17;
	
	public float getH() {return _h;}
	
	public void setH(float hv) {_h = hv;}
	
	protected Bitmap _image = null;
	

	/**
	 * 
	 * @return the image that should be shown. If null, no image should be displayed
	 */
	public Bitmap getImage() {return _image;}
	
	/**
	 * @param newImage the image that is set, which could be null if it is not supposed to be visible
	 */
	
	public void setImage(Bitmap newImage) {_image = newImage;}
	
	public static final int START_STATE = 0;
	protected int _currentState = START_STATE;
	
	public int getCurrentState() {return _currentState;}
	
	public void resetState() {_currentState = START_STATE;}
	
	protected FSMState[] _FSMStateTable = null;
	
	/**
	 * This method is called when there is an event that this character might
	 * be able to consume. The method should check to see if there are any
	 * transitions from the character's current state that match this event,
	 * If there are, that transition should be taken and the method should 
	 * return true.
	 * @param event the event that can be consumed
	 * return whether or not this character consumed this event
	 */
	
	public abstract boolean deliverEvent(FSMEvent event);  
	  // returns true if event was "consumed" (acted upon)
	
	/**
	 * This method causes the character to take the specified transition, which
	 * was triggered by the included event. The method should make sure to 
	 * complete any actions that are associated with the transition and ensure 
	 * that the character moves to the target state
	 * @param transition the transition that is to be taken
	 * @param evt the event that matched this transition
	 */
	protected abstract void makeFSMTransition(
			FSMTransition transition, FSMEvent evt);
	
	/**
	 * Draws the character on the canvas
	 * @param canv the canvas on which the character should be drawn
	 */
	
	public abstract void draw(Canvas canv);
	
	public GameCharacterPreBase(
			GameEnginePreBase owner,
			int index,
			int x, int y, 
			int w, int h, 
			FSMState[] states,
			Bitmap img) 
	{
		_owner = owner;
		_characterIndex = index;
		_x = x; _y = y; _w = w; _h = h;
		_FSMStateTable = states;
		_currentState = START_STATE;
		_image = img;
	}
	
	public GameCharacterPreBase(
		GameEnginePreBase owner,
		int index,
		int x, int y, 
		int w, int h, 
		FSMState[] states) 
	{
		this(owner,index,x,y,w,h,states,null);
	}

	public GameCharacterPreBase() {
		this(null,-1,0,0,13,17,null);
	}
}
