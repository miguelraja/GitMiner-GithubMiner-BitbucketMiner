package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.miner.MinerProject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectControllerTest {

    @Value("${bitbucketminer.tests.workspace}")
    private String workspace;

    @Value("${bitbucketminer.tests.repo_slug}")
    private String repo_slug;

    @Value("${bitbucketminer.tests.ncommits}")
    private String nCommits;

    @Value("${bitbucketminer.tests.nissues}")
    private String nIssues;

    @Value("${bitbucketminer.tests.maxpages}")
    private String maxPages;

    @Autowired
    ProjectController projectController;

    @Test
    @DisplayName("Fetch project from BitbucketMiner")
    void findOneTest() throws Exception {
        MinerProject project = projectController.findOne(workspace, repo_slug, nCommits, nIssues, maxPages);
        System.out.println(project);
        assertNotNull(project);
        assertNotNull(project.getCommits());
        assertNotNull(project.getIssues());

        project.getCommits().forEach(commit -> assertNotNull(commit.getId()));
        project.getIssues().forEach(issue -> assertNotNull(issue.getId()));
        project.getIssues().forEach(issue -> issue.getComments().forEach(comment -> assertNotNull(comment.getId())));
    }
}