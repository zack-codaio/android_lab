package com.example.edu.cmu.zaman.lab2collage;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

public class ArtistBase extends ArtistPrebase {

	// variables
	private float myX;
	private float myY;
	private float myW;
	private float myH;
	private Artist myParent;
	private int myColor;
	private ArrayList<Artist> myChildren;

	public ArtistBase() {
		myChildren = new ArrayList<Artist>();
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		myX = x;
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		myY = y;
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return myX;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return myY;
	}

	@Override
	public void setW(float w) {
		// TODO Auto-generated method stub
		myW = w;
	}

	@Override
	public void setH(float h) {
		// TODO Auto-generated method stub
		myH = h;
	}

	@Override
	public float getW() {
		// TODO Auto-generated method stub
		return myW;
	}

	@Override
	public float getH() {
		// TODO Auto-generated method stub
		return myH;
	}

	@Override
	public Artist getParent() {
		// TODO Auto-generated method stub
		return myParent;
	}

	@Override
	public void setParent(Artist newParent) {
		// TODO Auto-generated method stub
		myParent = newParent;
	}

	@Override
	public int getNumChildren() {
		// TODO Auto-generated method stub
		return myChildren.size();
	}

	@Override
	public Artist getChildAt(int index) {
		// TODO Auto-generated method stub
		if(index > 0 && index < getNumChildren()){
		return myChildren.get(index);
		}
		else{
			return null;
		}
	}

	@Override
	public int findChild(Artist child) {
		// TODO Auto-generated method stub
		return myChildren.indexOf(child);
	}

	@Override
	public void addChild(Artist child) {
		// TODO Auto-generated method stub
		if(child != null){
		try {
			if (child.getParent() != null) {
				child.getParent().removeChild(child);
			}
		} catch (Error e) {
			Log.e("addChild", "caught error " + e
					+ " when trying to add child " + child);
		}

		myChildren.add(child);
		child.setParent(this);
		}
	}

	@Override
	public void removeChildAt(int index) {
		// TODO Auto-generated method stub
		myChildren.get(index).setParent(null);
		myChildren.remove(index);

	}

	@Override
	public void removeChild(Artist child) {
		// TODO Auto-generated method stub
		myChildren.remove(child);
		child.setParent(null);
	}

	@Override
	public void moveChildFirst(Artist child) {
		// TODO Auto-generated method stub

		// should this check to see if it is already here?
		// if not, should it just insert at the beginning?

		// remove if already exists
		myChildren.remove(child);

		// insert at beginning of list
		myChildren.add(0, child);
		child.setParent(this);

	}

	@Override
	public void moveChildLast(Artist child) {
		// TODO Auto-generated method stub

		// should this check to see if it is already here?
		// if not, should it just append?

		// remove if already exists
		myChildren.remove(child);

		// insert at beginning of list
		myChildren.add(child);
		child.setParent(this);

	}

	@Override
	public void moveChildEarlier(Artist child) {
		// TODO Auto-generated method stub
		// remove child, insert at index - 1 (if > 0)
		int index = findChild(child);
		if (index != -1) {
			index--;
			if (index <= 0) {
				index = 0;
			}
			removeChild(child);
			myChildren.add(index, child);
			child.setParent(this);
		}

	}

	@Override
	public void moveChildLater(Artist child) {
		// TODO Auto-generated method stub

		// remove child, insert at index + 1 (if < .size())
		int index = findChild(child);
		if (index != -1) {
			index++;
			if (index >= myChildren.size()) {
				index = myChildren.size();
			}
			removeChild(child);
			myChildren.add(index, child);
			child.setParent(this);
		}

	}

	@Override
	public void doLayout() {
		// TODO Auto-generated method stub

		// recursive traversal down the tree to do setX and setY?

		// position children based on their declared size

		// default to "Manual" layout which doesn't modify their position

		// call doLayout() on all children
		for (int i = 0; i < getNumChildren(); i++) {
			getChildAt(i).doLayout();
		}

	}

	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub

		// clipping occurs in draw
		// draw needs to recursively draw children as well

		Log.d("ArtistBase", getX() + " " + getY() + " " + getX() + getW() + " "
				+ getY() + getH());

		drawChildren(onCanvas);

	}
	public void drawChildren(Canvas onCanvas){
		// draw children
		
				onCanvas.save();
				onCanvas.clipRect(getX(), getY(), getX()+getW(), getY()+getH());
				for (int i = 0; i < getNumChildren(); i++) {
					getChildAt(i).draw(onCanvas);
				}
				
				onCanvas.restore();
//				onCanvas.clipRect(0,0,onCanvas.getWidth(), onCanvas.getHeight());
	}
	
//	public void clipToParent(Canvas onCanvas){
//		Paint p = new Paint();
//		p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
//		
//	}

}
