package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.comment.RawComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CommentServiceTest {

    @Value("${bitbucketminer.tests.workspace}")
    private String workspace;

    @Value("${bitbucketminer.tests.repo_slug}")
    private String repo_slug;

    @Value("${bitbucketminer.tests.maxpages}")
    private String maxPages;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Fetch comments from Bitbucket")
    void getRawCommentsTest() {
        List<RawComment> rawComments = commentService.getRawComments(workspace, repo_slug, maxPages, 1);
        System.out.println(rawComments);

        assertNotNull(rawComments);
        rawComments.forEach(comment -> assertNotNull(comment.getId()));
    }
}