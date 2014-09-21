package com.tdil.thalamus.android.gui;

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

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public abstract class AbstractListAdapter<T,U> extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<T> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	T iter = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public AbstractListAdapter(Activity a, ArrayList<T> d, Resources resLocal) {

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

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		U holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(getItemLayout(), null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = createViewHolder(vi);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (U) vi.getTag();

		if (data.size() <= 0) {

		} else {
			/***** Get each Model object from Arraylist ********/
			iter = null;
			iter = data.get(position);
			/************ Set Model values in Holder elements ***********/
			fillViewHolder(holder, iter);
			/******** Set Item Click Listner for LayoutInflater for each row ***********/
		}
		return vi;
	}

	protected abstract void fillViewHolder(U holder, T iter);

	protected abstract U createViewHolder(View vi);

	protected abstract int getItemLayout();

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	
}