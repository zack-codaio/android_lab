package com.example.edu.cmu.zaman.lab2collage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Minimal test app for SSUI Mobile Project #2 (Collage).  To use this 
 * test framework implement the body of buildTest().
 * 
 * @author Scott Hudson
 *
 */
public class TestActivity extends Activity {
	
	protected Artist buildTest() {
		// create and return a test Artist tree here...
        Artist child = new ArtistBase();
        child.addChild(new ArtistBase());
        return /* replace this: */ new ArtistBase();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// let the base class take care of bookkeeping stuff...
        super.onCreate(savedInstanceState);

        // create an ArtistView widget and install a generated test tree there
        ArtistView root = new ArtistView(this);
        root.setChildArtist(buildTest());
        
        // put that widget in a frame so we have a layout object above it to 
        // listen to the fact that it doesn't want to be expanded to fill the
        // screen (the frame gets expanded, but then the root object stays 
        // it's original size within the frame).  
        FrameLayout frame = new FrameLayout(this);
        frame.addView(root);
        setContentView(frame);
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
    
    protected Artist buildTest2(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
		SolidBackDrop s = new SolidBackDrop(0, 0, 50, 50, Color.GREEN);
		rootArtist.addChild(s);
		s.setPosition(-40, -40);
		SolidBackDrop s2 = new SolidBackDrop(0, 0, 50, 50, Color.BLUE);
		rootArtist.addChild(s2);
		SolidBackDrop s3 = new SolidBackDrop(0, 0, 50, 50, Color.RED);
		rootArtist.addChild(s3);
		rootArtist.moveChildLast(s);
		rootArtist.removeChild(s3);
		
		Circle c = new Circle(100, 100, 300, 400, 150, 200, 100);		
		rootArtist.addChild(c);
		for(int i = 0; i<8; i++){
			c.addChild(new SolidBackDrop(0, 0, 20, 20, Color.BLUE));
		}
		SolidBackDrop s4 = new SolidBackDrop(100, 100, 50, 50, Color.MAGENTA);
		c.addChild(s4);
		rootArtist.addChild(s4);
		
		OvalClip o = new OvalClip(5, 200, 50, 50);
		rootArtist.addChild(o);
		o.addChild(new SolidBackDrop(0, 0, 50, 50, Color.BLUE));
		putAll(o, true);
		
		return rootArtist;
	}
    
    protected Artist buildTest3(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
		
		try{
			rootArtist.addChild(null);
			Log.d("ssui_grading", "Add null child:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Add null Child:FAIL");
		}
		
		try{
			if(rootArtist.getChildAt(-1) == null)
				Log.d("ssui_grading", "Get bad Child:PASS");
			else
				Log.d("ssui_grading", "Get bad Child:FAIL");
		} catch (Exception e){
			Log.d("ssui_grading", "Get bad Child:FAIL");
		}
		
		try{
			if(rootArtist.findChild(new SolidBackDrop(0, 0, 400, 800, Color.WHITE)) == -1 && rootArtist.findChild(null) == -1)
				Log.d("ssui_grading", "Find bad Child:PASS");
			else
				Log.d("ssui_grading", "Find bad Child:FAIL");
		} catch (Exception e){
			Log.d("ssui_grading", "Find bad Child:FAIL");
		}
		

		try{
			rootArtist.moveChildLater(new SolidBackDrop(0, 0, 400, 800, Color.WHITE));
			rootArtist.moveChildLater(null);
			Log.d("ssui_grading", "Move bad Child:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Move bad Child:FAIL");
		}
		
		try{
			SolidBackDrop s = new SolidBackDrop(0, 0, 50, 50, Color.GREEN);
			s.setPosition(null);
			s.setSize(null);
			Log.d("ssui_grading", "Set position/size null:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Set position/size null:FAIL");
		}		
		
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
