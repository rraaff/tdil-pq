package com.tdil.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class PaginationUtils {

	public static int parsePageParam(String pString) {
		if (StringUtils.isEmpty(pString)) {
			return 0;
		} else {
			if (StringUtils.isNumeric(pString)) {
				try {
					return Integer.parseInt(pString);
				} catch (NumberFormatException e) {
					return 0;
				}
			} else {
				return 0;
			}
		}
	}
	
	public static int currentPageLimit(int pageNumber, int pageSize) {
		return ((pageNumber + 1) * pageSize) + 1;
	}
	
	/**
	 * 
	 * @param totalItems cantidad todtal a paginal
	 * @param pageNumber pagina actual
	 * @param pageSize tamaño de la pagina
	 * @param pageSide cantidad de paginas a cada lado de la actual
	 * @return
	 * 
	 * Ejemplo: paginar 232 items, con pagina actual 4, tamaño de pagina 10, 2 paginas a la izquierda y dos a la derecha de la actual
	 */
	public static List<Integer> getPages(int totalItems, int pageNumber, int pageSize, int pageSide) {
		int maxpages = (pageSide * 2) + 1;
		List<Integer> result = new ArrayList<Integer>();
		int min = pageNumber - pageSide;
		if (min < 0) {
			min = 0;
		}
		int pages = 0;
		for (int i = min; (i * pageSize) < totalItems && pages < maxpages; i++) {
			result.add(i);
			pages = pages + 1;
		}
		if (result.isEmpty()) {
			result.add(0);
		}
		return result;
	}
	
	public static boolean isFirst(List<Integer> pages) {
		if (pages.isEmpty()) {
			 return true;
		}
		return pages.get(0).equals(0);
	}
	
	public static int first(List<Integer> pages) {
		return pages.get(0);
	}
	
	public static int last(List<Integer> pages) {
		return pages.get(pages.size() - 1);
	}
}
