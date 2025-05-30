package aiss.bitbucketminer.model.raw.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawCommitRepository {

    @JsonProperty("full_name")
    public String full_name;

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
        return "RawCommitRepository{" +
                "full_name='" + full_name + '\'' +
                '}';
    }

}
