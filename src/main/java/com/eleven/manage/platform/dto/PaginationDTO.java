package com.eleven.manage.platform.dto;

/**
 * @author ywl
 * @date 2018/5/25
 **/
public class PaginationDTO {
    private int pageSize    = 20;
    private int currentPage = 1;
    private int total;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
