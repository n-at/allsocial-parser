package ru.bserg.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by SBoichenko on 25.04.2016.
 */
public class Response {

    Integer total_count;

    List<Entity> entity;

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public List<Entity> getEntity() {
        return entity;
    }

    public void setEntity(List<Entity> entity) {
        this.entity = entity;
    }
}
