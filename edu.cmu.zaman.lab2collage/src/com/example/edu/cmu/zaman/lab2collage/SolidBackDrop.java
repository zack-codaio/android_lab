package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class SolidBackDrop extends ArtistBase {

	private Paint myPaint;
	
	//constructor
	public SolidBackDrop(float x, float y, float w, float h, int color){
		myPaint = new Paint();
		myPaint.setColor(color);
		setX(x);
		setY(y);
		setW(w);
		setH(h);
		
	}
	
	//draw
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		
		//draw self
		float right = getX() + getW();
		float bottom = getY() + getH();
		Log.d("solid back drop", getX() + " "+getY()+" "+right+" "+bottom);
		//all children need to clip themselves to their parent
		
		
		onCanvas.drawRect(getX(), getY(), getX()+getW(), getY()+getH(), myPaint);
		
		Log.d("solid back drop num children", Integer.toString(getNumChildren()));
		
		
		
		drawChildren(onCanvas);
		
		//clip to my bounding box
	}
	
}
