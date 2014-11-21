package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

public class OvalClip extends ArtistBase {
	
	public OvalClip(float x, float y, float w, float h){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
	}

	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("OvalClip", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
		Path path = new Path();
		RectF myRect = new RectF(getX(), getY(), getX()+getW(), getY()+getH());
		path.addOval(myRect, Path.Direction.CW);
		
//		draw children
		onCanvas.save();
		
		onCanvas.clipPath(path);
		drawChildren(onCanvas);
		
		onCanvas.restore();
	}
	
	@Override
	public void doLayout() {
		// TODO Auto-generated method stub
		
		//recursive traversal down the tree to do setX and setY?
		
		//position children based on their declared size
		
		//call doLayout() on all children
		
		for(int i = 0; i < getNumChildren(); i++){
			
			getChildAt(i).setX(getX());
			getChildAt(i).setY(getY());
			getChildAt(i).doLayout();
		}
		

	}
	
}
