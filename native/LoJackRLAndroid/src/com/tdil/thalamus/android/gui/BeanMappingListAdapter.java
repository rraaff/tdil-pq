package com.tdil.thalamus.android.gui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Key and Value Array Adapter
 * 
 * @param <T>
 */
public class BeanMappingListAdapter<T> extends ArrayAdapter<T> {

	private BeanMappingFunction<T> mapFunction;

    /**
     * @param context
     * @param resource
     * @param textViewResourceId
     * @param objects
     */
    public BeanMappingListAdapter(final Context context, final int resource,
            final int textViewResourceId,
            final List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /**
     * @param context
     * @param resource
     * @param textViewResourceId
     */
    public BeanMappingListAdapter(final Context context, final int resource,
            final int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public BeanMappingListAdapter(final Context context, final int textViewResourceId,
    		final List<T> objects, BeanMappingFunction<T> function) {
        super(context, textViewResourceId, objects);
        this.mapFunction = function;
    }


    /**
     * @param context
     * @param textViewResourceId
     */
    public BeanMappingListAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
    }

    /**
     * Change the string value of the TextView with the value of the KeyValue.
     */
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final TextView view = (TextView) super.getView(position, convertView, parent);

        view.setText(mapFunction.value(getItem(position)));
        return view;
    }

    /**
     * Change the string value of the TextView with the value of the KeyValue.
     */
    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        final TextView view = (TextView) super.getDropDownView(position, convertView, parent);

        view.setText(mapFunction.value(getItem(position)));
        return view;
    }


    /**
     * Get the value of the KeyValue with the specified position in the data set.
     * 
     * @param position
     * @return
     */
    public String getValue(final int position) {
        return mapFunction.value(getItem(position));
    }

    /**
     * Get the key of the KeyValue with the specified position in the data set.
     * 
     * @param position
     * @return
     */
    public String getKey(final int position) {
        return mapFunction.key(getItem(position));
    }

    /**
     * Get the entry of the KeyValue with the specified position in the data set.
     * 
     * @param position
     * @return
     */
    public String getEntry(final int position) {
        return getValue(position);
    }

    /**
     * Get the entry value of the KeyValue with the specified position in the data set.
     * 
     * @param position
     * @return
     */
    public String getEntryValue(final int position) {
        return getKey(position);
    }

}
