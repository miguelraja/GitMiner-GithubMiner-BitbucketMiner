package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.commit.RawCommit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {

    @Value("${bitbucketminer.tests.workspace}")
    private String workspace;

    @Value("${bitbucketminer.tests.repo_slug}")
    private String repo_slug;

    @Value("${bitbucketminer.tests.ncommits}")
    private String nCommits;

    @Value("${bitbucketminer.tests.maxpages}")
    private String maxPages;

    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Fetch commits from Bitbucket")
    void getRawCommitsTest() {
        List<RawCommit> rawCommits = commitService.getRawCommits(workspace, repo_slug, nCommits, maxPages);
        System.out.println(rawCommits);

        assertNotNull(rawCommits);
        rawCommits.forEach(commit -> assertNotNull(commit.getHash()));
    }
}