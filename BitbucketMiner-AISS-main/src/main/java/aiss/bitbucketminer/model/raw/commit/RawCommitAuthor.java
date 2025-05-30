package aiss.bitbucketminer.model.raw.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawCommitAuthor {

    @JsonProperty("raw")
    private String raw;

    @JsonProperty("raw")
    public String getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(String raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return "RawCommitAuthor{" +
                "raw='" + raw + '\'' +
                '}';
    }

}
