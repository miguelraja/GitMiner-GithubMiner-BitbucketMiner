package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitControllerTest {

    @Autowired
    private CommitRepository commitRepository;
    private Commit commit;

    @BeforeEach
    public void setup() {
        commit = new Commit();
        commit.setId("1");
        commit.setMessage("Initial commit");
        commit.setAuthor_email("author@email.com");
        commit.setAuthor_name("Author Name");
        commit.setAuthored_date("2025-05-08T12:00:00");
        commit.setWeb_url("http://example.com/commit/1");

        commitRepository.save(commit);
    }

    @Test
    @DisplayName("Find all commits")
    public void testFindAllCommits() {
        List<Commit> commits = commitRepository.findAll();
        String id = "1";
        assertFalse(commits.isEmpty(), "There should be commits");
        assertEquals(1, commits.size(), "There should be one commit");
        assertEquals(id, commits.get(0).getId(), "The commit with ID" + commits.get(0).getId() + "should be" + id);
    }

    @Test
    @DisplayName("Find one commit by ID")
    public void testFindCommitById() {
        String id = "1";
        Optional<Commit> retrievedCommit = commitRepository.findById(id);
        assertTrue(retrievedCommit.isPresent(), "The commit with ID" + id + "should be present");

        Commit commit = retrievedCommit.get();
        assertEquals("Initial commit", commit.getMessage(), "The commit message should be 'Initial commit'");
        assertEquals("http://example.com/commit/1", commit.getWeb_url(), "The web url should be 'http://example.com/commit/1'");
        assertEquals("Author Name", commit.getAuthor_name(), "The author name should be 'Author Name'");
        assertEquals("author@email.com", commit.getAuthor_email(), "The author email should be 'author@email.com'");
    }

    @Test
    @DisplayName("Find one commit by invalid ID")
    public void testFindCommitByInvalidId() {
        String invalidId = "99999999";
        Optional<Commit> retrievedCommit = commitRepository.findById(invalidId);
        assertFalse(retrievedCommit.isPresent(), "The commit with ID" + invalidId + "should not be present");
    }

    @Test
    @DisplayName("Update a commit")
    public void testUpdateCommit() {
        Optional<Commit> retrievedcommit = commitRepository.findById(commit.getId());
        assertTrue(retrievedcommit.isPresent());

        Commit existingCommit = retrievedcommit.get();
        existingCommit.setMessage("Updated commit");
        commitRepository.save(existingCommit);

        Optional<Commit> updatedCommit = commitRepository.findById(existingCommit.getId());
        assertTrue(updatedCommit.isPresent());
        assertEquals("Updated commit", updatedCommit.get().getMessage(), "The commit message should be 'Updated commit'" );
    }

    @Test
    @DisplayName("Delete commit")
    public void testDeleteCommit() {
        commitRepository.deleteById(commit.getId());
        Optional<Commit> deletedCommit = commitRepository.findById(commit.getId());
        assertFalse(deletedCommit.isPresent(), "The commit with ID" + commit.getId() + "should not be present");
    }
}