package aiss.bitbucketminer.model.raw.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawIssueWrapper {
    @JsonProperty("size")
    private Integer size;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("pagelen")
    private Integer pagelen;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("values")
    private List<RawIssue> values;


    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }
    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }
    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }
    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }
    @JsonProperty("pagelen")
    public Integer getPagelen() {
        return pagelen;
    }
    @JsonProperty("pagelen")
    public void setPagelen(Integer pagelen) {
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
    @JsonProperty("previous")
    public String getPrevious() {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @JsonProperty("values")
    public List<RawIssue> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<RawIssue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "RawIssueWrapper{" +
                "size=" + size +
                ", page=" + page +
                ", pagelen=" + pagelen +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", values=" + values +
                '}';
    }
}
