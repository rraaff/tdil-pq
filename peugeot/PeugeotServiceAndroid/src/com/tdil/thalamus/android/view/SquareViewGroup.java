package com.tdil.thalamus.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SquareViewGroup extends ViewGroup {
	
	private int width;

	public SquareViewGroup(Context context) {
		super(context);
	}

	public SquareViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SquareViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    width = MeasureSpec.getSize(widthMeasureSpec);
	    int height = MeasureSpec.getSize(heightMeasureSpec);
	    int size = width > height ? height : width;
	    setMeasuredDimension(size, size);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// Posicion item 1
		View v = getChildAt(0);
		int left = (width / 200 * 142) - (width / 28);
		int top = (width / 200 * 45) - (width / 28);
		int buttonWidth = width / 14;
		v.layout(left, top, left + buttonWidth, top + buttonWidth);
		
		// Posicion item 2
		v = getChildAt(1);
		left = width / 3 * 2;
		top = width / 5 * 2;
		v.layout(left, top, left + buttonWidth, top + buttonWidth);
		
		// Posicion item 3
		v = getChildAt(2);
		left = width / 3 * 2;
		top = width / 5 * 4;
		v.layout(left, top, left + buttonWidth, top + buttonWidth);

	}

}
