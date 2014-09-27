package com.tdil.thalamus.android.menu.cars;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tdil.thalamus.android.car.ActivityCars;
import com.tdil.thalamus.android.car.CarsParkedModeOnClick;
import com.tdil.thalamus.android.car.CarsPathOnClick;
import com.tdil.thalamus.android.car.CarsPhoneOnClick;
import com.tdil.thalamus.android.car.CarsPositionsOnClick;
import com.tdil.thalamus.android.car.CarsSpeedOnClick;
import com.tdil.thalamus.android.car.CarsZoneOnClick;

public class CarsViewGroup extends ViewGroup {
	
	private ActivityCars activity;
	private int width;
	private ToggleCarsMenuOnClick menuListener;

	public CarsViewGroup(Context context) {
		super(context);
		activity = (ActivityCars)context;
	}

	public CarsViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		activity = (ActivityCars)context;
	}

	public CarsViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (ActivityCars)context;
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

		// Posición Menu
		int topPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50)).divide(BigDecimal.valueOf(100)).intValue();
		int leftPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50)).divide(BigDecimal.valueOf(100)).intValue();
		topPrimerBoton = topPrimerBoton - (widthAndHeightBoton / 2);
		leftPrimerBoton = leftPrimerBoton - (widthAndHeightBoton / 2);
		
		ImageView menuImage = (ImageView)getChildAt(0);
		menuImage.layout(leftPrimerBoton, topPrimerBoton, leftPrimerBoton + widthAndHeightBoton, topPrimerBoton + widthAndHeightBoton);

		// Posicion v1
		int topSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(16.00)).divide(BigDecimal.valueOf(100)).intValue();
		topSegundoBoton = topSegundoBoton - (widthAndHeightBoton / 2);
		leftSegundoBoton = leftSegundoBoton - (widthAndHeightBoton / 2);
		
		View carPositionsButtonMenu = getChildAt(1);
		carPositionsButtonMenu.layout(leftSegundoBoton, topSegundoBoton, leftSegundoBoton + widthAndHeightBoton, topSegundoBoton + widthAndHeightBoton);
		
		// Posicion v2
		int topTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(33.00)).divide(BigDecimal.valueOf(100)).intValue();
		topTercerBoton = topTercerBoton - (widthAndHeightBoton / 2);
		leftTercerBoton = leftTercerBoton - (widthAndHeightBoton / 2);
		
		View carSpeedButtonMenu = getChildAt(2);
		carSpeedButtonMenu.layout(leftTercerBoton, topTercerBoton, leftTercerBoton + widthAndHeightBoton, topTercerBoton + widthAndHeightBoton);
		
		// Posicion v3
		int topCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(79.70)).divide(BigDecimal.valueOf(100)).intValue();
		int leftCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(33.00)).divide(BigDecimal.valueOf(100)).intValue();
		topCuartoBoton = topCuartoBoton - (widthAndHeightBoton / 2);
		leftCuartoBoton = leftCuartoBoton - (widthAndHeightBoton / 2);
		View carZoneButtonMenu = getChildAt(3); // btnFooterSecureZone
		carZoneButtonMenu.layout(leftCuartoBoton, topCuartoBoton, leftCuartoBoton + widthAndHeightBoton, topCuartoBoton + widthAndHeightBoton);
		
		// Posicion v4
		int topQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(84.00)).divide(BigDecimal.valueOf(100)).intValue();
		topQuintoBoton = topQuintoBoton - (widthAndHeightBoton / 2);
		leftQuintoBoton = leftQuintoBoton - (widthAndHeightBoton / 2);
		View carPhoneButtonMenu = getChildAt(4); // btnFooterSpeed
		carPhoneButtonMenu.layout(leftQuintoBoton, topQuintoBoton, leftQuintoBoton + widthAndHeightBoton, topQuintoBoton + widthAndHeightBoton);
		
		// Posicion v5
		int topSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(66.30)).divide(BigDecimal.valueOf(100)).intValue();
		topSextoBoton = topSextoBoton - (widthAndHeightBoton / 2);
		leftSextoBoton = leftSextoBoton - (widthAndHeightBoton / 2);
		View carPathButtonMenu = getChildAt(5); // historic
		carPathButtonMenu.layout(leftSextoBoton, topSextoBoton, leftSextoBoton + widthAndHeightBoton, topSextoBoton + widthAndHeightBoton);
		
		// Posicion v6
		int topSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(79.70)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(66.30)).divide(BigDecimal.valueOf(100)).intValue();
		topSeptimoBoton = topSeptimoBoton - (widthAndHeightBoton / 2);
		leftSeptimoBoton = leftSeptimoBoton - (widthAndHeightBoton / 2);
		View carParkedModeButtonMenu = getChildAt(6); // historic
		carParkedModeButtonMenu.layout(leftSeptimoBoton, topSeptimoBoton, leftSeptimoBoton + widthAndHeightBoton, topSeptimoBoton + widthAndHeightBoton);

		if (menuListener == null) {
			menuListener = new ToggleCarsMenuOnClick(true, menuImage, carPositionsButtonMenu, carSpeedButtonMenu, carZoneButtonMenu, 
					carPhoneButtonMenu, carPathButtonMenu, carParkedModeButtonMenu);
			carPositionsButtonMenu.setOnClickListener(new CarsPositionsOnClick(this.activity));
			carSpeedButtonMenu.setOnClickListener(new CarsSpeedOnClick(this.activity));
			carZoneButtonMenu.setOnClickListener(new CarsZoneOnClick(this.activity));
			carPhoneButtonMenu.setOnClickListener(new CarsPhoneOnClick(this.activity));
			carPathButtonMenu.setOnClickListener(new CarsPathOnClick(this.activity));
			carParkedModeButtonMenu.setOnClickListener(new CarsParkedModeOnClick(this.activity));
			menuImage.setOnClickListener(menuListener);
		}
	}

}
