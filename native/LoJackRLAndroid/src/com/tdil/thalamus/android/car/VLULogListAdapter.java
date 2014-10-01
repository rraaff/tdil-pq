package com.tdil.thalamus.android.car;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.model.VLUDataDTO;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class VLULogListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<VLUDataDTO> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	VLUDataDTO iter = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public VLULogListAdapter(Activity a, ArrayList<VLUDataDTO> d, Resources resLocal) {

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
	public static class VLUDataDTOViewHolder {

		public TextView dni;
		public TextView domain;
		public TextView message;
//		public ImageView alert;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		VLUDataDTOViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.vluitem, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new VLUDataDTOViewHolder();
//			holder.dni = (TextView) vi.findViewById(R.id.vluDni);
			holder.domain = (TextView) vi.findViewById(R.id.vluDomain);
			holder.message = (TextView) vi.findViewById(R.id.vluMessage);
//			holder.alert = (ImageView) vi.findViewById(R.id.vluAlertImage);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (VLUDataDTOViewHolder) vi.getTag();

		if (data.size() <= 0) {

		} else {
			/***** Get each Model object from Arraylist ********/
			iter = null;
			iter = data.get(position);

			/************ Set Model values in Holder elements ***********/
//			holder.dni.setText(iter.getDni());
			holder.domain.setText(iter.getDomain());
			holder.message.setText(iter.getMessage());
//			if (iter.getMessage() != null && iter.getMessage().length() > 0) {
//				holder.alert.setImageResource(R.drawable.android_button_plus);
//			}
//			holder.alert.setImageDrawable(drawable);
			// holder.image.setImageResource(res.getIdentifier("com.androidexample.customlistview:drawable/"+tempValues.getStatus(),null,null));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	
}