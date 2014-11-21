/**
 * 
 */
package com.example.edu.cmu.zaman.lab2collage;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * This base class provides a partial implementation of the Artist interface.
 * You are to provide the rest of this implementation to create a full base
 * class called ArtistBase (which must inherit from this class). 
 * 
 * @see ssuimobile.Collage.Artist
 * @author Scott Hudson
 *
 */
public abstract class ArtistPrebase implements Artist {

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setPosition(android.graphics.PointF)
	 */
	@Override
    public void setPosition(PointF pos) {
		if (pos != null) {
			setPosition(pos.x, pos.y);
		}
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setPosition(float, float)
	 */
	@Override
	public void setPosition(float x, float y) {
		setX(x); 
		setY(y);
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setX(float)
	 */
	@Override
	abstract public void setX(float x);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setY(float)
	 */
	@Override
	abstract public void setY(float y);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getPosition()
	 */
	@Override
	public PointF getPosition() {
		return new PointF(getX(),getY());
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getX()
	 */
	@Override
	abstract public float getX();

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getY()
	 */
	@Override
	abstract public float getY();
	
	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#sizeIsIntrinsic()
	 */
	@Override
	public boolean sizeIsIntrinsic() {
		// default value -- override in subclasses that need to...
		return false;
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setSize(android.graphics.PointF)
	 */
	@Override
	public void setSize(PointF size) {
		if (size != null) {
			setSize(size.x,size.y);
		}
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setSize(float, float)
	 */
	@Override
	public void setSize(float w, float h) {
		setW(w); 
		setH(h);
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setW(float)
	 */
	@Override
	abstract public void setW(float w);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setH(float)
	 */
	@Override
	abstract public void setH(float h);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getSize()
	 */
	@Override
	public PointF getSize() {
		return new PointF(getW(),getH());
	}

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getW()
	 */
	@Override
	abstract public float getW();

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getH()
	 */
	@Override
	abstract public float getH();
	
	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getParent()
	 */
	@Override
	abstract public Artist getParent();

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#setParent()
	 */
	@Override
	abstract public void setParent(Artist newParent);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getNumChildren()
	 */
	@Override
	abstract public int getNumChildren();

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#getChildAt(int)
	 */
	@Override
	abstract public Artist getChildAt(int index);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#findChild(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public int findChild(Artist child);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#addChild(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void addChild(Artist child);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#removeChildAt(int)
	 */
	@Override
	abstract public void removeChildAt(int index);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#removeChild(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void removeChild(Artist child);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#moveChildFirst(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void moveChildFirst(Artist child);
	
	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#moveChildLast(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void moveChildLast(Artist child);

	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#moveChildEarlier(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void moveChildEarlier(Artist child);
	
	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#moveChildLater(ssuimobile.Collage.Artist)
	 */
	@Override
	abstract public void moveChildLater(Artist child);


	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#doLayout()
	 */
	@Override
	abstract public void doLayout();
	
	/* (non-Javadoc)
	 * @see ssuimobile.Collage.Artist#draw(android.graphics.Canvas)
	 */
	@Override
	abstract public void draw(Canvas onCanvas);
	
	/**
	 * Default constructor. 
	 */
	public ArtistPrebase() {
		// do default initialization at an odd size so its easy to see when
		// the size is never set up.
		this(0,0);
	}
	
	/**
	 * Constructor with position only.
	 */
	public ArtistPrebase(float xLoc, float yLoc) {
		// do default initialization at an odd size so its easy to see when
		// the size is never set up.
		this(xLoc,yLoc,13.7f, 17.9f);
	}
	
	/**
	 * Full constructor giving position and size.
	 */
	public ArtistPrebase(float xLoc, float yLoc, float width, float height) {
		setPosition(xLoc,yLoc);
		setSize(width,height);
	}
}
