package aiss.githubminer.modelTransformed;

import aiss.githubminer.model.Comment;
import aiss.githubminer.model.Issue;
import aiss.githubminer.model.User;
import aiss.githubminer.model.UserData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class IssueTransformed {

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

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("author")
    private UserTransformed author;

    @JsonProperty("assignee")
    private UserTransformed assignee;

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("comments")
    private List<CommentTransformed> comments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getclosed_at() {
        return closed_at;
    }

    public void setclosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public UserTransformed getAuthor() {
        return author;
    }

    public void setAuthor(UserTransformed author) {
        this.author = author;
    }

    public UserTransformed getAssignee() {
        return assignee;
    }

    public void setAssignee(UserTransformed assignee) {
        this.assignee = assignee;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<CommentTransformed> getComments() {
        return comments;
    }

    public void setComments(List<CommentTransformed> comments) {
        this.comments = comments;
    }

    public IssueTransformed() {}
    public IssueTransformed(Issue rawIssue, List<Comment> rawComments) {
        this.id = rawIssue.getId().toString();
        this.title = rawIssue.getTitle();
        this.description = rawIssue.getBody();
        this.state = rawIssue.getState();
        this.created_at = rawIssue.getCreatedAt();
        this.updated_at = rawIssue.getUpdatedAt();
        this.closed_at = (rawIssue.getClosedAt() == null) ? "Not Closed" : rawIssue.getClosedAt().toString();

        this.labels = new ArrayList<>();

        List<Object> l = rawIssue.getLabels();
        for (Object o : l) {
            this.labels.add(o.toString());
        }

        this.votes = rawIssue.getReactions().getTotalCount();

        this.author = rawIssue.getUser() != null
                ? new UserTransformed(UserData.toUserData(rawIssue.getUser()))
                : null;

        if (rawIssue.getAssignee() instanceof Map) {
            // Map to UserData
            ObjectMapper objectMapper = new ObjectMapper();
            this.assignee = new UserTransformed(objectMapper.convertValue(rawIssue.getAssignee(), UserData.class));
        } else {
            this.assignee = rawIssue.getAssignee() != null ? new UserTransformed((UserData) rawIssue.getAssignee()) : null;
        }

        this.comments = (rawComments != null) ? rawComments.stream().map(CommentTransformed::new).toList() : new ArrayList<>();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(IssueTransformed.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null) ? "<null>" : this.state));
        sb.append(',');
        sb.append("created_at");
        sb.append('=');
        sb.append(((this.created_at == null) ? "<null>" : this.created_at));
        sb.append(',');
        sb.append("updated_at");
        sb.append('=');
        sb.append(((this.updated_at == null) ? "<null>" : this.updated_at));
        sb.append(',');
        sb.append("closed_at");
        sb.append('=');
        sb.append(((this.closed_at == null) ? "<null>" : this.closed_at));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null) ? "<null>" : this.labels));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("assignee");
        sb.append('=');
        sb.append(((this.assignee == null) ? "<null>" : this.assignee));
        sb.append(',');
        /*sb.append("upvotes");
        sb.append('=');
        sb.append(((this.upvotes == null) ? "<null>" : this.upvotes));
        sb.append(',');
        sb.append("downvotes");
        sb.append('=');
        sb.append(((this.downvotes == null) ? "<null>" : this.downvotes));
        sb.append(',');*/
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null) ? "<null>" : this.comments));
        sb.append(',');

        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }



}
