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
		int widthAndHeightBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(6.964420)).divide(BigDecimal.valueOf(100)).intValue();
		
		int topPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(17.78955)).divide(BigDecimal.valueOf(100)).intValue();
		int leftPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(55.86677)).divide(BigDecimal.valueOf(100)).intValue();
		topPrimerBoton = topPrimerBoton - (widthAndHeightBoton / 2);
		leftPrimerBoton = leftPrimerBoton - (widthAndHeightBoton / 2);
		
		View v = getChildAt(0);
		v.layout(leftPrimerBoton, topPrimerBoton, leftPrimerBoton + widthAndHeightBoton, topPrimerBoton + widthAndHeightBoton);

		int topSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(42.39712)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(68.50870)).divide(BigDecimal.valueOf(100)).intValue();
		topSegundoBoton = topSegundoBoton - (widthAndHeightBoton / 2);
		leftSegundoBoton = leftSegundoBoton - (widthAndHeightBoton / 2);
		
		// Posicion item 2
		v = getChildAt(1);
		v.layout(leftSegundoBoton, topSegundoBoton, leftSegundoBoton + widthAndHeightBoton, topSegundoBoton + widthAndHeightBoton);
		
		int topTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(69.03860)).divide(BigDecimal.valueOf(100)).intValue();
		int leftTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(64.42089)).divide(BigDecimal.valueOf(100)).intValue();
		topTercerBoton = topTercerBoton - (widthAndHeightBoton / 2);
		leftTercerBoton = leftTercerBoton - (widthAndHeightBoton / 2);
		
		// Posicion item 3
		v = getChildAt(2);
		v.layout(leftTercerBoton, topTercerBoton, leftTercerBoton + widthAndHeightBoton, topTercerBoton + widthAndHeightBoton);

	}

}
