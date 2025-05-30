package aiss.bitbucketminer.model.miner;

import aiss.bitbucketminer.model.raw.commit.RawCommit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerCommit {

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

    public MinerCommit() {}
    public MinerCommit(
            RawCommit rawCommit
    ) {
        this.id = rawCommit.getHash();

        String[] text = this.parseMessage(rawCommit.getMessage());
        this.title = text[0];
        this.message = text[1];

        String[] author = this.parseAuthor(rawCommit.getAuthor().getRaw());
        this.author_name = author[0];
        this.author_email = author[1];

        this.authored_date = rawCommit.getDate();
        this.web_url = "https://bitbucket.org/" + rawCommit.getRepository().getFull_name() + "/commits/" + rawCommit.getHash();
    }

    private String[] parseMessage (String message) {
        String[] parts = message.split("\n\n", 2);
        String title = parts[0];
        String body = parts.length > 1 ? parts[1] : null;

        return new String[] { title.trim(), body != null ? body.trim() : null };
    }

    private String[] parseAuthor (String author) {
        String name = author.split(" <")[0];
        String email = author.substring(author.indexOf("<") + 1, author.indexOf(">"));

        return new String[] { name.trim(), email.trim() };
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("author_name")
    public String getAuthor_name() {
        return author_name;
    }

    @JsonProperty("author_name")
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    @JsonProperty("author_email")
    public String getAuthor_email() {
        return author_email;
    }

    @JsonProperty("author_email")
    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    @JsonProperty("authored_date")
    public String getAuthored_date() {
        return authored_date;
    }

    @JsonProperty("authored_date")
    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    @JsonProperty("web_url")
    public String getWeb_url() {
        return web_url;
    }

    @JsonProperty("web_url")
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "MinerCommit{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", authored_date='" + authored_date + '\'' +
                ", web_url='" + web_url + '\'' +
                '}';
    }

}
