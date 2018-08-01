package com.test.kafka.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kapilkapri on 31/07/18 - 5:35 PM
 * @project demo
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "total_quantity"
})
public class SuppressArticlePayload implements Serializable {

    private final static long serialVersionUID = 9014156503086593345L;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("total_quantity")
    private Integer totalQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public SuppressArticlePayload() {
    }

    /**
     * @param uid
     * @param totalQuantity
     */
    public SuppressArticlePayload(String uid, Integer totalQuantity) {
        super();
        this.uid = uid;
        this.totalQuantity = totalQuantity;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("total_quantity")
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    @JsonProperty("total_quantity")
    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
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