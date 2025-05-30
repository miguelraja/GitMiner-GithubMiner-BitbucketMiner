package aiss.gitminer.controller;

import aiss.gitminer.exception.NotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
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

@Tag(name = "GitMiner Commits", description = "GitMiner Commits Collection")
@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {

    @Autowired
    CommitRepository commitRepository;

    @Operation(
            summary = "Retrieve all commits",
            description = "Retrieve all commits that are stored",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Commit.class)),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping
    public List<Commit> findAll() {
        return commitRepository.findAll();
    }

    @Operation(
            summary = "Retrieve a commit",
            description = "Retrieve a commit that is stored given its ID",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema =  @Schema(implementation = Commit.class),
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
    public Commit findOne(
            @Parameter(description = "ID of the commit") @PathVariable String id
    ) throws NotFoundException {
        if (!commitRepository.existsById(id)) {
            throw new NotFoundException();
        }

        return commitRepository.findById(id).get();
    }

}
