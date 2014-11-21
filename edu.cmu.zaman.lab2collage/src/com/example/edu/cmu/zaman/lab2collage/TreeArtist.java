package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.util.Log;

public class TreeArtist extends ArtistBase {
	private int color;
	private int size;
	
	
	public TreeArtist(float x, float y, float w, float h, int c, int s){
		setX(x);
		setY(y);
		setW(w);
		setH(h);
		size = s;
		color = c;
	}
	
	@Override
	public void draw(Canvas onCanvas) {
		// TODO Auto-generated method stub
		Log.d("TreeArtist", getX() + " "+getY()+" "+getX()+getW()+" "+getY()+getH());

		int branchstate = size;
		
		while(branchstate > 0){
			
			
			branchstate--;
		}
		
//		draw children
		drawChildren(onCanvas);
	}
}
