package com.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by LunaFlores on 2/8/17.
 */
@JsonRootName("production_company")
public class ProductionCompany  {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;


    public int getId() {
        return id;
    }


    public void setId(int id) {


        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "name='" + name + '\'' +
            ", id=" + id +
            '}';
    }
}
