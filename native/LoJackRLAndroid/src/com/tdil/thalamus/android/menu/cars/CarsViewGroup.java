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
	
	private Context activity;
	private int width;
	private ToggleCarsMenuOnClick menuListener;

	
	public CarsViewGroup(Context context) {
		super(context);
		activity = context;
	}

	public CarsViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		activity = context;
	}

	public CarsViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = context;
	}
	
	@Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    width = MeasureSpec.getSize(widthMeasureSpec);
	    int height = MeasureSpec.getSize(heightMeasureSpec);
	    int size = width > height ? height : width;
	    setMeasuredDimension(size, size);
	}

	
/*
BOTON LOCALIZAR 
86px es el centro LEFT: 86 * 100 / 427 =  20.14%
TOP: 50%

BOTON VELOCIDADES
111px es el centro LEFT: 111 * 100 / 427 = 25.99%
287px es el centro TOP: 287 * 100 / 427 = 67.21%

BOTON ZONAS
173px es el centro LEFT: 173 * 100 / 427 = 40.51%
333px es el centro TOP:	 333 * 100 / 427 = 77.98%

BOTON TELÉFONO
252px es el centro LEFT: 252 * 100 / 427 = 59.01%
333px es el centro TOP:	 333 * 100 / 427 = 77.98%

BOTON RECORRIDOS
315px es el centro LEFT: 315 * 100 / 427 = 73.77%
287px es el centro TOP: 287 * 100 / 427 = 67.21%

BOTON ESTACIONAME
339px es el centro LEFT: 339 * 100 / 427 = 79.39%
TOP: 50%

*/
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		MathContext context = new MathContext(10,RoundingMode.FLOOR);
		
		int widthAndHeightBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.00)).divide(BigDecimal.valueOf(100)).intValue();

		// Posición Menu
		int topPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50)).divide(BigDecimal.valueOf(100)).intValue();
		int leftPrimerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50)).divide(BigDecimal.valueOf(100)).intValue();
		topPrimerBoton = topPrimerBoton - (widthAndHeightBoton / 2);
		leftPrimerBoton = leftPrimerBoton - (widthAndHeightBoton / 2);
		
		ImageView menuImage = (ImageView)getChildAt(0);
		menuImage.layout(leftPrimerBoton, topPrimerBoton, leftPrimerBoton + widthAndHeightBoton, topPrimerBoton + widthAndHeightBoton);

		// Posicion v1 (LOCALIZAR)
		int topSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSegundoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(20.14)).divide(BigDecimal.valueOf(100)).intValue();
		topSegundoBoton = topSegundoBoton - (widthAndHeightBoton / 2);
		leftSegundoBoton = leftSegundoBoton - (widthAndHeightBoton / 2);
		
		View carPositionsButtonMenu = getChildAt(1);
		carPositionsButtonMenu.layout(leftSegundoBoton, topSegundoBoton, leftSegundoBoton + widthAndHeightBoton, topSegundoBoton + widthAndHeightBoton);
		
		// Posicion v2 (VELOCIDAD)
		int topTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(67.21)).divide(BigDecimal.valueOf(100)).intValue();
		int leftTercerBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(25.00)).divide(BigDecimal.valueOf(100)).intValue();
		topTercerBoton = topTercerBoton - (widthAndHeightBoton / 2);
		leftTercerBoton = leftTercerBoton - (widthAndHeightBoton / 2);
		
		View carSpeedButtonMenu = getChildAt(2);
		carSpeedButtonMenu.layout(leftTercerBoton, topTercerBoton, leftTercerBoton + widthAndHeightBoton, topTercerBoton + widthAndHeightBoton);
		
		// Posicion v3 (ZONAS)
		int topCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(77.98)).divide(BigDecimal.valueOf(100)).intValue();
		int leftCuartoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(40.51)).divide(BigDecimal.valueOf(100)).intValue();
		topCuartoBoton = topCuartoBoton - (widthAndHeightBoton / 2);
		leftCuartoBoton = leftCuartoBoton - (widthAndHeightBoton / 2);
		
		View carZoneButtonMenu = getChildAt(3); // 
		carZoneButtonMenu.layout(leftCuartoBoton, topCuartoBoton, leftCuartoBoton + widthAndHeightBoton, topCuartoBoton + widthAndHeightBoton);
		
		// Posicion v4 (TELEFONOS)
		int topQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(77.98)).divide(BigDecimal.valueOf(100)).intValue();
		int leftQuintoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(59.01)).divide(BigDecimal.valueOf(100)).intValue();
		topQuintoBoton = topQuintoBoton - (widthAndHeightBoton / 2);
		leftQuintoBoton = leftQuintoBoton - (widthAndHeightBoton / 2);
		
		View carPhoneButtonMenu = getChildAt(4); // 
		carPhoneButtonMenu.layout(leftQuintoBoton, topQuintoBoton, leftQuintoBoton + widthAndHeightBoton, topQuintoBoton + widthAndHeightBoton);
		
		// Posicion v5 (HISTÓRICO)
		int topSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(67.21)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSextoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(73.77)).divide(BigDecimal.valueOf(100)).intValue();
		topSextoBoton = topSextoBoton - (widthAndHeightBoton / 2);
		leftSextoBoton = leftSextoBoton - (widthAndHeightBoton / 2);
		
		View carPathButtonMenu = getChildAt(5); // 
		carPathButtonMenu.layout(leftSextoBoton, topSextoBoton, leftSextoBoton + widthAndHeightBoton, topSextoBoton + widthAndHeightBoton);
		
		// Posicion v6 (ESTACIONAME)
		int topSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(50.00)).divide(BigDecimal.valueOf(100)).intValue();
		int leftSeptimoBoton = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(79.39)).divide(BigDecimal.valueOf(100)).intValue();
		topSeptimoBoton = topSeptimoBoton - (widthAndHeightBoton / 2);
		leftSeptimoBoton = leftSeptimoBoton - (widthAndHeightBoton / 2);
		
		View carParkedModeButtonMenu = getChildAt(6); // 
		carParkedModeButtonMenu.layout(leftSeptimoBoton, topSeptimoBoton, leftSeptimoBoton + widthAndHeightBoton, topSeptimoBoton + widthAndHeightBoton);

		if (menuListener == null) {
			if (this.activity instanceof ActivityCars) {
				ActivityCars activityCars = (ActivityCars)this.activity;
				menuListener = new ToggleCarsMenuOnClick(true, this, menuImage, carPositionsButtonMenu, carSpeedButtonMenu, carZoneButtonMenu, 
						carPhoneButtonMenu, carPathButtonMenu, carParkedModeButtonMenu);
				carPositionsButtonMenu.setOnClickListener(new CarsPositionsOnClick(activityCars, menuListener));
				carSpeedButtonMenu.setOnClickListener(new CarsSpeedOnClick(activityCars, menuListener));
				carZoneButtonMenu.setOnClickListener(new CarsZoneOnClick(activityCars, menuListener));
				carPhoneButtonMenu.setOnClickListener(new CarsPhoneOnClick(activityCars, menuListener));
				carPathButtonMenu.setOnClickListener(new CarsPathOnClick(activityCars, menuListener));
				carParkedModeButtonMenu.setOnClickListener(new CarsParkedModeOnClick(activityCars, menuListener));
				menuImage.setOnClickListener(menuListener);
			}
		}
	}

}
