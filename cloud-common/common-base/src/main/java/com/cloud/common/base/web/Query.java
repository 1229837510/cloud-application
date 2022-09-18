package com.cloud.common.base.web;

import lombok.*;

import java.util.Objects;

/**
 * @author fangcy
 * @date 2022-09-18
 */
@Getter
@Setter
public class Query {
    private Integer current;
    private Integer size;
    private String order;
    public static final String ASC = "_asc";
    public static final String DESC = "_desc";
    public static final String CURRENT = "query.current";
    public static final String SIZE = "query.size";
    public static final String ORDER = "query.order";

    public Query() {
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(current, query.current) &&
                Objects.equals(size, query.size) &&
                Objects.equals(order, query.order);
    }

    protected boolean canEqual(Object other) {
        return other instanceof Query;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, size, order);
    }

    @Override
    public String toString() {
        return "Query{" +
                "current=" + this.getCurrent() +
                ", size=" + this.getSize() +
                ", order='" + this.getOrder() + '\'' +
                '}';
    }
}
