package aiss.githubminer.modelTransformed;

import aiss.githubminer.model.Comment;
import aiss.githubminer.model.User;
import aiss.githubminer.model.UserData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CommentTransformed {


    @JsonProperty("id")
    private String id;

    @JsonProperty("body")
    private String body;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("author")
    private UserTransformed author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public CommentTransformed() {}
    public CommentTransformed( Comment rawComment ) {
        this.id = rawComment.getId().toString();
        this.body = rawComment.getBody();
        this.created_at = rawComment.getCreatedAt();
        this.updated_at = rawComment.getUpdatedAt();
        this.author = rawComment.getUser() != null
                ? new UserTransformed(UserData.toUserData(rawComment.getUser()))
                : null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommentTransformed.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null) ? "<null>" : this.body));
        sb.append(',');
        // sb.append("author");
        // sb.append('=');
        // sb.append(((this.author == null) ? "<null>" : this.author));
        // sb.append(',');
        sb.append("created_at");
        sb.append('=');
        sb.append(((this.created_at == null) ? "<null>" : this.created_at));
        sb.append(',');
        sb.append("updated_at");
        sb.append('=');
        sb.append(((this.updated_at == null) ? "<null>" : this.updated_at));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}