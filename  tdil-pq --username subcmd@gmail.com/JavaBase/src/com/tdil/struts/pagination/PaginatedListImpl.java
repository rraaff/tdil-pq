package com.tdil.struts.pagination;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.properties.SortOrderEnum;

/**
 */
public class PaginatedListImpl implements IExtendedPaginatedList {

	/** current page index, starts at 0 */
	private int index;

	/** number of results per page (number of rows per page to be displayed ) */
	private int pageSize;

	/** total number of results (the total number of rows ) */
	private int fullListSize;

	/** list of results (rows found ) in the current page */
	private List list;

	private List fullList;

	/** default sorting order */
	private SortOrderEnum sortDirection = SortOrderEnum.ASCENDING;

	/** sort criteria (sorting property name) */
	private String sortCriterion;

	/** Http servlet request **/
	private HttpServletRequest request;
	
	private CriterionComparator comparator = new BeanUtilsComparator();

	/** default constructor **/
	public PaginatedListImpl(List fullList) {
		setFullList(fullList);
	}

	/**
	 * Create <code>PaginatedListImpl</code> instance using the
	 * <code>HttpServletRequest</code> object.
	 * 
	 * @param request
	 *            <code>HttpServletRequest</code> object.
	 */
	/**
	 * Create <code>PaginatedListImpl</code> instance using the
	 * <code>HttpServletRequest</code> object.
	 * 
	 * @param request
	 *            <code>HttpServletRequest</code> object.
	 * @param pageSize
	 *            the page size - the total number of rows per page.
	 */
	public PaginatedListImpl(List fullList, HttpServletRequest request, int pageSize) {
		this(fullList, request, pageSize, new BeanUtilsComparator());
	}
	
	public PaginatedListImpl(List fullList, HttpServletRequest request, int pageSize, CriterionComparator comparator) {
		setFullList(fullList);
		sortCriterion = request.getParameter(IExtendedPaginatedList.IRequestParameters.SORT);
		sortDirection = IExtendedPaginatedList.IRequestParameters.DESC.equals(request
				.getParameter(IExtendedPaginatedList.IRequestParameters.DIRECTION)) ? SortOrderEnum.DESCENDING
				: SortOrderEnum.ASCENDING;
		setPageSize(pageSize != 0 ? pageSize : DEFAULT_PAGE_SIZE);
		String page = request.getParameter(IExtendedPaginatedList.IRequestParameters.PAGE);
		index = page == null ? 0 : Integer.parseInt(page) - 1;
		setComparator(comparator);
		if (!StringUtils.isEmpty(sortCriterion)) {
			this.sortList(fullList, sortDirection);
		}
	}

	private void sortList(List fullList2, SortOrderEnum sortDirection2) {
		this.getComparator().setCriterion(sortCriterion);
		this.getComparator().setAsc(sortDirection2.equals(SortOrderEnum.ASCENDING));
		Collections.sort(fullList2, this.getComparator());
	}

	/**
	 * Create <code>PaginatedListImpl</code> instance .
	 * 
	 * @param pageSize
	 *            the page size - the total number of rows per page.
	 * @return <code>IExtendedPaginatedList</code> instance.
	 * @throws Exception
	 *             - problem while creating paginatedlist object.
	 */
	public IExtendedPaginatedList getPaginatedListObject(int pageSize) throws Exception {

		if (request == null) {
			throw new Exception("Cannot create paginated list. Depends upon HttpServletRequest.");
		}
		return new PaginatedListImpl(this.getFullList(), request, pageSize);
	}

	/**
	 * Set the non-null <code>HttpServletRequest</code> object.
	 * 
	 * @param request
	 *            a <code>HttpServletRequest</code> object.
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gorti.project.web.common.IExtendedPaginatedList#getFirstRecordIndex()
	 */
	public int getFirstRecordIndex() {
		return index * pageSize;
	}

	/**
	 * return the index.
	 * 
	 * @return the index.
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gorti.project.web.common.IExtendedPaginatedList#setIndex(int)
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gorti.project.web.common.IExtendedPaginatedList#getPageSize()
	 */
	public int getPageSize() {
		return pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gorti.project.web.common.IExtendedPaginatedList#setPageSize(int)
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getList()
	 */
	public List getList() {
		int start = this.getIndex() * this.getPageSize();
		return fullList.subList(start, Math.min(start + this.getPageSize(), this.getFullListSize()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gorti.project.web.common.IExtendedPaginatedList#setList(java.util
	 * .List)
	 */
	public void setList(List results) {
		this.list = results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getFullListSize()
	 */
	public int getFullListSize() {
		return this.getFullList().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gorti.project.web.common.IExtendedPaginatedList#setTotal(int)
	 */
	public void setTotalNumberOfRows(int total) {
		this.fullListSize = total;
	}

	/**
	 * return the total number of pages for the total number of rows.
	 * 
	 * @return the total.
	 */
	public int getTotalPages() {
		return (int) Math.ceil(((double) this.getFullListSize()) / pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getObjectsPerPage()
	 */
	public int getObjectsPerPage() {
		return pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getPageNumber()
	 */
	public int getPageNumber() {
		return index + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getSearchId()
	 */
	public String getSearchId() {
		// Not implemented for now.
		// This is required, if we want the ID to be included in the paginated
		// purpose.
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getSortCriterion()
	 */
	public String getSortCriterion() {
		return sortCriterion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.displaytag.pagination.PaginatedList#getSortDirection()
	 */
	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gorti.project.web.common.IExtendedPaginatedList#setSortCriterion(
	 * java.lang.String)
	 */
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gorti.project.web.common.IExtendedPaginatedList#setSortDirection(
	 * org.displaytag.properties.SortOrderEnum)
	 */
	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public List getFullList() {
		return fullList;
	}

	public void setFullList(List fullList) {
		this.fullList = fullList;
	}

	public CriterionComparator getComparator() {
		return comparator;
	}

	public void setComparator(CriterionComparator comparator) {
		this.comparator = comparator;
	}
}