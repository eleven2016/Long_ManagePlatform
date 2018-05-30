package com.eleven.manage.platform.dto;

/**
 * @author ywl
 * @date 2018/5/25
 **/
public class PaginationDTO {
    private long pageSize = 10;
    private long pageNum  = 1;
    private long total;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
