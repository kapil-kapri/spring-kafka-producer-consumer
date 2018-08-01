package com.test.kafka.demo.pojo;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kapilkapri on 23/07/18 - 12:07 PM
 * @project interfacesc
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "payload",
        "meta"
})
public class ProducerPayload<T> implements Serializable {

    private final static long serialVersionUID = -542334123823862429L;
    @JsonProperty("payload")
    private List<T> payload = null;
    @JsonProperty("meta")
    private MetaData meta;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ProducerPayload() {}

    public ProducerPayload(List<T> payload, MetaData meta) {
        this.payload = payload;
        this.meta = meta;
    }


    public ProducerPayload(List<T> payload,
                           String actionType) {
        super();
        this.payload = payload;
        this.meta = new MetaData(actionType);
    }

    @JsonProperty("payload")
    public List<T> getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    @JsonProperty("meta")
    public MetaData getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(MetaData meta) {
        this.meta = meta;
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
