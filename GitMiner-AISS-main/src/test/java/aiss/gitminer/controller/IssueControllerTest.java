package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.User;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueControllerTest {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Issue issue;
    private Comment comment1;
    private Comment comment2;
    private User author;
    private User assignee;


    @BeforeEach
    public void setUp(){
        author = new User();
        author.setId("1");
        author.setUsername("authorUser");

        assignee = new User();
        assignee.setId("2");
        assignee.setUsername("assigneeUser");

        comment1 = new Comment();
        comment1.setId("1");
        comment1.setBody("First test comment");
        comment1.setCreated_at("2025-05-08T12:00:00");
        comment1.setUpdated_at("2025-05-08T12:30:00");
        commentRepository.save(comment1);

        comment2 = new Comment();
        comment2.setId("2");
        comment2.setBody("Second test comment");
        comment2.setCreated_at("2025-05-08T12:05:00");
        comment2.setUpdated_at("2025-05-08T12:35:00");
        commentRepository.save(comment2);

        issue = new Issue();
        issue.setId("1");
        issue.setTitle("Test Issue");
        issue.setDescription("This is a test issue");
        issue.setState("open");
        issue.setCreated_at("2025-05-08T12:00:00");
        issue.setUpdated_at("2025-05-08T12:30:00");
        issue.setClosed_at(null);
        issue.setVotes(5);
        issue.setLabels(List.of("bug", "urgent"));
        issue.setAuthor(author);
        issue.setAssignee(assignee);
        issue.setComments(new ArrayList<>(List.of(comment1, comment2)));

        issueRepository.save(issue);
    }

    @Test
    @DisplayName("Find all issues")
    public void testFindAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        assertFalse(issues.isEmpty(), "The list of issues should not be empty");
        assertEquals(1, issues.size(), "There should be only one issue");

        Issue retrievedIssue = issues.get(0);
        assertEquals("Test Issue", retrievedIssue.getTitle(), "The title should match");
        assertEquals("open", retrievedIssue.getState(), "The state should be 'open'");
        assertEquals(5, retrievedIssue.getVotes(), "The number of votes should be 5");
        assertEquals(List.of("bug", "urgent"), retrievedIssue.getLabels(), "Labels should match the expected list");
        assertNotNull(issue.getAuthor(), "Author should not be null");
        assertEquals("authorUser", issue.getAuthor().getUsername(), "The author username should match");
        assertNotNull(issue.getAssignee(), "Assignee should not be null");
        assertEquals("assigneeUser", issue.getAssignee().getUsername(), "The assignee username should match");
    }

    @Test
    @DisplayName("Find one issue by id")
    void testFindOne() {
        String id = "1";
        Optional<Issue> retrievedIssue = issueRepository.findById(id);
        assertTrue(retrievedIssue.isPresent(), "The issue with ID " + id + " should be present");

        Issue issue = retrievedIssue.get();
        assertEquals("Test Issue", issue.getTitle(), "The title should be 'Test Issue'");
        assertEquals("This is a test issue", issue.getDescription(), "The description should match");
        assertEquals("open", issue.getState(), "The state should be 'open'");
        assertEquals(5, issue.getVotes(), "The number of votes should be 5");

        assertNotNull(issue.getAuthor(), "The author should not be null");
        assertEquals("authorUser", issue.getAuthor().getUsername(), "The author username should be 'authorUser'");

        assertNotNull(issue.getAssignee(), "The assignee should not be null");
        assertEquals("assigneeUser", issue.getAssignee().getUsername(), "The assignee username should be 'assigneeUser'");

        List<Comment> comments = issue.getComments();
        assertEquals(2, comments.size(), "There should be two comments associated with the issue");

        assertEquals("1", comments.get(0).getId(), "First comment ID should be 'c101'");
        assertEquals("First test comment", comments.get(0).getBody(), "First comment body should match");

        assertEquals("2", comments.get(1).getId(), "Second comment ID should be 'c102'");
        assertEquals("Second test comment", comments.get(1).getBody(), "Second comment body should match");
    }

}