package aiss.gitminer.controller;

import aiss.gitminer.exception.NotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "GitMiner Issues", description = "GitMiner Issues Collection")
@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CommentRepository commentRepository;

    @Operation(
            summary = "Retrieve all issues",
            description = "Retrieve all issues that are stored, optionally by author and/or state",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Issue.class)),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping
    public List<Issue> findAll(
            @Parameter(description = "State of the issue") @RequestParam(name = "state", required = false) String state,
            @Parameter(description = "ID of the author of the issue") @RequestParam(name = "authorId", required = false) String authorId
    ) {
        return issueRepository.findAllByStateAndAuthor(state, authorId);
    }

    @Operation(
            summary = "Retrieve an issue",
            description = "Retrieve an issue that is stored given its ID",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema =  @Schema(implementation = Issue.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    schema = @Schema()
                            )
                    }
            )
    })
    @GetMapping("/{id}")
    public Issue findOne(
            @Parameter(description = "ID of the issue") @PathVariable String id
    ) throws NotFoundException {
        if(!issueRepository.existsById(id)){
            throw new NotFoundException();
        }

        return issueRepository.findById(id).get();
    }

    @Operation(
            summary = "Retrieve all comments of an issue",
            description = "Retrieve all comments of an issue that are stored given the ID of the issue",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    schema = @Schema()
                            )
                    }
            )
    })
    @GetMapping("/{id}/comments")
    public List<Comment> findAll(
            @Parameter(description = "ID of the issue") @PathVariable String id
    ) throws NotFoundException {
        if(!issueRepository.existsById(id)){
            throw new NotFoundException();
        }

        return issueRepository.findById(id).get().getComments();
    }

}
