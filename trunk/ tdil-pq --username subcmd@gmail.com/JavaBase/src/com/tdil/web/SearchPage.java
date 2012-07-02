package com.tdil.web;

import java.util.List;


public class SearchPage<T> {

	private List<T> page;
	private boolean hasNext;
	
	public SearchPage(List<T> page, boolean hasNext) {
		super();
		this.page = page;
		this.hasNext = hasNext;
	}

	public SearchPage(List<T> result, int pageNumber, int pageSize) {
		if (result.isEmpty()) {
			page = result;
			hasNext = false;
		} else {
			int limit = PaginationUtils.currentPageLimit(pageNumber, pageSize);
			int size = result.size();
			page = result.subList(pageNumber * pageSize, Math.min(limit - 1, size));
			hasNext = limit == size;
			
		}
	}
	
	public T getItemAt(int position) {
		if (page.size() > position) {
			return page.get(position);
		} else {
			return null;
		}
	}

	public List<T> getPage() {
		return page;
	}
	public boolean isHasNext() {
		return hasNext;
	}
}
