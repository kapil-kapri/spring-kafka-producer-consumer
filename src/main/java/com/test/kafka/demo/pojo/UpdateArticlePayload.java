package com.test.kafka.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kapilkapri on 31/07/18 - 9:29 PM
 * @project demo
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "total_quantity",
        "price"
})
public class UpdateArticlePayload implements Serializable {

    private final static long serialVersionUID = -7446439234448668396L;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("total_quantity")
    private Integer totalQuantity;
    @JsonProperty("price")
    private ArticlePrice price;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public UpdateArticlePayload() {
    }

    /**
     * @param uid
     * @param totalQuantity
     * @param price
     */
    public UpdateArticlePayload(String uid, Integer totalQuantity, ArticlePrice price) {
        super();
        this.uid = uid;
        this.totalQuantity = totalQuantity;
        this.price = price;
    }

    public UpdateArticlePayload(String uid, Integer totalQuantity) {
        super();
        this.uid = uid;
        this.totalQuantity = totalQuantity;
    }
    public UpdateArticlePayload(String uid,  ArticlePrice price) {
        super();
        this.uid = uid;
        this.price = price;
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

    @JsonProperty("price")
    public ArticlePrice getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(ArticlePrice price) {
        this.price = price;
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