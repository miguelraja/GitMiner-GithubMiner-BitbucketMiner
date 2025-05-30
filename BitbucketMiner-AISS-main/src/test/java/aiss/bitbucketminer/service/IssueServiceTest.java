package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.issue.RawIssue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class IssueServiceTest {

    @Value("${bitbucketminer.tests.workspace}")
    private String workspace;

    @Value("${bitbucketminer.tests.repo_slug}")
    private String repo_slug;

    @Value("${bitbucketminer.tests.nissues}")
    private String nIssues;

    @Value("${bitbucketminer.tests.maxpages}")
    private String maxPages;

    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Fetch issues from Bitbucket")
    void getRawIssues() {
        List<RawIssue> rawIssues = issueService.getRawIssues(workspace, repo_slug, nIssues, maxPages);
        System.out.println(rawIssues);

        assertNotNull(rawIssues);
        rawIssues.forEach(issue -> assertNotNull(issue.getId()));
    }
}