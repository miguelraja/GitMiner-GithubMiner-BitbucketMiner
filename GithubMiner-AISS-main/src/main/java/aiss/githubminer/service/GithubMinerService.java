package aiss.githubminer.service;

import aiss.githubminer.model.*; // import all classes
import aiss.githubminer.modelTransformed.CommitTransformed;
import aiss.githubminer.modelTransformed.IssueTransformed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubMinerService {

    @Autowired
    RestTemplate restTemplate;

    @Value("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")
    private String githubToken;

    @Value("${githubminer.uri}")
    private String githubApiUri;

    private HttpHeaders createHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubToken);
        headers.set("Accept", "application/vnd.github+json");
        return  headers;
    }

    public Repos getGitHubRepo(String owner, String repoName, int maxPages)
    {
        try{
            String uri = githubApiUri + owner + "/" + repoName + "?per_page=" + maxPages;
            // Repos repos = restTemplate.getForObject(uri, Repos.class);

            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            // HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            Repos repos = restTemplate.exchange(uri, HttpMethod.GET, entity, Repos.class).getBody();

            return repos;
        }
        catch (Exception e) {
            return null;
        }

    }

    public List<CommitOuter> getGitHubCommits(String owner, String repoName, int sinceCommits, int maxPages)
    {
        try{
            String uri = githubApiUri + owner + "/" + repoName + "/commits?since=" + LocalDate.now().minusDays(sinceCommits)+"&per_page=" + maxPages;
            // CommitOuter[] githubcommits = restTemplate.getForObject(uri, CommitOuter[].class);

            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            // HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            CommitOuter[] githubcommits = restTemplate.exchange(uri, HttpMethod.GET, entity, CommitOuter[].class).getBody();

            return Arrays.stream(githubcommits).toList();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Issue> getGitHubIssues(String owner, String repoName, int sinceIssues, int maxPages)
    {
        try{
            String uri = githubApiUri + owner + "/" + repoName + "/issues?since=" + LocalDate.now().minusDays(sinceIssues)+"&per_page=" + maxPages;
            // IssueOuter[] githubissues = restTemplate.getForObject(uri, IssueOuter[].class);

            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            // HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            Issue[] githubissues = restTemplate.exchange(uri, HttpMethod.GET, entity, Issue[].class).getBody();

            return Arrays.stream(githubissues).toList();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Comment> getGitHubComments(String owner, String repoName)
    {
        try{

            String uri = githubApiUri + owner + "/" + repoName + "/issues/comments";

            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            // HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            Comment[] githubComments = restTemplate.exchange(uri, HttpMethod.GET, entity, Comment[].class).getBody();

            return Arrays.stream(githubComments).toList();
        }
        catch (Exception e) {
            return null;
        }
    }
}
