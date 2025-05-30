package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, String> {

//    List<Issue> findAllByState(String state);
//    List<Issue> findAllByAuthor(String authorId);

    @Query("SELECT i FROM Issue i WHERE (:state IS NULL OR i.state = :state) AND (:authorId IS NULL OR i.author.id = :authorId)")
    List<Issue> findAllByStateAndAuthor(
            @Param("state") String state,
            @Param("authorId") String authorId
    );
}
