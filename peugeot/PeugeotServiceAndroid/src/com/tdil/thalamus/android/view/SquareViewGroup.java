package com.tdil.thalamus.android.view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
		
		MathContext context = new MathContext(10,RoundingMode.FLOOR);
		
		
/*
widthAndHeightBoton = 6.964420 % del ancho de la pantalla

topPrimerBoton		= 17.78955 % del ancho de la pantalla
leftPrimerBoton		= 55.86677 % del ancho de la pantalla

topSegundoBoton		= 42.39712 % del ancho de la pantalla
leftSegundoBoton	= 68.50870 % del ancho de la pantalla

topTercerBoton		= 69.03860 % del ancho de la pantalla
leftTercerBoton		= 64.42089 % del ancho de la pantalla

*/	
		// Posicion item 1
		View v = getChildAt(0);
		double firstDivide = 100;
		double firstMultiply = 55.86677;
		double substractDivide = 6.96442 / 2;
		BigDecimal leftBD = BigDecimal.valueOf(width).divide(BigDecimal.valueOf(firstDivide), context).multiply(BigDecimal.valueOf(firstMultiply));
		leftBD = leftBD.subtract(BigDecimal.valueOf(width).divide(BigDecimal.valueOf(substractDivide), context));
		
		int left = leftBD.intValue();
		
		double secondDivide = 100;
		double secondMultiply = 17.78955;
		double secondsubstractDivide = 6.96442 / 2;
		BigDecimal topBD = BigDecimal.valueOf(width).divide(BigDecimal.valueOf(secondDivide), context).multiply(BigDecimal.valueOf(secondMultiply));
		topBD = topBD.subtract(BigDecimal.valueOf(width).divide(BigDecimal.valueOf(secondsubstractDivide), context));
		
		int top = topBD.intValue();
		
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
