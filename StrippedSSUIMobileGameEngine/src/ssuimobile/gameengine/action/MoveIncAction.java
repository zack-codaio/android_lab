/**
 * 
 */
package ssuimobile.gameengine.action;


/**
 * @author hudson
 *
 */
public class MoveIncAction extends XYAction {
	/**
	 * @param x
	 * @param y
	 */
	public MoveIncAction(float x, float y) {
		super(FSMActionType.MOVE_INC, x, y);
	}
}
