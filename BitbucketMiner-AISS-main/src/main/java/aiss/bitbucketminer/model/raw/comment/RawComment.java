package aiss.bitbucketminer.model.raw.comment;

import aiss.bitbucketminer.model.raw.user.RawUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawComment {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("created_on")
    private String created_on;

    @JsonProperty("updated_on")
    private String updated_on;

    @JsonProperty("content")
    private RawCommentContent content;

    @JsonProperty("user")
    private RawUser author;

    // Getters & Setters

    @JsonProperty("id")
    public Integer getId() {return id;}

    @JsonProperty("id")
    public void setId(Integer id) {this.id = id;}

    @JsonProperty("created_on")
    public String getCreated_on() {
        return created_on;
    }

    @JsonProperty("created_on")
    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    @JsonProperty("updated_on")
    public String getUpdated_on() {
        return updated_on;
    }

    @JsonProperty("updated_on")
    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    @JsonProperty("content")
    public RawCommentContent getContent() {return content;}

    @JsonProperty("content")
    public void setContent(RawCommentContent content) {this.content = content;}

    @JsonProperty("user")
    public RawUser getAuthor() {return author;}

    @JsonProperty("user")
    public void setAuthor(RawUser author) {this.author = author;}

    @Override
    public String toString() {
        return "RawComment{" +
                "id=" + id +
                ", created_on='" + created_on + '\'' +
                ", updated_on='" + updated_on + '\'' +
                ", content=" + content +
                ", author=" + author +
                '}';
    }

}
