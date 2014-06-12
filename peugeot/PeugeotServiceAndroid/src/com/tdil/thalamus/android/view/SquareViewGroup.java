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
Tamaño de los botones 28% del ancho de la pantalla 

rd_icon_location.png
	X: 15,90% 
	Y: 4,17%
rd_icon_pois.png
	X: 73.42%
	Y: 36.74%
rd_icon_recorridos.png
	X: 55,19%
	Y: 69,05%
rd_icon_services.png
	X: 54,98%
	Y: 4,17%
rd_icon_velocidades.png
	X: 16,39%
	Y: 69,05%
rd_icon_zonas.png
	X: -1,69%
	Y: 36,47%

*/	
		int widthAndHeightBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(28)).divide(BigDecimal.valueOf(100)).intValue();
		
		int topPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(89.30)).divide(BigDecimal.valueOf(100)).intValue();
		topPrimerBoton = topPrimerBoton - (widthAndHeightBoton / 2);
		leftPrimerBoton = leftPrimerBoton - (widthAndHeightBoton / 2);
		
		View v = getChildAt(0);
		v.layout(leftPrimerBoton, topPrimerBoton, leftPrimerBoton + widthAndHeightBoton, topPrimerBoton + widthAndHeightBoton);

		// Posicion item 2
		int topSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(17.20)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(30.60)).divide(BigDecimal.valueOf(100)).intValue();
		topSegundoBoton = topSegundoBoton - (widthAndHeightBoton / 2);
		leftSegundoBoton = leftSegundoBoton - (widthAndHeightBoton / 2);
		
		v = getChildAt(1);
		v.layout(leftSegundoBoton, topSegundoBoton, leftSegundoBoton + widthAndHeightBoton, topSegundoBoton + widthAndHeightBoton);
		
		// Posicion item 3
		int topTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(17.20)).divide(BigDecimal.valueOf(100)).intValue();
		int leftTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(70.20)).divide(BigDecimal.valueOf(100)).intValue();
		topTercerBoton = topTercerBoton - (widthAndHeightBoton / 2);
		leftTercerBoton = leftTercerBoton - (widthAndHeightBoton / 2);
		
		v = getChildAt(2);
		v.layout(leftTercerBoton, topTercerBoton, leftTercerBoton + widthAndHeightBoton, topTercerBoton + widthAndHeightBoton);
		
		// Posicion item 4 TOP: 50,00% LEFT: 10,70%
		int topCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(10.70)).divide(BigDecimal.valueOf(100)).intValue();
		topCuartoBoton = topCuartoBoton - (widthAndHeightBoton / 2);
		leftCuartoBoton = leftCuartoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(3); // btnFooterSecureZone
		v.layout(leftCuartoBoton, topCuartoBoton, leftCuartoBoton + widthAndHeightBoton, topCuartoBoton + widthAndHeightBoton);
		
		// Posicion item 5 TOP: 50,00% LEFT: 10,70%
		int topQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(82.80)).divide(BigDecimal.valueOf(100)).intValue();
		int leftQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(30.60)).divide(BigDecimal.valueOf(100)).intValue();
		topQuintoBoton = topQuintoBoton - (widthAndHeightBoton / 2);
		leftQuintoBoton = leftQuintoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(4); // btnFooterSpeed
		v.layout(leftQuintoBoton, topQuintoBoton, leftQuintoBoton + widthAndHeightBoton, topQuintoBoton + widthAndHeightBoton);
		
		// Posicion item 5 TOP: 50,00% LEFT: 10,70%
		int topSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(82.80)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(70.20)).divide(BigDecimal.valueOf(100)).intValue();
		topSextoBoton = topSextoBoton - (widthAndHeightBoton / 2);
		leftSextoBoton = leftSextoBoton - (widthAndHeightBoton / 2);
		v = getChildAt(5); // historic
		v.layout(leftSextoBoton, topSextoBoton, leftSextoBoton + widthAndHeightBoton, topQuintoBoton + widthAndHeightBoton);

	}

}
