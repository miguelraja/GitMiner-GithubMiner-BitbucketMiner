package aiss.bitbucketminer.model.raw.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawIssueRepository {

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("full_name")
    public String getFull_name() {
        return full_name;
    }

    @JsonProperty("full_name")
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "RawIssueRepository{" +
                "full_name='" + full_name + '\'' +
                '}';
    }
}
