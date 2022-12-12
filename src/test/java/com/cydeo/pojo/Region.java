package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Data  //has getter,setter,tostring
public class Region {

    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
    private List<Link> links;


}
