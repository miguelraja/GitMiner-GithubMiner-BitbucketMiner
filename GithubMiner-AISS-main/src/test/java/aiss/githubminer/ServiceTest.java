package aiss.githubminer;

import aiss.githubminer.model.*;
import aiss.githubminer.service.GithubMinerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ServiceTest {

    @Autowired
    GithubMinerService githubMinerService;

    @Value("spring-projects")
    private String ownerLogin;

    @Value("spring-framework")
    private String repoName;

    @Value("1")
    private int maxPages;

    @Value("1")
    private int sinceCommits;

    @Value("10")
    private int sinceIssues;

    @Test
    @DisplayName("Fetch commits from GitHubMiner")
    void fetchCommitsFromGitHubMiner() {
        List<CommitOuter> commits = githubMinerService.getGitHubCommits(ownerLogin, repoName, sinceCommits, maxPages);
        System.out.println(commits);
        assertNotNull(commits);
        commits.forEach(comment -> assertNotNull(comment.getAuthor()));
    }

    @Test
    @DisplayName("Fetch issues from GitHubMiner")
    void fetchIssuesFromGitHubMiner() {
        List<Issue> issues = githubMinerService.getGitHubIssues(ownerLogin, repoName, sinceIssues, maxPages);
        System.out.println(issues);
        assertNotNull(issues);
        issues.forEach(issue -> assertNotNull(issue.getId()));
    }

    @Test
    @DisplayName("Fetch projects from GitHubMiner")
    void fetchProjectsFromGitHubMiner() {
        Repos project = githubMinerService.getGitHubRepo(ownerLogin, repoName,maxPages);
        System.out.println(project);
        assertNotNull(project);
        assertNotNull(project.getId());
    }

    @Test
    @DisplayName("Fetch comments from GitHubMiner")
    void fetchCommentsFromGitHubMiner() {
        List<Comment> comments = githubMinerService.getGitHubComments(ownerLogin, repoName);
        System.out.println(comments);
        assertNotNull(comments);
        comments.forEach(comment -> assertNotNull(comment.getId()));
    }


}