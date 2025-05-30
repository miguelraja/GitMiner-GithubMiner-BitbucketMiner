package aiss.githubminer;

import aiss.githubminer.controller.GitHubMinerController;
import aiss.githubminer.exception.ProjectBadRequestException;
import aiss.githubminer.exception.ProjectNotFoundException;
import aiss.githubminer.model.Repos;
import aiss.githubminer.modelTransformed.ProjectTransformed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ControllerTest {

    @Autowired
    GitHubMinerController controller;

    @Value("spring-projects")
    private String ownerLogin;

    @Value("spring-framework")
    private String repoName;

    @Value("2")
    private int maxPages;

    @Value("2")
    private int sinceCommits;

    @Value("20")
    private int sinceIssues;

    @Test
    @DisplayName("Fetch project from GitHubMiner")
    void findOneTest() throws ProjectNotFoundException {
        ProjectTransformed project = controller.findOne(ownerLogin,repoName,sinceCommits,sinceIssues,maxPages);
        System.out.println(project);
        assertNotNull(project);
        assertNotNull(project.getId());

    }

    @Test
    @DisplayName("Insert project from GitHubMiner")
    void insertTest() throws ProjectNotFoundException, ProjectBadRequestException {
        ProjectTransformed project = controller.insertOne(ownerLogin,repoName,sinceCommits,sinceIssues,maxPages);
        System.out.println(project);
        assertNotNull(project);
        assertNotNull(project.getId());

    }

}
