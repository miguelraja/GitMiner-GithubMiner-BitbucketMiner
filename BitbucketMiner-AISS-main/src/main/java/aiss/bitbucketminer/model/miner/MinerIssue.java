package aiss.bitbucketminer.model.miner;


import aiss.bitbucketminer.model.raw.comment.RawComment;
import aiss.bitbucketminer.model.raw.issue.RawIssue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerIssue {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("closed_at")
    private String closed_at;

    @JsonProperty("author")
    private MinerUser author;

    @JsonProperty("assignee")
    private MinerUser assignee;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("comments")
    private List<MinerComment> comments;

    public MinerIssue() {}
    public MinerIssue(RawIssue rawIssue, List<RawComment> rawComments) {
        if (rawIssue.getId() != null) {
            this.id = String.valueOf(rawIssue.getId());
        }
        this.title = rawIssue.getTitle();
        this.description = rawIssue.getContent() != null ? rawIssue.getContent().getRaw() : null;
        this.state = rawIssue.getState();
        this.created_at = rawIssue.getCreated_on();
        this.updated_at = rawIssue.getUpdated_on();

        if("closed".equalsIgnoreCase(state)) {
            this.closed_at = rawIssue.getUpdated_on();
        } else{
            this.closed_at = null;
        }

        this.author = rawIssue.getReporter() != null ? new MinerUser(rawIssue.getReporter()) : null;
        this.assignee = rawIssue.getAssignee() != null ? new MinerUser(rawIssue.getAssignee()) : null;
        this.comments = this.parseComments(rawComments);

        this.labels = new ArrayList<>();
        if(rawIssue.getKind() != null) {this.labels.add(rawIssue.getKind());}
        if(rawIssue.getPriority() != null) {this.labels.add(rawIssue.getPriority());}

        this.votes = rawIssue.getVotes();
    }

    public List<MinerComment> parseComments (List<RawComment> rawComments) {
        return rawComments.stream().map(MinerComment::new).toList();
    }

    //Getters and Setters

    @JsonProperty("id")
    public String getId() {return id;}

    @JsonProperty("id")
    public void setId(String id) {this.id = id;}

    @JsonProperty("title")
    public String getTitle() {return title;}

    @JsonProperty("title")
    public void setTitle(String title) {this.title = title;}

    @JsonProperty("description")
    public String getDescription() {return description;}

    @JsonProperty("description")
    public void setDescription(String description) {this.description = description;}

    @JsonProperty("state")
    public String getState() {return state;}

    @JsonProperty("state")
    public void setState(String state) {this.state = state;}

    @JsonProperty("created_at")
    public String getCreated_at() {return created_at;}

    @JsonProperty("created_at")
    public void setCreated_at(String created_at) {this.created_at = created_at;}

    @JsonProperty("updated_at")
    public String getUpdated_at() {return updated_at;}

    @JsonProperty("updated_at")
    public void setUpdated_at(String updated_at) {this.updated_at = updated_at;}

    @JsonProperty("closed_at")
    public String getClosed_at() {return closed_at;}

    @JsonProperty("closed_at")
    public void setClosed_at(String closed_at) {this.closed_at = closed_at;}

    @JsonProperty("author")
    public MinerUser getAuthor() {return author;}

    @JsonProperty("author")
    public void setAuthor(MinerUser author) {this.author = author;}

    @JsonProperty("assignee")
    public MinerUser getAssignee() {return assignee;}

    @JsonProperty("assignee")
    public void setAssignee(MinerUser assignee) {this.assignee = assignee;}

    @JsonProperty("labels")
    public List<String> getLabels() {return labels;}

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {this.labels = labels;}

    @JsonProperty("votes")
    public int getVotes() {return votes;}

    @JsonProperty("votes")
    public void setVotes(int votes) {this.votes = votes;}

    @JsonProperty("comments")
    public List<MinerComment> getComments() {return comments;}

    @JsonProperty("comments")
    public void setComments(List<MinerComment> comments) {this.comments = comments;}

//    @JsonProperty("projectId")
//    public String getProjectId() {return projectId;}
//
//    @JsonProperty("projectId")
//    public void setProjectId(String projectId) {this.projectId = projectId;}

    @Override
    public String toString() {
        return "MinerIssue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", closed_at='" + closed_at + '\'' +
//                ", author=" + author +
//                ", assignee=" + assignee +
                ", labels=" + labels +
                ", votes=" + votes +
                ", comments=" + comments +
//                ", projectId='" + projectId + '\'' +
                '}';
    }
}
