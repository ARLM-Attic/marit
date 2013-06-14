/* Copyright (C) 2013 Marie Högkvist

Licensed under GNU Lesser General Public License (LGPL)

For more information, visit
https://marit.codeplex.com/
*/
package com.hogkvist.marit;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DrawButton extends Button implements OnClickListener
{	
    // Debug information
    private static final String TAG = "Debug";
	private static final String SOURCE = "MARIT.DrawButton ";
    
	public DrawButton(Context context, AttributeSet attrs, int defStyle) 
	{
        super(context, attrs, defStyle);
        init();
    }

    public DrawButton(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        init();
    }

    public DrawButton(Context context) 
    {
        super(context);
        init();
    }

	private void init() 
    {
    	setOnClickListener(this); 
    }
    
    /** Determines which button has been clicked based on id and calls the corresponding drawView method */
	@Override
    public void onClick(View view) 
	{
		Activity activity = (Activity)view.getContext();
    	DrawView drawView = (DrawView)activity.findViewById(R.id.DrawView);
    	
    	switch(view.getId()) 
    	{
    		// TODO: Dialog
    		case R.id.ResetButton:
   				drawView.clear();
    		break;
    	 	/*case R.id.ColorButton_Green:
    			drawView.setColor(Color.GREEN);
    		break;
    		case R.id.ColorButton_Red:
    			drawView.setColor(Color.RED);
    		break;
    		case R.id.ColorButton_Blue:
    			drawView.setColor(Color.BLUE);
    		break;
    		case R.id.ColorButton_Black:
    			drawView.setColor(Color.BLACK);
    		break;*/
    		default:
    			//Do nothing
    		break;
        }
	}
}
