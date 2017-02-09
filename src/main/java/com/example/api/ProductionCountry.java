package com.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by LunaFlores on 2/8/17.
 */
@JsonRootName("production_country")
public class ProductionCountry  {


    @JsonProperty("iso_3166_1")
    private String isoCode;
    @JsonProperty("name")
    private String name;


    public String getIsoCode() {
        return isoCode;
    }


    public String getName() {
        return name;
    }
}
