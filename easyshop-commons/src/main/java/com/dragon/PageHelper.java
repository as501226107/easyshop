package com.dragon;

import java.util.ArrayList;
import java.util.List;

/**
 * 要传回页面参数的实体类
 * 
 * @author weibo
 * @param <T>
 *
 */
public class PageHelper<T> {

	private int totalCount;// 总数
	private int pageIndex;// 页码
	private int pageSize;// 每一页的数量
	private int totalPage;// 总共的页数
	private List<T> list;// 存放User集合
	private Object bean;// 回显的数据
	private int startPage;// 开始的页数,要求开始的页面之前最多有四个页面，之后最多有五个页面
	private int endPage;// 结束的页数

	private List<Integer> numbers=new ArrayList<Integer>();

	public PageHelper() {
		super();
	}

	public PageHelper(int totalCount, int pageIndex, int pageSize, List<T> list, Object bean) {
		this.totalCount = totalCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
		this.list = list;
		this.bean = bean;
		if (totalPage <= 10) {
			this.startPage = 1;
			this.endPage = totalPage;
		} else {
			this.startPage = pageIndex - 4;
			this.endPage = pageIndex + 5;
			if (startPage < 1) {
				this.startPage = 1;
				this.endPage = 10;
			} else if (endPage > totalPage) {
				this.endPage = totalPage;
				this.startPage = totalPage - 9;
			}
		}
		//要循环的页面  1-10
		for (int i = startPage; i <= endPage; i++) {
			numbers.add(i);
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "PageHelper{" +
				"totalCount=" + totalCount +
				", pageIndex=" + pageIndex +
				", pageSize=" + pageSize +
				", totalPage=" + totalPage +
				", list=" + list +
				", bean=" + bean +
				", startPage=" + startPage +
				", endPage=" + endPage +
				", numbers=" + numbers +
				'}';
	}
}
