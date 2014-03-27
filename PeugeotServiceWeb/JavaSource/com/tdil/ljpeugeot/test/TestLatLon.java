package com.tdil.ljpeugeot.test;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class TestLatLon {

	public static void main(String[] args) {
		LatLng point1 = new LatLng(-34.72386255588855, -58.3079731640812);
		LatLng point2 = new LatLng(-34.72445177539831, -58.306732194207);
		double distance = LatLngTool.distance(point1, point2, LengthUnit.METER);
		System.out.println(distance);
	}
}
