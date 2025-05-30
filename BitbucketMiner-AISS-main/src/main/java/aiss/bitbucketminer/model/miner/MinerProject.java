package aiss.bitbucketminer.model.miner;

import aiss.bitbucketminer.model.raw.comment.RawComment;
import aiss.bitbucketminer.model.raw.commit.RawCommit;
import aiss.bitbucketminer.model.raw.issue.RawIssue;
import aiss.bitbucketminer.model.raw.project.RawProject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerProject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("web_url")
    private String web_url;

    @JsonProperty("commits")
    private List<MinerCommit> commits;

    @JsonProperty("issues")
    private List<MinerIssue> issues;

    public MinerProject() {}
    public MinerProject(
            RawProject rawProject,
            List<RawCommit> rawCommits,
            List<RawIssue> rawIssues,
            Map<String, List<RawComment>> rawComments
    ) {
        this.id = rawProject.getUuid();
        this.name = rawProject.getName();
        this.web_url = rawProject.getWebsite();
        this.commits = this.parseCommits(rawCommits);
        this.issues = this.parseIssues(rawIssues, rawComments);
    }

    public List<MinerCommit> parseCommits (List<RawCommit> rawCommits) {
        return rawCommits.stream().map(MinerCommit::new).toList();
    }

    public List<MinerIssue> parseIssues (List<RawIssue> rawIssues, Map<String, List<RawComment>> rawComments) {
        return rawIssues.stream().map(p -> new MinerIssue(p, rawComments.get(p.getId().toString()))).toList();
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_url")
    public String getWeb_url() {
        return web_url;
    }

    @JsonProperty("web_url")
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @JsonProperty("commits")
    public List<MinerCommit> getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(List<MinerCommit> commits) {
        this.commits = commits;
    }

    @JsonProperty("issues")
    public List<MinerIssue> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(List<MinerIssue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "MinerProject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", commits=" + commits +
                '}';
    }

}
