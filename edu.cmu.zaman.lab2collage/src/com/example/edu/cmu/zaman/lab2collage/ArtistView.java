
package com.example.edu.cmu.zaman.lab2collage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class provides an Android view object which can host an Artist object
 * tree from the 05-4/633C (SSUI Mobile Lab) Project #2.  It basically keeps a 
 * reference to one Artist object, which is presumed to be the root of an 
 * Artist tree, and arranges to draw it.  Since there is no explicit damage 
 * management as part of the Artist API, to force a redraw of the Artist tree
 * you can call <code>invalidate()</code> on this host object.
 * 
 * @author Scott Hudson
 *
 */
public class ArtistView extends View {

	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * The artist object we host and create a drawing from.
	 */
	protected Artist _childArtist = null;
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Read access for the Artist that this View hosts
	 * @return the hosted Artist.
	 */
	public Artist getChildArtist() {return _childArtist;}
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Set the child that is being hosted.
	 * @param child the Artist object being hosted.
	 */
	public void setChildArtist(Artist child) {
		_childArtist = child;
	}
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Perform initialization common to all constructors.  Currently, this sets 
	 * up layout parameters to tell its parent container not to expand the size 
	 * of this widget to match the parent (which is the default).
	 */
	protected void initArtistView() {
		setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
	}
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Constructor for use in simple programatic creation.
	 * @param the Context this object is running in, through which it can access 
	 *        the current theme, resources, etc.
	 */
	public ArtistView(Context context) {
		super(context);
		initArtistView();
	}
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Constructor for use in XML-based creation.
	 * @param the Context this object is running in, through which it can access 
	 *        the current theme, resources, etc.
	 * @param attrs   the attributes of the tag that is inflating this object
	 */
	public ArtistView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initArtistView();
	}

	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Constructor for use in XML-based creation.
	 * @param context  the Context this object is running in, through which it 
	 *                 can access the current theme, resources, etc.
	 * @param attrs    the attributes of the tag that is inflating this object
	 * @param defStyle the default style to apply to this object. If 0, 
	 *                 no style will be applied (beyond what is included in 
	 *                 the theme). This may either be an attribute resource, 
	 *                 whose value will be retrieved from the current theme, 
	 *                 or an explicit style resource. 
	 */
	public ArtistView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initArtistView();
	}

	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Measurement implementation for this object. This either applies the 
	 * size given to us by the parent (if we are asked to be "exact") or 
	 * supplies our own size as matching that of our child Artist (possibly 
	 * limited by a maximum size given by the parent).
	 * @param widthMeasureSpec   horizontal space requirements as imposed 
	 *                           by the parent.
	 * @param heightMeasureSpec  vertical space requirements as imposed 
	 *                           by the parent.
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int w = 10;  // default size if we have no Artist to host
		int h = 10;
		Artist child = getChildArtist();
		
		// measure the child if we have one
		if (child != null) {
			// force a layout so we have a valid child size
			child.doLayout();
			w = (int)Math.ceil(child.getX()+child.getW());
			h = (int)Math.ceil(child.getY()+child.getH());
		}
		// apply the constraints imposed by our parent on that measurement
		setMeasuredDimension(doSimpleMeasure(widthMeasureSpec, w), 
				             doSimpleMeasure(heightMeasureSpec,h));
	}
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
	/**
	 * Utility routine to unpack a measurement specification and apply it 
	 * in conjunction with a desired value.  The measurement spec can basically
	 * apply an override of the local object (ask for an "exact" value it 
	 * specifies), impose a maximum value, or say that this object can do 
	 * whatever it wants.  
	 * @param forMeasureSpec  encoded measurement instructions 
	 *                        (optional-value and flag) as imposed by parent
	 * @param desiredValue    the value the local object want if it's not
	 *                        modified by instructions for the parent
	 * @return the size value we get from applying the measurement spec to 
	 *         the desired value.
	 */
	protected int doSimpleMeasure(int forMeasureSpec, int desiredValue) {
		int result = desiredValue;  
		int specMode = MeasureSpec.getMode(forMeasureSpec);
		int specSize = MeasureSpec.getSize(forMeasureSpec);
		
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST){
			result = Math.min(result, specSize);
		} 
		return result;
	}
	
	
	/* . . . . . . . . . . . . . . . . . . . . . . . . . */
    /**
     * Drawing routine for this object.  This first clears its background to 
     * white and then both lays out and draws the Artist tree that it hosts.
     * @param drawCanvas Canvas object we draw on
     */
	@Override
	protected void onDraw(Canvas drawCanvas) {
		// clear our background
		drawCanvas.drawColor(Color.WHITE);
		
		// do we have a child to draw?
		Artist child = getChildArtist();
		if (child != null) {
			
			// do a layout of the child
			child.doLayout();
			
			// set up coordinate system and clipping for it
			drawCanvas.translate(child.getX(), child.getY());
			drawCanvas.clipRect(0,0,child.getW(),child.getH());
			
			// draw it
			child.draw(drawCanvas);
		}
	}
}
