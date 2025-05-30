package aiss.gitminer.controller;

import aiss.gitminer.exception.NotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "GitMiner Comments", description = "GitMiner Comments Collection")
@RestController
@RequestMapping("/gitminer/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Operation(
            summary = "Retrieve all comments",
            description = "Retrieve all comments that are stored",
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
            )
    })
    @GetMapping
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    @Operation(
            summary = "Retrieve a comment",
            description = "Retrieve a comment that is stored given its ID",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema =  @Schema(implementation = Comment.class),
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
    public Comment findOne(
            @Parameter(description = "ID of the comment") @PathVariable String id
    ) throws NotFoundException {
         if(!commentRepository.existsById(id)){
             throw new NotFoundException();
         }

         return commentRepository.findById(id).get();
    }
}
