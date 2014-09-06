package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.model.Camera;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CameraListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<Camera> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	Camera iterCamera = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public CameraListAdapter(Activity a, ArrayList<Camera> d, Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;

		/*********** Layout inflator to call external xml layout () **********************/
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder to contain inflated xml file elements ***********/
	public static class CameraViewHolder {
		public TextView cameraDescription;
		public View viewCamera;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		CameraViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.cameraitem, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new CameraViewHolder();
			holder.cameraDescription = (TextView) vi.findViewById(R.id.cameraDescription);
			holder.viewCamera = (View)vi.findViewById(R.id.viewCameraButton);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (CameraViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.cameraDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterCamera = null;
			iterCamera = (Camera) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.cameraDescription.setText(iterCamera.getDescription());
			
			ViewCameraListener viewCameraListener = new ViewCameraListener(iterCamera, data.size());
			holder.cameraDescription.setOnClickListener(viewCameraListener);
			holder.viewCamera.setOnClickListener(viewCameraListener);
			
			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(viewCameraListener);
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	/********* Called when Item click in ListView ************/
	private class ViewCameraListener implements OnClickListener {
		private Camera camera;
		private Integer size;
		
		ViewCameraListener(Camera camera,Integer size) {
			this.camera = camera;
			this.size = size;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), ActivityHomeCamera.class);
			intent.putExtra(ActivityHomeCamera.CAMERA, this.camera);
			intent.putExtra(ActivityHomeCamera.CAMERAS_COUNT, this.size);
			activity.startActivity(intent);
			activity.finish();
		}
	}
}