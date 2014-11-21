package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.util.Log;

public class Circle extends ArtistBase {
	private float layoutX;
	private float layoutY;
	private float layoutR;
	
	
	public Circle(float x, float y, float w, float h, float layoutCenterX, float layoutCenterY, float layoutRadius){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
		layoutX = layoutCenterX;
		layoutY = layoutCenterY;
		layoutR = layoutRadius;
	}
	
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("Circle layout", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
//		draw children
		drawChildren(onCanvas);
	}
	
	@Override
	public void doLayout() {
		// TODO Auto-generated method stub
		
		//recursive traversal down the tree to do setX and setY?
		
		//position children based on their declared size
		
		//call doLayout() on all children
		float angle = 0;
		for(int i = 0; i < getNumChildren(); i++){
			Artist curChild = getChildAt(i);

			//set x
			curChild.setX(getX()+layoutX+(float)(Math.cos(Math.toRadians(angle))*layoutR));
			//correct for centering
			curChild.setX(curChild.getX()-curChild.getW()/2);
			
			
			//set y
			curChild.setY(getY()+layoutY+(float)(Math.sin(Math.toRadians(angle))*layoutR));
			curChild.setY(curChild.getY()-curChild.getH()/2);
			
			angle += 360 / getNumChildren();
		}
		

	}
	
	
	
}
