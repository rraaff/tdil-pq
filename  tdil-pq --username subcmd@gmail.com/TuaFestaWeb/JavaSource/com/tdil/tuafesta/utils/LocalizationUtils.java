package com.tdil.tuafesta.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class LocalizationUtils {

	public static String formatPrice(String priceSt) {
		String price = priceSt;
		if (price == null || StringUtils.isEmpty(price)) {
			return "-";
		}
		int index = price.indexOf('.');
		if (index != 0) {
			price = price.substring(0, index);
		}
		if (!StringUtils.isNumeric(price)) {
			return "-";
		}
		Integer intPrice = Integer.parseInt(price);
		DecimalFormat format = new DecimalFormat("$###,###,###,###.00", new DecimalFormatSymbols(new Locale("es", "ES")));
		return format.format(intPrice);
	}
	
	public static String formatPrice(BigDecimal price) {
		if (price == null) {
			return "-";
		}
		DecimalFormat format = new DecimalFormat("$###,###,###,###.00", new DecimalFormatSymbols(new Locale("es", "ES")));
		return format.format(price.intValue());
	}
	
	public static String formatPrice(int price) {
		DecimalFormat format = new DecimalFormat("$###,###,###,###.00", new DecimalFormatSymbols(new Locale("es", "ES")));
		return format.format(price);
	}
	
	public static void main(String[] args) {
		System.out.println(formatPrice(10000));
	}
}
