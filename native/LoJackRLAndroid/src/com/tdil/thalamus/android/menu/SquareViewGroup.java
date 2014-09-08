package com.tdil.thalamus.android.menu;

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
		
		int widthAndHeightBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(38.00)).divide(BigDecimal.valueOf(100)).intValue();

		// Posición LUGARES
		int topPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(79.70)).divide(BigDecimal.valueOf(100)).intValue();
		int leftPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(66.30)).divide(BigDecimal.valueOf(100)).intValue();
		topPrimerBoton = topPrimerBoton - (widthAndHeightBoton / 2);
		leftPrimerBoton = leftPrimerBoton - (widthAndHeightBoton / 2);
		
		View v = getChildAt(0);
		v.layout(leftPrimerBoton, topPrimerBoton, leftPrimerBoton + widthAndHeightBoton, topPrimerBoton + widthAndHeightBoton);

		// Posicion CARS
		int topSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(16.00)).divide(BigDecimal.valueOf(100)).intValue();
		topSegundoBoton = topSegundoBoton - (widthAndHeightBoton / 2);
		leftSegundoBoton = leftSegundoBoton - (widthAndHeightBoton / 2);
		
		v = getChildAt(1);
		v.layout(leftSegundoBoton, topSegundoBoton, leftSegundoBoton + widthAndHeightBoton, topSegundoBoton + widthAndHeightBoton);
		
		// Posicion HOME
		int topTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(33.00)).divide(BigDecimal.valueOf(100)).intValue();
		topTercerBoton = topTercerBoton - (widthAndHeightBoton / 2);
		leftTercerBoton = leftTercerBoton - (widthAndHeightBoton / 2);
		
		v = getChildAt(2);
		v.layout(leftTercerBoton, topTercerBoton, leftTercerBoton + widthAndHeightBoton, topTercerBoton + widthAndHeightBoton);
		
		// Posicion PETS
		int topCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(79.70)).divide(BigDecimal.valueOf(100)).intValue();
		int leftCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(33.00)).divide(BigDecimal.valueOf(100)).intValue();
		topCuartoBoton = topCuartoBoton - (widthAndHeightBoton / 2);
		leftCuartoBoton = leftCuartoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(3); // btnFooterSecureZone
		v.layout(leftCuartoBoton, topCuartoBoton, leftCuartoBoton + widthAndHeightBoton, topCuartoBoton + widthAndHeightBoton);
		
		// Posicion ESTACIONAME
		int topQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(84.00)).divide(BigDecimal.valueOf(100)).intValue();
		topQuintoBoton = topQuintoBoton - (widthAndHeightBoton / 2);
		leftQuintoBoton = leftQuintoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(4); // btnFooterSpeed
		v.layout(leftQuintoBoton, topQuintoBoton, leftQuintoBoton + widthAndHeightBoton, topQuintoBoton + widthAndHeightBoton);
		
		// Posicion LOCLUB
		int topSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(66.30)).divide(BigDecimal.valueOf(100)).intValue();
		topSextoBoton = topSextoBoton - (widthAndHeightBoton / 2);
		leftSextoBoton = leftSextoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(5); // historic
		v.layout(leftSextoBoton, topSextoBoton, leftSextoBoton + widthAndHeightBoton, topSextoBoton + widthAndHeightBoton);
		
		// Posicion VLU BADGE
		int widthAndHeightBadge = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(6.50)).divide(BigDecimal.valueOf(100)).intValue();
		int topSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(5.90)).divide(BigDecimal.valueOf(100)).intValue();
		topSeptimoBoton = topSeptimoBoton - (widthAndHeightBadge / 2);
		leftSeptimoBoton = leftSeptimoBoton - (widthAndHeightBadge / 2);
		v = getChildAt(6); // historic
		v.layout(leftSeptimoBoton, topSeptimoBoton, leftSeptimoBoton + widthAndHeightBadge, topSeptimoBoton + widthAndHeightBadge);

	}

}
