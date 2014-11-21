package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class SimpleFrame extends ArtistBase {
private Paint myPaint;
	
	//constructor
	public SimpleFrame(float x, float y, float w, float h){
		myPaint = new Paint();
		myPaint.setColor(Color.BLACK);
		myPaint.setStrokeWidth(2);
		myPaint.setStyle(Paint.Style.STROKE);
		setX(x);
		setY(y);
		setW(w);
		setH(h);
//		
	}
	
	//draw
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("simple frame", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
		
//		if(getParent() != null){
//		//all children need to clip themselves to their parent
////		onCanvas.clipRect(getParent().getX(), getParent().getY(), getParent().getX()+getParent().getW(), getParent().getY() + getParent().getH());
//		}
		onCanvas.drawRect(getX(), getY(), getX()+getW(), getY()+getH(), myPaint);
		
//		onCanvas.clipRect(getX(), getY(), getX()+getW(), getY()+getH());
//		for (int i = 0; i < getNumChildren(); i++) {
//			getChildAt(i).draw(onCanvas);
//		}
//		onCanvas.clipRect(0,0,onCanvas.getWidth(), onCanvas.getHeight());
		
		drawChildren(onCanvas);
	}
}
