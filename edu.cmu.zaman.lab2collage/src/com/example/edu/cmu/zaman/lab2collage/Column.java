package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.util.Log;


public class Column extends ArtistBase {
	public Column(float x, float y, float w, float h){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
	}
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("Column", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
//		draw children
		drawChildren(onCanvas);
	}
	
	@Override
	public void doLayout() {
		// TODO Auto-generated method stub
		
		//recursive traversal down the tree to do setX and setY?
		
		//position children based on their declared size
		
		//call doLayout() on all children
		float ycord = 0;
		for(int i = 0; i < getNumChildren(); i++){
			float relX = getX() + getW()/2 - getChildAt(i).getW()/2;
			getChildAt(i).setX(relX);
			getChildAt(i).setY(getY()+ycord);
			ycord += getChildAt(i).getH();
			getChildAt(i).doLayout();
		}
		

	}
}
