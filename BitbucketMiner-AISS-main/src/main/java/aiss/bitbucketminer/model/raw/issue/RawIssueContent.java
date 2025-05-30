package aiss.bitbucketminer.model.raw.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RawIssueContent {

    @JsonProperty("raw")
    private String raw;

//    @JsonProperty("markup")
//    private String markup;
//
//    @JsonProperty("html")
//    private String html;

    @JsonProperty("raw")
    public String getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(String raw) {
        this.raw = raw;
    }

//    public String getMarkup() {
//        return markup;
//    }
//
//    public void setMarkup(String markup) {
//        this.markup = markup;
//    }
//
//    public String getHtml() {
//        return html;
//    }
//
//    public void setHtml(String html) {
//        this.html = html;
//    }

    @Override
    public String toString() {
        return "RawIssueContent{" +
                "raw='" + raw + '\'' +
//                ", markup='" + markup + '\'' +
//                ", html='" + html + '\'' +
                '}';
    }
}
