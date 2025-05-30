package aiss.bitbucketminer.model.raw.comment;

import aiss.bitbucketminer.model.raw.commit.RawCommit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawCommentWrapper {

    @JsonProperty("values")
    private List<RawComment> values;

    @JsonProperty("pagelen")
    private int pagelen;

    @JsonProperty("size")
    private int size;

    @JsonProperty("page")
    private int page;

    @JsonProperty("values")
    public List<RawComment> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<RawComment> values) {
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

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(int size) {
        this.size = size;
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

}
