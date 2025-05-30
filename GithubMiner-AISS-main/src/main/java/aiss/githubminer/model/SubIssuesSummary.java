
package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubIssuesSummary {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("completed")
    private Integer completed;
    @JsonProperty("percent_completed")
    private Integer percentCompleted;

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("completed")
    public Integer getCompleted() {
        return completed;
    }

    @JsonProperty("completed")
    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    @JsonProperty("percent_completed")
    public Integer getPercentCompleted() {
        return percentCompleted;
    }

    @JsonProperty("percent_completed")
    public void setPercentCompleted(Integer percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubIssuesSummary.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("total");
        sb.append('=');
        sb.append(((this.total == null)?"<null>":this.total));
        sb.append(',');
        sb.append("completed");
        sb.append('=');
        sb.append(((this.completed == null)?"<null>":this.completed));
        sb.append(',');
        sb.append("percentCompleted");
        sb.append('=');
        sb.append(((this.percentCompleted == null)?"<null>":this.percentCompleted));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
