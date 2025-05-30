package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest {

    @Autowired
    private CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment();
        comment.setId("1");
        comment.setBody("This is a test comment");
        comment.setCreated_at("2025-05-08T12:00:00");
        comment.setUpdated_at("2025-05-08T12:30:00");

        commentRepository.save(comment);
    }

    @Test
    public void contextLoads() {
        assertNotNull(commentRepository);
    }

    @Test
    @DisplayName("Find all comments")
    public void testFindAllComments() {
        List<Comment> comments = commentRepository.findAll();
        assertFalse(comments.isEmpty(), "Comments list shouldn't be empty");
        assertEquals(1, comments.size(), "There should be only one comment in the list");
        assertEquals("1", comments.get(0).getId(), "There should be only one comment in the list");
    }


    @Test
    @DisplayName("Find one comment by ID")
    public void testFindCommentById() {
        String id = "1";
        Optional<Comment> retrievedComment = commentRepository.findById(id);
        assertTrue(retrievedComment.isPresent(), "The comment with ID" + id + "should be present");

        Comment comment = retrievedComment.get();
        assertEquals("This is a test comment", comment.getBody(), "The body should be 'This is a test comment'");
    }

    @Test
    @DisplayName("Save a comment")
    public void testSaveComment() {
        Optional<Comment> retrievedComment = commentRepository.findById(comment.getId());
        assertTrue(retrievedComment.isPresent(), "The comment with ID" + comment.getId() + "should be present");
    }

    @Test
    @DisplayName("Update a comment")
    public void testUpdateComment(){
        comment.setBody("This is an updated test comment");
        commentRepository.save(comment);
        Optional<Comment> updatedComment = commentRepository.findById(comment.getId());
        assertTrue(updatedComment.isPresent(), "The comment with ID" + comment.getId() + "should be present");
        assertEquals("This is an updated test comment", updatedComment.get().getBody(), "The body should be 'This is an updated test comment'");
    }

    @Test
    @DisplayName("Delete a comment")
    public void testDeleteComment(){
        commentRepository.deleteById(comment.getId());
        Optional<Comment> deletedComment = commentRepository.findById(comment.getId());
        assertFalse(deletedComment.isPresent(), "The comment with ID" + comment.getId() + "should be deleted");
    }

}