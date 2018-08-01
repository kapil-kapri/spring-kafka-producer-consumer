package com.test.kafka.demo.pojo;

/**
 * @author kapilkapri on 31/07/18 - 5:09 PM
 * @project demo
 */

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id"
})
public class Company implements Serializable {

    private final static long serialVersionUID = 277476358526520288L;
    @JsonProperty("id")
    private Integer id;

    public Company() {

    }
    public Company(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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