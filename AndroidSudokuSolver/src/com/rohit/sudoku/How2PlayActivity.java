package com.rohit.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.WebView;


public class How2PlayActivity extends Activity implements OnKeyListener {
	
	private WebView how2PlayWebView;
	private static final String HOME_PAGE = "file:///android_asset/how2play.html";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.how_to_play);

		how2PlayWebView = (WebView) findViewById(R.id.how2PlayWebView);
		how2PlayWebView.loadUrl(HOME_PAGE);
		
		how2PlayWebView.setOnKeyListener(this);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if( v.getId() == how2PlayWebView.getId() ) {
			if( event.getAction() == KeyEvent.ACTION_DOWN ) {
    			if(keyCode == KeyEvent.KEYCODE_BACK) {
    				if(! how2PlayWebView.getUrl().contains("how2play.html") ) {
    					how2PlayWebView.loadUrl(HOME_PAGE);
    					return true;
    				}
    			}
			}
		}
		return false;
	}
}