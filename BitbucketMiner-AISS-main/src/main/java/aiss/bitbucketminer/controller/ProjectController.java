package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.exception.ProjectBadRequestException;
import aiss.bitbucketminer.exception.ProjectNotFoundException;
import aiss.bitbucketminer.model.miner.MinerProject;
import aiss.bitbucketminer.model.raw.comment.RawComment;
import aiss.bitbucketminer.model.raw.commit.RawCommit;
import aiss.bitbucketminer.model.raw.issue.RawIssue;
import aiss.bitbucketminer.model.raw.project.RawProject;
import aiss.bitbucketminer.service.CommentService;
import aiss.bitbucketminer.service.CommitService;
import aiss.bitbucketminer.service.IssueService;
import aiss.bitbucketminer.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "BitbucketMiner", description = "Bitbucket API Miner")
@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    CommitService commitService;

    @Autowired
    IssueService issueService;

    @Autowired
    CommentService commentService;

    @Autowired
    RestTemplate template;

    @Value("${gitminer.base}")
    private String gitminer;

    @Operation(
            summary = "Retrieve a project",
            description = "Retrieve a project from Bitbucket given its workspace and repository slug"
//            tags = {"get"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = MinerProject.class),
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
    @GetMapping("/{workspace}/{repo_slug}")
    public MinerProject findOne(
            @Parameter(description = "Workspace of the project") @PathVariable String workspace,
            @Parameter(description = "Repository slug of the project") @PathVariable String repo_slug,
            @Parameter(description = "Number of commits to retrieve per page") @RequestParam(name = "nCommits", required = false, defaultValue = "5") String nCommits,
            @Parameter(description = "Number of issues to retrieve per page") @RequestParam(name = "nIssues", required = false, defaultValue = "5") String nIssues,
            @Parameter(description = "Number of pages to retrieve") @RequestParam(name = "maxPages", required = false, defaultValue = "2") String maxPages
    ) throws ProjectNotFoundException {
        RawProject rawProject = this.projectService.getRawProject(workspace, repo_slug);

        if (rawProject == null) {
            throw new ProjectNotFoundException();
        }

        List<RawCommit> rawCommits = this.commitService.getRawCommits(workspace, repo_slug, nCommits, maxPages);
        List<RawIssue> rawIssues = this.issueService.getRawIssues(workspace, repo_slug, nIssues, maxPages);
        Map<String, List<RawComment>> rawComments = rawIssues.stream()
                .collect(Collectors.toMap(
                        rawIssue -> rawIssue.getId().toString(),
                        rawIssue -> this.commentService.getRawComments(workspace, repo_slug, maxPages, rawIssue.getId())
                ));

        return new MinerProject(rawProject, rawCommits, rawIssues, rawComments);
    }

    @Operation(
            summary = "Insert a project",
            description = "Insert a project from Bitbucket to GitMiner given its workspace and repository slug"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = MinerProject.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    schema = @Schema()
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
    @PostMapping("/{workspace}/{repo_slug}")
    @ResponseStatus(HttpStatus.CREATED)
    public MinerProject insertOne(
            @Parameter(description = "Workspace of the project") @PathVariable String workspace,
            @Parameter(description = "Repository slug of the project") @PathVariable String repo_slug,
            @Parameter(description = "Number of commits to retrieve per page") @RequestParam(name = "nCommits", required = false, defaultValue = "5") String nCommits,
            @Parameter(description = "Number of issues to retrieve per page") @RequestParam(name = "nIssues", required = false, defaultValue = "5") String nIssues,
            @Parameter(description = "Number of pages to retrieve") @RequestParam(name = "maxPages", required = false, defaultValue = "2") String maxPages
    ) throws ProjectNotFoundException, ProjectBadRequestException {
        MinerProject project = this.findOne(workspace, repo_slug, nCommits, nIssues, maxPages);

        try {
            HttpHeaders headers = new HttpHeaders();
            // BitBucket only allows once repository to be accessed with an Auth Token???
            HttpEntity<MinerProject> entity = new HttpEntity<>(project, headers);

            ResponseEntity<MinerProject> response = this.template.exchange(
                    this.gitminer,
                    HttpMethod.POST,
                    entity,
                    MinerProject.class
            );

            return response.getBody();
        } catch (BadRequest e) {
            throw new ProjectBadRequestException();
        }
    }

}
