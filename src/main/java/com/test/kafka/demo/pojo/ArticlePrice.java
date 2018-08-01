package com.test.kafka.demo.pojo;

/**
 * @author kapilkapri on 31/07/18 - 5:15 PM
 * @project demo
 */

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "actual_marked",
        "actual_effective",
        "currency",
        "transfer"
})
public class ArticlePrice implements Serializable {

    private final static long serialVersionUID = -2382800307223047747L;
    @JsonProperty("actual_marked")
    private Double actualMarked;
    @JsonProperty("actual_effective")
    private Double actualEffective;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("transfer")
    private Double transfer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ArticlePrice() {
    }

    /**
     *
     * @param transfer
     * @param actualMarked
     * @param actualEffective
     * @param currency
     */
    public ArticlePrice(Double actualMarked, Double actualEffective, String currency, Double transfer) {
        super();
        this.actualMarked = actualMarked;
        this.actualEffective = actualEffective;
        this.currency = currency;
        this.transfer = transfer;
    }
    public ArticlePrice(Double actualMarked, Double actualEffective, Double transfer) {
        this(actualMarked,actualEffective,"INR",transfer);
    }
    public ArticlePrice(Double actualMarked, Double actualEffective) {
        this(actualMarked,actualEffective,"INR",0.0);
    }

    @JsonProperty("actual_marked")
    public Double getActualMarked() {
        return actualMarked;
    }

    @JsonProperty("actual_marked")
    public void setActualMarked(Double actualMarked) {
        this.actualMarked = actualMarked;
    }

    @JsonProperty("actual_effective")
    public Double getActualEffective() {
        return actualEffective;
    }

    @JsonProperty("actual_effective")
    public void setActualEffective(Double actualEffective) {
        this.actualEffective = actualEffective;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("transfer")
    public Double getTransfer() {
        return transfer;
    }

    @JsonProperty("transfer")
    public void setTransfer(Double transfer) {
        this.transfer = transfer;
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