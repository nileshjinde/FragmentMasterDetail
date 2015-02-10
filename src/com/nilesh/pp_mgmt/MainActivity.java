package com.nilesh.pp_mgmt;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nilesh.pp_mgmt.ListFragment.OnURLSelectedListener;

public class MainActivity extends Activity implements OnURLSelectedListener  {

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
			ListFragment listFragment = new ListFragment();
			ft.add(R.id.displayList, listFragment, "List_Fragment");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if(findViewById(R.id.displayDetail) != null){
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
		}

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
		setContentView(R.layout.activity_main);
	}*/
	
	@Override
	public void onURLSelected(String URL) {
		Log.v("AndroidFragmentActivity",URL);

		if(detailPage){
			WebviewDetailFragment detailFragment = (WebviewDetailFragment)
					getFragmentManager().findFragmentById(R.id.displayDetail);
			detailFragment.updateURLContent(URL);
		}
		else{
			WebviewDetailFragment detailFragment = new WebviewDetailFragment();
			detailFragment.setURLContent(URL);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.displayList, detailFragment, "Detail_Fragment2");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}
	}
}
