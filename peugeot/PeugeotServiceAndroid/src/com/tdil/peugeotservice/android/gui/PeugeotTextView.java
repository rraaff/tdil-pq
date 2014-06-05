package com.tdil.peugeotservice.android.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tdil.peugeotservice.android.PeugeotActivity;

public class PeugeotTextView extends TextView {

	public PeugeotTextView(final Context context) {
	    this(context, null);
	}

	public PeugeotTextView(final Context context, final AttributeSet attrs) {
	    this(context, attrs, 0);
	}

	public PeugeotTextView(final Context context, final AttributeSet attrs, final int defStyle) {
	    super(context, attrs, defStyle);
	    try {
	    	setTypeface(PeugeotActivity.getNormalFont(context));
	    } catch (Throwable e){}
	}
}
