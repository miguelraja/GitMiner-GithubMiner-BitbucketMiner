package aiss.bitbucketminer.model.miner;


import aiss.bitbucketminer.model.raw.comment.RawComment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerComment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("body")
    private String body;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("author")
    private MinerUser author;

    public MinerComment() {}
    public MinerComment(RawComment rawComment) {
        this.id = String.valueOf(rawComment.getId());
        this.body = rawComment.getContent().getRaw() != null ? rawComment.getContent().getRaw() : "-";
        this.created_at = rawComment.getCreated_on() != null ? rawComment.getCreated_on() : null;
        this.updated_at = rawComment.getUpdated_on() != null ? rawComment.getUpdated_on() : null;
        this.author = new MinerUser(rawComment.getAuthor());
    }
    @JsonProperty("id")
    public String getId() {return id;}

    @JsonProperty("id")
    public void setId(String id) {this.id = id;}

    @JsonProperty("body")
    public String getBody() {return body;}

    @JsonProperty("body")
    public void setBody(String body) {this.body = body;}

    @JsonProperty("created_at")
    public String getCreated_at() {return created_at;}

    @JsonProperty("created_at")
    public void setCreated_at(String created_at) {this.created_at = created_at;}

    @JsonProperty("updated_at")
    public String getUpdated_at() {return updated_at;}

    @JsonProperty("updated_at")
    public void setUpdated_at(String updated_at) {this.updated_at = updated_at;}

    @JsonProperty("author")
    public MinerUser getAuthor() {return author;}

    @JsonProperty("author")
    public void setAuthor(MinerUser author) {this.author = author;}

    @Override
    public String toString() {
        return "MinerComment{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", author=" + author +
                '}';
    }

}
