package aiss.bitbucketminer.model.raw.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawCommit {

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("date")
    private String date;

    @JsonProperty("author")
    private RawCommitAuthor author;

    @JsonProperty("message")
    private String message;

    @JsonProperty("repository")
    private RawCommitRepository repository;

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("author")
    public RawCommitAuthor getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(RawCommitAuthor author) {
        this.author = author;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("repository")
    public RawCommitRepository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(RawCommitRepository repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "RawCommit{" +
                "hash='" + hash + '\'' +
                ", date='" + date + '\'' +
                ", author=" + author +
                ", message='" + message + '\'' +
                ", repository=" + repository +
                '}';
    }

}
