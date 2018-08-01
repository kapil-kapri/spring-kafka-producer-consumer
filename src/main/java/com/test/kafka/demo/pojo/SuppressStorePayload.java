package com.test.kafka.demo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author kapilkapri on 31/07/18 - 5:33 PM
 * @project demo
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "brand_id",
        "store"
})
public class SuppressStorePayload implements Serializable {

    private final static long serialVersionUID = 1906295994931522633L;
    @JsonProperty("brand_id")
    private Integer brandId;
    @JsonProperty("store")
    private List<String> store = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public SuppressStorePayload() {
    }

    /**
     * @param store
     * @param brandId
     */
    public SuppressStorePayload(Integer brandId, List<String> store) {
        super();
        this.brandId = brandId;
        this.store = store;
    }

    @JsonProperty("brand_id")
    public Integer getBrandId() {
        return brandId;
    }

    @JsonProperty("brand_id")
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @JsonProperty("store")
    public List<String> getStore() {
        return store;
    }

    @JsonProperty("store")
    public void setStore(List<String> store) {
        this.store = store;
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