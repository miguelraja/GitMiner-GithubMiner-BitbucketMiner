package aiss.bitbucketminer.model.raw.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawCommitWrapper {

    @JsonProperty("values")
    private List<RawCommit> values;

    @JsonProperty("pagelen")
    private int pagelen;

    @JsonProperty("next")
    private String next;

    @JsonProperty("values")
    public List<RawCommit> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<RawCommit> values) {
        this.values = values;
    }

    @JsonProperty("pagelen")
    public int getPagelen() {
        return pagelen;
    }

    @JsonProperty("pagelen")
    public void setPagelen(int pagelen) {
        this.pagelen = pagelen;
    }

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "RawCommitWrapper{" +
                "values=" + values +
                ", pagelen=" + pagelen +
                ", next='" + next + '\'' +
                '}';
    }

}
