package com.test.kafka.demo.pojo;

/**
 * @author kapilkapri on 31/07/18 - 5:11 PM
 * @project demo
 */

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "id"
})
public class Brand implements Serializable {

    private final static long serialVersionUID = -23041861612568205L;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private Integer id;
    public Brand(){}
    public Brand(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}