package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.RectF;

public class NinePartImage extends ArtistBase {

	private NinePatch myNinePatch;
	
	public NinePartImage(float x, float y, float w, float h, NinePatch patches){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
		myNinePatch = patches;
	}
	
	@Override
	public void draw(Canvas onCanvas) {
//		if(getParent() != null){
//			//all children need to clip themselves to their parent
//			onCanvas.clipRect(getParent().getX(), getParent().getY(), getParent().getX()+getParent().getW(), getParent().getY() + getParent().getH());
//			}
		
		RectF myRectF = new RectF(getX(), getY(), getX() + getW(), getY() + getH());
		myNinePatch.draw(onCanvas, myRectF);
		drawChildren(onCanvas);
	}
	
}
