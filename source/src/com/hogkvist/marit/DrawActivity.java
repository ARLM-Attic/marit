/* Copyright (C) 2013 Marie Högkvist

Licensed under GNU Lesser General Public License (LGPL)

For more information, visit
https://marit.codeplex.com/
*/
package com.hogkvist.marit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class DrawActivity extends Activity
{

	// Location of file in file system
	private static final String currentDrawing = "MARIT.current.png";
	 
	// Debug information
	boolean Debug = false; 
	private static final String TAG = "Debug";
	private static final String SOURCE = "MARIT.DrawActivity ";
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw); //Load the drawing screen
    }    

    /** Saves the current drawing when app is minimized */
    protected void onPause()
    {
    	super.onPause();
    	
    	DrawView drawView = (DrawView)findViewById(R.id.DrawView); // Our draw view
    	saveToFile(drawView.getDrawing());
    }
    
    /** Loads the most recent drawing from file */
    protected void onResume()
    {
    	super.onResume(); 
    	//DrawView drawView = (DrawView)findViewById(R.id.DrawView);
    	//drawView.loadDrawing(openFile());
    }
    
    // TODO: Separate file handler class 
    // TODO: Error handling 
    /** Save bitmap to file */
    protected void saveToFile(Bitmap bitmap)
    {
        try {
        	OutputStream fOut = null;
        	File file = new File(this.getFilesDir() + currentDrawing);
        	fOut = new FileOutputStream(file);
        	bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        	fOut.flush();
        	fOut.close();
        }
        catch (Exception e) {
        	Log.v(TAG, SOURCE + "saveToFile ERROR: " + e);
        }
    }
    
    // TODO: Separate file handler class 
    // TODO: Error handling 
    /** Load bitmap from file */
    protected Bitmap openFile()
    {
        try{
        	String filePath = this.getFilesDir().getPath().toString();
        	return BitmapFactory.decodeFile(filePath + currentDrawing);
        }
        catch (Exception e) {
        	Log.v(TAG, SOURCE + "openFile ERROR: " + e);
        	return null; 
        }
    }
}
