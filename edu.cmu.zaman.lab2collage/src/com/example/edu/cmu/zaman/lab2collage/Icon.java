package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Icon extends ArtistBase {
	private Rect myRect;
//	private Paint myPaint;
	private Bitmap myBmp;
	
	//constructor
		public Icon(float x, float y, Bitmap image){
			setX(x);
			setY(y);
			setW(image.getWidth());
			setH(image.getHeight());
//			myPaint = new Paint();
			myBmp = image;
		}
		
		//draw
		@Override
		public void draw(Canvas onCanvas) {
			// TODO Auto-generated method stub
			Log.d("bitmap", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());
//			if(getParent() != null){
//			//all children need to clip themselves to their parent
//			onCanvas.clipRect(getParent().getX(), getParent().getY(), getParent().getX()+getParent().getW(), getParent().getY() + getParent().getH());
//			}
			
			onCanvas.drawBitmap(myBmp, getX(), getY(), null);
			
			
			drawChildren(onCanvas);
		}
		
		@Override
		public boolean sizeIsIntrinsic() {
			return true;
		}
	
}
