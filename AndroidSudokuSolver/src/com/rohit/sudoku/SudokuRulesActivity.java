package com.rohit.sudoku;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;


public class SudokuRulesActivity extends Activity implements ViewFactory, OnClickListener {
	
	private ImageSwitcher rulesImageSwitcher;
	private int imageIndex = 0;
	
	int imageIds[] = { 0,0,0,0,0,0 };
	Button nextButton;
	Button previousButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sudoku_rules);
		
		rulesImageSwitcher = (ImageSwitcher) findViewById(R.id.rulesImageSwitcher);
		rulesImageSwitcher.setFactory(this);
		rulesImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		rulesImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		imageIds[0] = R.drawable.rule_1;
		imageIds[1] = R.drawable.rule_2;
		imageIds[2] = R.drawable.rule_3;
		imageIds[3] = R.drawable.rule_4;
		imageIds[4] = R.drawable.rule_5;
		imageIds[5] = R.drawable.rule_6;
		
		rulesImageSwitcher.setImageResource(imageIds[imageIndex]);

		nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener(this);
		previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener(this);
	}

	@Override
	public View makeView() {
		ImageView imageVew = new ImageView(this);
		imageVew.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageVew.setLayoutParams(new 
						ImageSwitcher.LayoutParams( LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT) );
		return imageVew;
	}

	String tag = "Touch";
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float xDown = 0;
		float yDown = 0;
		float xUp = 0;
		float yUp = 0;
		
	    if (event.getAction() == MotionEvent.ACTION_UP) {
	    	xDown = event.getHistoricalX(0, 0);
	    	yDown = event.getHistoricalY(0, 0);
	    	xUp = event.getX();
	        yUp = event.getY();
	        Log.d(tag, "ACTION_UP event occured, XUp = " + xUp + " ,xDown = " + xDown);
	        Log.d(tag, "event.getHistoricalX() = " + event.getHistoricalX(0, 0));
	        
	        float xMovement = xUp - xDown;
	        float yMovement = yUp - yDown;
	        if( xMovement < 0.0F ) {
	        	xMovement *= -1.0F;
	        }
	        if( yMovement < 0.0F ) {
	        	yMovement *= -1.0F;
	        }
	        
	        if( Float.compare(xMovement, yMovement) > 0  ) {
		        if( Float.compare(xUp, xDown) > 0 ) {
		        	imageIndex--;
		        	if(imageIndex < 0 ) {
		        		imageIndex = 5;
		        	}
		        	rulesImageSwitcher.setImageResource(imageIds[imageIndex]);
		        	Log.d(tag, "xUp > XDown, XUp = " + xUp + " ,xDown = " + xDown);
		        	return true;
		        }
		        else if( Float.compare(xUp, xDown) < 0 ) {
		        	imageIndex++;
		        	if(imageIndex >= 6 ) {
		        		imageIndex = 0;
		        	}
		        	rulesImageSwitcher.setImageResource(imageIds[imageIndex]);
		        	Log.d(tag, "xUp < XDown, XUp = " + xUp + " ,xDown = " + xDown);
		        	return true;
		        }
	        }
	    }
	    return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nextButton:
			imageIndex++;
        	if(imageIndex >= 6 ) {
        		imageIndex = 0;
        	}
        	rulesImageSwitcher.setImageResource(imageIds[imageIndex]);
			break;
		case R.id.previousButton:
			imageIndex--;
        	if(imageIndex < 0 ) {
        		imageIndex = 5;
        	}
        	rulesImageSwitcher.setImageResource(imageIds[imageIndex]);
			break;
		
		}
	}
	
	private void CreateMenu(Menu menu) {
		MenuItem menuExit = menu.add(0, 0, 2, "Home");
		{
			menuExit.setAlphabeticShortcut('h');
			menuExit.setIcon(R.drawable.home_icon);
		}
	}
	
	private boolean MenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			this.finish();
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}
}