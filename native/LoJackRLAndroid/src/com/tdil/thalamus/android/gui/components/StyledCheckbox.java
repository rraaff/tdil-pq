package com.tdil.thalamus.android.gui.components;

import com.tdil.thalamus.android.LoJackActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class StyledCheckbox extends CheckBox {

	public StyledCheckbox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public StyledCheckbox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public StyledCheckbox(Context context) {
		super(context);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {
		if (!this.isInEditMode()) {
		LoJackActivity.setCustomTypeface(context, this);
//		if (attrs!=null) {
//			 TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
//			 String fontName = a.getString(R.styleable.MyTextView_fontName);
//			 if (fontName!=null) {
//				 Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+fontName);
//				 setTypeface(myTypeface);
//			 }
//			 a.recycle();
//		}
		}
	}
}
