package cn.bjjoy.bms.util;

public class Page {

	private long page; // 当前页
	private long rows; // 每页数据数
	private long total;
	private long totalPage;

	public Page() {
		super();
	}

	public Page(Long page, Long rows, Long total) {
		super();
		this.page = page;
		this.rows = rows > 100 ? 10 : rows;
		this.total = total;
		this.totalPage = total / rows;

		if(total % rows > 0) {
			this.totalPage += 1;
		}
		
		if(this.totalPage < 1) {
			this.totalPage = 1;
		}
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}