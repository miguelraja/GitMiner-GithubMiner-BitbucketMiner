package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.project.RawProject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Value("${bitbucketminer.tests.workspace}")
    private String workspace;

    @Value("${bitbucketminer.tests.repo_slug}")
    private String repo_slug;

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Fetch project from Bitbucket")
    void getRawProjectTest() {
        RawProject rawProject = projectService.getRawProject(workspace, repo_slug);
        System.out.println(rawProject);

        assertNotNull(rawProject);
        assertNotNull(rawProject.getUuid());
    }
}