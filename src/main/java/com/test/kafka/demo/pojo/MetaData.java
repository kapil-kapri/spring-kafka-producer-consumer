package com.test.kafka.demo.pojo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.uuid.Generators;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kapilkapri on 31/07/18 - 5:27 PM
 * @project demo
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "job_type",
        "action",
        "trace"
})
public class MetaData implements Serializable {

    private final static long serialVersionUID = -4710442481039261884L;
    @JsonProperty("job_type")
    private String jobType;
    @JsonProperty("action")
    private String action;
    @JsonProperty("trace")
    private List<String> trace = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public MetaData() {
    }

    public MetaData(String action) {
        this.action = action;
        this.jobType = "article";
        this.trace = generatetrackId();
    }

    public MetaData(String action, String jobType) {
        this.action = action;
        this.jobType = jobType;
        this.trace = generatetrackId();
    }

    @JsonProperty("job_type")
    public String getJobType() {
        return jobType;
    }

    @JsonProperty("job_type")
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("trace")
    public List<String> getTrace() {
        return trace;
    }

    @JsonProperty("trace")
    public void setTrace(List<String> trace) {
        this.trace = trace;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<String> generatetrackId() {
        return Arrays.asList("inventory." + Generators.timeBasedGenerator().generate().toString());
    }

    public enum ActionType {
        UPSERT("upsert"),
        UPDATE("update"),
        SUPPRESS("suppress"),
        BRAND_STORE_SUPPRESS("brand_store_suppress");

        private final String text;

        ActionType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}

