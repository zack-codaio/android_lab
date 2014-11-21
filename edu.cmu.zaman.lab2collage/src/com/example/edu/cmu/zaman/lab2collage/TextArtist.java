package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

public class TextArtist extends ArtistBase {
	
	private String myText;
	private Paint myPaint;
	private Rect myBounds;
	
	public TextArtist(float x, float y, String text, Typeface face, float textSize){
		setX(x);
		setY(y);
		myText = text;
		myPaint = new Paint();
		myPaint.setTypeface(face);
		myPaint.setTextSize(textSize);
		myPaint.setColor(Color.BLACK);
		Log.d("TextArtist", myText);
		Log.d("TextArtist", Float.toString(getX()));
		Log.d("TextArtist", Float.toString(getY()));
		Log.d("TextArtist", myPaint.toString());
		myBounds = new Rect();
		myPaint.getTextBounds(myText, 0, myText.length(), myBounds);
		Log.d("TextArtist", Integer.toString(myBounds.width()));
		Log.d("TextArtist", Integer.toString(myBounds.height()));
		setW(myBounds.width());
		setH(myBounds.height());	
	}
	
	@Override
	public void draw(Canvas onCanvas) {
//		if(getParent() != null){
//			//all children need to clip themselves to their parent
//			onCanvas.clipRect(getParent().getX(), getParent().getY(), getParent().getX()+getParent().getW(), getParent().getY() + getParent().getH());
//		}
		onCanvas.drawText(myText, getX(), getY()+getH(), myPaint);
		drawChildren(onCanvas);
	}
	
	@Override
	public boolean sizeIsIntrinsic() {
		return true;
	}
	
}
