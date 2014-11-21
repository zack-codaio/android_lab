package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.util.Log;

public class Pile extends ArtistBase {
	
	public Pile(float x, float y, float w, float h){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
	}

	
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("Pile", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
//		draw children
//		for(int i = 0; i < getNumChildren(); i++){
//			getChildAt(i).draw(onCanvas);
//		}	
		drawChildren(onCanvas);
	}
	
	@Override
	public void doLayout() {
		// TODO Auto-generated method stub
		
		//recursive traversal down the tree to do setX and setY?
		
		//position children based on their declared size
		
		//position at x,y of the Pile
		//call doLayout() on all children
		for(int i = 0; i < getNumChildren(); i++){
			getChildAt(i).setX(getX());
			getChildAt(i).setY(getY());
			getChildAt(i).doLayout();
		}
		

	}
}
