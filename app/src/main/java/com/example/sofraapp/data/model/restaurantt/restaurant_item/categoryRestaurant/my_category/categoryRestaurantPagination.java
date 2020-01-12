package com.example.sofraapp.data.model.restaurantt.restaurant_item.categoryRestaurant.my_category;

import java.util.List;

public class categoryRestaurantPagination {
    private String firstPageUrl;
    private String path;
    private int perPage;
    private int total;
    private List<CategoryRestaurantDataItem> data;
    private int lastPage;
    private String lastPageUrl;
    private String nextPageUrl;
    private int from;
    private int to;
    private Object prevPageUrl;
    private int currentPage;

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setData(List<CategoryRestaurantDataItem> data) {
        this.data = data;
    }

    public List<CategoryRestaurantDataItem> getData() {
        return data;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public String toString() {
        return
                "categoryRestaurantPagination{" +
                        "first_page_url = '" + firstPageUrl + '\'' +
                        ",path = '" + path + '\'' +
                        ",per_page = '" + perPage + '\'' +
                        ",total = '" + total + '\'' +
                        ",data = '" + data + '\'' +
                        ",last_page = '" + lastPage + '\'' +
                        ",last_page_url = '" + lastPageUrl + '\'' +
                        ",next_page_url = '" + nextPageUrl + '\'' +
                        ",from = '" + from + '\'' +
                        ",to = '" + to + '\'' +
                        ",prev_page_url = '" + prevPageUrl + '\'' +
                        ",current_page = '" + currentPage + '\'' +
                        "}";
    }
}