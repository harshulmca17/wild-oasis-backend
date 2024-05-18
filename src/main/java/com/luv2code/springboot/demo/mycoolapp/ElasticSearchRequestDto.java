package com.luv2code.springboot.demo.mycoolapp;

public class ElasticSearchRequestDto {

    String query;
    Integer pageSize;


    public void setQuery(String query) {
        this.query = query;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return "ElasticSearchRequestDto{" +
                "query='" + query + '\'' +
                ", page=" + pageSize +
                '}';
    }
}
