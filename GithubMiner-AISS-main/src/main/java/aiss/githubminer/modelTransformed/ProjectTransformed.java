package aiss.githubminer.modelTransformed;

import aiss.githubminer.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ProjectTransformed {


    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("web_url")
    public String webUrl;

    @JsonProperty("commits")
    private List<CommitTransformed> commits;

    @JsonProperty("issues")
    private List<IssueTransformed> issues;

    public ProjectTransformed() {}
    public ProjectTransformed(
            Repos rawProject,
            List<CommitOuter> rawCommits,
            List<Issue> rawIssues,
            List<Comment> rawComments
    ) {
        this.id = rawProject.getId().toString();
        this.name = rawProject.getName();
        this.webUrl = rawProject.getUrl();
        this.commits = this.parseCommits(rawCommits);
        this.issues = this.parseIssues(rawIssues, rawComments);
    }

    private List<CommitTransformed> parseCommits(List<CommitOuter> commits ){
        return commits.stream().map(CommitTransformed::new).collect(Collectors.toList());
    }

    private List<IssueTransformed> parseIssues(List<Issue> issues, List<Comment> comments){
        List<IssueTransformed> res = new ArrayList<IssueTransformed>();
        Map<String, List<Comment>> aux = new HashMap<>();
        for (Comment c: comments) {
            String issueUrl = c.getIssueUrl();
            String[] parts = issueUrl.split("/");
            String issueId = parts[parts.length - 1];
            if(aux.containsKey(issueId)){
                aux.get(issueId).add(c);
            } else {
                aux.put(issueId, new ArrayList<>(List.of(c)));
            }
        }
        for (Issue i: issues){
            res.add(
                    new IssueTransformed(
                            i,
                            aux.get(i.getId().toString())
                    )
            );
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProjectTransformed.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
