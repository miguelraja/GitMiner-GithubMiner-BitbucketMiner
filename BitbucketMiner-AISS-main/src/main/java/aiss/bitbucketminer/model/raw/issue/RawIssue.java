package aiss.bitbucketminer.model.raw.issue;


import aiss.bitbucketminer.model.raw.user.RawUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawIssue {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("state")
    private String state;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("created_on")
    private String created_on;

    @JsonProperty("updated_on")
    private String updated_on;

    @JsonProperty("edited_on")
    private String edited_on;

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("repository")
    private RawIssueRepository repository;

    @JsonProperty("reporter")
    private RawUser reporter;

    @JsonProperty("assignee")
    private RawUser assignee;

//    @JsonProperty("milestone")
//    private RawIssueField milestone;
//
//    @JsonProperty("version")
//    private Integer version;
//
//    @JsonProperty("component")
//    private RawIssueField component;

    @JsonProperty("content")
    private RawIssueContent content;

    // Getters and setters

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {this.kind = kind;}

    @JsonProperty("priority")
    public String getPriority() {return priority;}

    @JsonProperty("priority")
    public void setPriority(String priority) {this.priority = priority;}

    @JsonProperty("created_on")
    public String getCreated_on() {return created_on;}

    @JsonProperty("created_on")
    public void setCreated_on(String created_on) {this.created_on = created_on;}

    @JsonProperty("updated_on")
    public String getUpdated_on() {return updated_on;}

    @JsonProperty("updated_on")
    public void setUpdated_on(String updated_on) {this.updated_on = updated_on;}

    @JsonProperty("edited_on")
    public String getEdited_on() {return edited_on;}

    @JsonProperty("edited_on")
    public void setEdited_on(String edited_on) {this.edited_on = edited_on;}

    @JsonProperty("votes")
    public Integer getVotes() {return votes;}

    @JsonProperty("votes")
    public void setVotes(Integer votes) {this.votes = votes;}

    @JsonProperty("repository")
    public RawIssueRepository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(RawIssueRepository repository) {
        this.repository = repository;
    }

    @JsonProperty("reporter")
    public RawUser getReporter() {
        return reporter;
    }

    @JsonProperty("reporter")
    public void setReporter(RawUser reporter) {
        this.reporter = reporter;
    }

    @JsonProperty("assignee")
    public RawUser getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(RawUser assignee) {
        this.assignee = assignee;
    }

//    public RawIssueField getMilestone() {
//        return milestone;
//    }
//
//    public void setMilestone(RawIssueField milestone) {
//        this.milestone = milestone;
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    public RawIssueField getComponent() {
//        return component;
//    }
//
//    public void setComponent(RawIssueField component) {
//        this.component = component;
//    }

    @JsonProperty("content")
    public RawIssueContent getContent() {return content;}

    @JsonProperty("content")
    public void setContent(RawIssueContent content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RawIssue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", kind='" + kind + '\'' +
//                ", priority='" + priority + '\'' +
                ", created_on='" + created_on + '\'' +
                ", updated_on='" + updated_on + '\'' +
                ", edited_on='" + edited_on + '\'' +
                ", votes=" + votes +
//                ", repository=" + repository +
                ", reporter=" + reporter +
                ", assignee=" + assignee +
//                ", milestone=" + milestone +
//                ", version=" + version +
//                ", component=" + component +
                ", content=" + content +
                '}';
    }
}
