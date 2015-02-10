package com.nilesh.pp_mgmt;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewDetailFragment extends Fragment {
	String mURL = "http://www.google.com";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("DetailFragment", "onCreate()");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v("DetailFragment", "onActivityCreated()");
		if (savedInstanceState != null) {
			mURL = savedInstanceState.getString("currentURL", "");
		}
		
		if(!mURL.trim().equalsIgnoreCase("")){
			WebView myWebView = (WebView) getView().findViewById(R.id.page1);
			myWebView.getSettings().setJavaScriptEnabled(true);
			myWebView.setWebViewClient(new MyWebViewClient());
			myWebView.loadUrl(mURL.trim());
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("currentURL", mURL);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("DetailFragment", "onCreateView()");
		View view = inflater.inflate(R.layout.webview_detail_fragment_layout, container, false);
	
		return view;
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}
	}
}
