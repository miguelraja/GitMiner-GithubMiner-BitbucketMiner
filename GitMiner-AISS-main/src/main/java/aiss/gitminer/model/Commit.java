package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Commit")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    @Column(columnDefinition="TEXT")
    private String message;

    @JsonProperty("author_name")
    @NotEmpty(message = "Author name cannot be empty.")
    private String author_name;

    @JsonProperty("author_email")
    private String author_email;

    @JsonProperty("authored_date")
    @NotEmpty(message = "Author date cannot be empty.")
    private String authored_date;

    @JsonProperty("web_url")
    @NotEmpty(message = "URL cannot be empty.")
    private String web_url;

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

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
