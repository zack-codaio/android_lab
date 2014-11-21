package com.example.edu.cmu.zaman.lab2collage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// let the base class take care of bookkeeping stuff...
        super.onCreate(savedInstanceState);
        
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        
        
        
        

//        // create an ArtistView widget and install a generated test tree there
//        ArtistView root = new ArtistView(this);
//        root.setChildArtist(buildTest1());
//        
//        // put that widget in a frame so we have a layout object above it to 
//        // listen to the fact that it doesn't want to be expanded to fill the
//        // screen (the frame gets expanded, but then the root object stays 
//        // it's original size within the frame).  
//        FrameLayout frame = new FrameLayout(this);
//        frame.addView(root);
//        setContentView(frame);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected Artist buildTest0(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 300, 700, Color.BLACK);
//		SimpleFrame rootArtist = new SimpleFrame(0, 0, 400, 800);
		rootArtist.addChild(new Icon(0, 0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
//		Icon rootArtist = new Icon(0,0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
	
//		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
//		NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
//		NinePartImage rootArtist = new NinePartImage(0, 0, 200, 20, patches);
		
//		TextArtist rootArtist = new TextArtist(0, 0, "SSUI RoCkS!!!!", Typeface.DEFAULT_BOLD, 50f); // y u no werk?!?
		
		rootArtist.addChild(new SolidBackDrop(0, 0, 9, 9, Color.GRAY));
		
//		putAll(rootArtist);
//		Pile p = new Pile(5, 200, 100, 100);
//		p.addChild(new SolidBackDrop(0, 0, 900, 900, Color.GRAY));
//		p.addChild(new SimpleFrame(0, 0, 100, 100));
//		rootArtist.addChild(p);
//		putAll(p);
//		p.addChild(new SolidBackDrop(0, 0, 20, 20, Color.GRAY));
//		Row r = new Row(5, 310, 350, 50);
//		rootArtist.addChild(r);
//		putAll(r);
//		Column col = new Column(5, 370, 50, 200);
//		rootArtist.addChild(col);
//		putAll(col);
		
		return rootArtist;
	}
	
	protected Artist buildTest1(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
		putAll(rootArtist);
		Pile p = new Pile(5, 200, 100, 100);
		p.addChild(new SolidBackDrop(0, 0, 900, 900, Color.GRAY));
		p.addChild(new SimpleFrame(0, 0, 100, 100));
		rootArtist.addChild(p);
		putAll(p);
		p.addChild(new SolidBackDrop(0, 0, 20, 20, Color.GRAY));
		Row r = new Row(5, 310, 350, 50);
		rootArtist.addChild(r);
		putAll(r);
		Column col = new Column(5, 370, 50, 200);
		rootArtist.addChild(col);
		putAll(col);
		
		return rootArtist;
	}
	
	 protected void putAll(Artist rootArtist){putAll(rootArtist, false);}
	    
		protected void putAll(Artist rootArtist, boolean allAtOrigin){
			if(allAtOrigin){
				rootArtist.addChild(new SimpleFrame(0, 0, 20, 20));
				rootArtist.addChild(new SolidBackDrop(0, 0, 20, 20, Color.BLUE));
				rootArtist.addChild(new Icon(0, 0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
				Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
				NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
				rootArtist.addChild(new NinePartImage(0, 0, 200, 20, patches));
				rootArtist.addChild(new TextArtist(0, 0, "SSUI RoCkS!!!!", Typeface.DEFAULT_BOLD, 50f));
			}
			else{
				rootArtist.addChild(new SimpleFrame(5, 5, 20, 20));
				rootArtist.addChild(new SolidBackDrop(5, 30, 20, 20, Color.BLUE));
				rootArtist.addChild(new Icon(5, 55, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
				Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
				NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
				rootArtist.addChild(new NinePartImage(30, 5, 200, 20, patches));
				rootArtist.addChild(new TextArtist(30, 30, "SSUI RoCkS!!!!", Typeface.DEFAULT_BOLD, 50f));
			}
		}
}
