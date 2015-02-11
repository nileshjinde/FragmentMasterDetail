package com.nilesh.pp_mgmt;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebviewDetailFragment extends Fragment {
	String mURL = "http://www.google.com";

	private WebView mWebViewPage1,mWebViewPage2;
	private Button btnPrev, btnNext;

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

		mWebViewPage1 = (WebView) getView().findViewById(R.id.page1);
		mWebViewPage2 = (WebView) getView().findViewById(R.id.page2);

		btnNext = (Button) getView().findViewById(R.id.btnNext);
		btnPrev = (Button) getView().findViewById(R.id.btnPrev);

		if(btnNext !=null && btnPrev!= null){
			btnNext.setOnClickListener(btnOnClickListener);
			btnPrev.setOnClickListener(btnOnClickListener);
		}

		loadWebView("http://www.google.com", mWebViewPage1);		
		loadWebView("http://www.futurismtechnologies.com", mWebViewPage2);
	}

	OnClickListener btnOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btnPrev){
				mWebViewPage1.setVisibility(View.VISIBLE);
				btnNext.setVisibility(View.VISIBLE);

				btnPrev.setVisibility(View.GONE);
				mWebViewPage2.setVisibility(View.GONE);
			}else if(v.getId() == R.id.btnNext){
				mWebViewPage1.setVisibility(View.GONE);
				btnNext.setVisibility(View.GONE);

				btnPrev.setVisibility(View.VISIBLE);
				mWebViewPage2.setVisibility(View.VISIBLE);
			}

		}
	};

	private void loadWebView(String strUrl, WebView myWebView ){
		if(!strUrl.trim().equalsIgnoreCase("")){

			if(myWebView != null){
				myWebView.getSettings().setJavaScriptEnabled(true);
				myWebView.setWebViewClient(new MyWebViewClient());
				myWebView.loadUrl(strUrl.trim());
			}
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
