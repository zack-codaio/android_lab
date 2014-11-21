package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.util.Log;

public class Row extends ArtistBase {

	public Row(float x, float y, float w, float h){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
	}
	
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("Row", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
//		draw children
		drawChildren(onCanvas);
	}
	
	@Override
	public void doLayout() {
		// TODO Auto-generated method stub
		
		//recursive traversal down the tree to do setX and setY?
		
		//position children based on their declared size
		
		//call doLayout() on all children
		float xcord = 0;
		for(int i = 0; i < getNumChildren(); i++){
			getChildAt(i).setX(getX()+xcord);
			xcord += getChildAt(i).getW();
			
			float relY = getY() + getH()/2 - getChildAt(i).getH()/2;
			getChildAt(i).setY(relY);
			getChildAt(i).doLayout();
		}
		

	}
}
