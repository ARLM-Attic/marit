/* Copyright (C) 2013 Marie Högkvist

Licensed under GNU Lesser General Public License (LGPL)

For more information, visit
https://marit.codeplex.com/
*/
package com.hogkvist.marit;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener
{
	// Drawing information 
    private Path currPath = new Path();
    
    // Color and paintbrush settings
    private Paint activePaint = new Paint();

    // Debug information
    private boolean Debug = false; 
    private static final String TAG = "Debug";
	private static final String SOURCE = "MARIT.DrawView ";
    private List<Point> points = new ArrayList<Point>(); // Used for debugging purposes 
    private int activeColor; 
    
    public DrawView(Context context) 
    {
    	super(context);
    	init();
    }

    public DrawView(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) 
    {
        super(context, attrs, defStyle);
        init();
    }
    
	public void init()
    {
    	this.setOnTouchListener(this);

    	// Starting color and paint settings; 
        activePaint.setColor(Color.BLACK);
        activePaint.setStrokeWidth(3);
        activePaint.setStyle(Paint.Style.STROKE);
        activeColor = Color.BLACK; 
    }
    
    @Override
    public void onDraw(Canvas canvas) 
    {
    	if(Debug)
    	{
    		for (Point point : points) 
    		{
    			canvas.drawCircle(point.x, point.y, 5, activePaint);
    			activePaint.setColor(point.color); 
	        } 
    	}
    	
    	// TODO: Array of paths 
    	// TODO: Color state on lines 	    
    	// TODO: Optimize? Move draw to own thread - change view class to SurfaceView? 
    	canvas.drawPath(currPath, activePaint);
    }
   
    /** Creates lines based on the users touch input */
    public boolean onTouch(View view, MotionEvent event) 
    {
		Point point = new Point(); 
		point.x = event.getX();
		point.y = event.getY();
		
		int action = MotionEventCompat.getActionMasked(event);
		switch(action) 
		{
			case (MotionEvent.ACTION_DOWN):
				currPath.close();
    			currPath.moveTo(point.x, point.y);
				break;
			case (MotionEvent.ACTION_MOVE):
				currPath.lineTo(point.x, point.y);
    			currPath.moveTo(point.x, point.y);
				break;
			case (MotionEvent.ACTION_UP):
				currPath.lineTo(point.x, point.y);
    			currPath.moveTo(point.x, point.y);
				break;
			default: 
				break;
    	    }      
		
		if(Debug)
		{
			point.color = activeColor; 
			points.add(point);
			Log.v(TAG, SOURCE + "x: " + point.x + "y: " + point.y);
		}
		
		invalidate(); // Triggers onDraw() 
		return true; 
    }
    
    /** Clears the drawing screen */
	public void clear() 
	{
		points.clear();
		currPath.reset(); 
		invalidate();  // Triggers onDraw() 
	}

	/** Sets color of paintbrush */ 
	public void setColor(int color) 
	{
		activePaint.setColor(color);
		activeColor = color; 
	}
	
	/** Returns the current drawing as a bitmap */
	public Bitmap getDrawing() 
	{
		this.setDrawingCacheEnabled(true); 
		Bitmap originalImage= this.getDrawingCache();
		return originalImage; 		
	}
	
	// TODO 
	/** Loads a bitmap into our current drawing */
	public boolean loadDrawing(Bitmap bmp) 
	{
		return true; 
	}
}

/** Point class - used for debugging purposes */
class Point 
{
    float x, y;
    int color; 
}