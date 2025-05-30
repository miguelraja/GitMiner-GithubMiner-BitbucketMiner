package aiss.githubminer.modelTransformed;

import aiss.githubminer.model.CommitOuter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitTransformed {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    private String message;

    @JsonProperty("author_name")
    private String author_name;

    @JsonProperty("author_email")
    private String author_email;

    @JsonProperty("authored_date")
    private String authored_date;

    @JsonProperty("web_url")
    private String web_url;

    public CommitTransformed() {}
    public CommitTransformed( CommitOuter commit ){
        this.id = commit.getSha();
        String[] text = parseMessage(commit.getCommit().getMessage());
        this.title = text[0];
        this.message = text[1];
        this.author_name = commit.getCommit().getAuthor().getName();
        this.author_email = commit.getCommit().getAuthor().getEmail();
        this.authored_date = commit.getCommit().getAuthor().getDate();
        this.web_url = commit.getCommit().getUrl();
    }

    private String[] parseMessage(String message) {
        String[] parts = message.split("\n\n", 2);
        String title = parts[0];
        String body = parts.length > 1 ? parts[1] : "";

        return new String[] { title.trim(), body.trim() };
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getauthor_name() {
        return author_name;
    }

    public void setauthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getauthor_email() {
        return author_email;
    }

    public void setauthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getauthored_date() {
        return authored_date;
    }

    public void setauthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    public String getweb_url() {
        return web_url;
    }

    public void setweb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommitTransformed.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        sb.append("author_name");
        sb.append('=');
        sb.append(((this.author_name == null) ? "<null>" : this.author_name));
        sb.append(',');
        sb.append("author_email");
        sb.append('=');
        sb.append(((this.author_email == null) ? "<null>" : this.author_email));
        sb.append(',');
        sb.append("authored_date");
        sb.append('=');
        sb.append(((this.authored_date == null) ? "<null>" : this.authored_date));
        sb.append(',');
        sb.append("web_url");
        sb.append('=');
        sb.append(((this.web_url == null) ? "<null>" : this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
