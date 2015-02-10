package com.nilesh.pp_mgmt;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity  {

	boolean detailPage = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("AndroidFragmentActivity", "onCreate()");
		Log.v("AndroidFragmentsavedInstanceState", savedInstanceState == null ? "true" : "false");
		
		Toast.makeText(this, "OnCreate" , Toast.LENGTH_LONG).show();

		setContentView(R.layout.activity_main);

		if(savedInstanceState == null) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			WebviewDetailFragment detailFragment = new WebviewDetailFragment();
			ft.add(R.id.fragmentContainer, detailFragment, "Detail_Fragment");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		/*if(findViewById(R.id.displayDetail) != null){
			detailPage = true;
			getFragmentManager().popBackStack();

			WebviewDetailFragment detailFragment = (WebviewDetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
			if(detailFragment == null){
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				detailFragment = new WebviewDetailFragment();
				ft.replace(R.id.displayDetail, detailFragment, "Detail_Fragment1");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		}*/

	}


	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
			Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
		}
		//setContentView(R.layout.activity_main);
	}*/

}
