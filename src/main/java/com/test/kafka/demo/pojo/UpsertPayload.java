package com.test.kafka.demo.pojo;

/**
 * @author kapilkapri on 31/07/18 - 5:01 PM
 * @project demo
 */

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "company",
        "brand",
        "store",
        "fynd_item_code",
        "fynd_article_code",
        "size",
        "identifier",
        "raw_meta",
        "price",
        "total_quantity",
        "added_on_store"
})
public class UpsertPayload implements Serializable {

    private final static long serialVersionUID = 1336327314514163948L;

    @JsonProperty("uid")
    private String uid;
    @JsonProperty("company")
    private Company company;
    @JsonProperty("brand")
    private Brand brand;
    @JsonProperty("store")
    private Store store;
    @JsonProperty("fynd_item_code")
    private String fyndItemCode;
    @JsonProperty("fynd_article_code")
    private String fyndArticleCode;
    @JsonProperty("size")
    private String size;
    @JsonProperty("identifier")
    private Map<String, Object> identifier;
    @JsonProperty("raw_meta")
    private Map<String, Object> rawMeta;
    @JsonProperty("price")
    private ArticlePrice articlePrice;
    @JsonProperty("total_quantity")
    private Integer totalQuantity;
    @JsonProperty("added_on_store")
    private String addedOnStore;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public UpsertPayload() {}
    public UpsertPayload(String uid, Company company, Brand brand, Store store, String fyndItemCode,
                         String fyndArticleCode, String size, Map<String, Object> identifier, Map<String, Object> rawMeta,
                         ArticlePrice articlePrice, Integer totalQuantity, String addedOnStore) {
        this.uid = uid;
        this.company = company;
        this.brand = brand;
        this.store = store;
        this.fyndItemCode = fyndItemCode;
        this.fyndArticleCode = fyndArticleCode;
        this.size = size;
        this.identifier = identifier;
        this.rawMeta = rawMeta;
        this.articlePrice = articlePrice;
        this.totalQuantity = totalQuantity;
        this.addedOnStore = addedOnStore;
    }
    public UpsertPayload(String uid, Company company, Brand brand, Store store, String fyndItemCode,
                         String size, Map<String, Object> identifier, Map<String, Object> rawMeta,
                         ArticlePrice articlePrice, Integer totalQuantity, String addedOnStore) {
        this.uid = uid;
        this.company = company;
        this.brand = brand;
        this.store = store;
        this.fyndItemCode = fyndItemCode;
        this.size = size;
        this.fyndArticleCode = generateArticleCode();
        this.identifier = identifier;
        this.rawMeta = rawMeta;
        this.articlePrice = articlePrice;
        this.totalQuantity = totalQuantity;
        this.addedOnStore = addedOnStore;
    }

    private String generateArticleCode() {
        return getFyndItemCode()+"_"+getSize();
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("company")
    public Company getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonProperty("brand")
    public Brand getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @JsonProperty("store")
    public Store getStore() {
        return store;
    }

    @JsonProperty("store")
    public void setStore(Store store) {
        this.store = store;
    }

    @JsonProperty("fynd_item_code")
    public String getFyndItemCode() {
        return fyndItemCode;
    }

    @JsonProperty("fynd_item_code")
    public void setFyndItemCode(String fyndItemCode) {
        this.fyndItemCode = fyndItemCode;
    }

    @JsonProperty("fynd_article_code")
    public String getFyndArticleCode() {
        return fyndArticleCode;
    }

    @JsonProperty("fynd_article_code")
    public void setFyndArticleCode(String fyndArticleCode) {
        this.fyndArticleCode = fyndArticleCode;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("identifier")
    public Map<String, Object> getIdentifier() {
        return identifier;
    }

    @JsonProperty("identifier")
    public void setIdentifier(Map<String, Object> identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("raw_meta")
    public Map<String, Object> getRawMeta() {
        return rawMeta;
    }

    @JsonProperty("raw_meta")
    public void setRawMeta(Map<String, Object> rawMeta) {
        this.rawMeta = rawMeta;
    }

    @JsonProperty("price")
    public ArticlePrice getPrice() {
        return articlePrice;
    }

    @JsonProperty("price")
    public void setPrice(ArticlePrice price) {
        this.articlePrice = price;
    }

    @JsonProperty("total_quantity")
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    @JsonProperty("total_quantity")
    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @JsonProperty("added_on_store")
    public String getAddedOnStore() {
        return addedOnStore;
    }

    @JsonProperty("added_on_store")
    public void setAddedOnStore(String addedOnStore) {
        this.addedOnStore = addedOnStore;
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
