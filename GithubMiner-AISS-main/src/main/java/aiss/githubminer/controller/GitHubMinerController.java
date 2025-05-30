package aiss.githubminer.controller;

import aiss.githubminer.exception.*;
import aiss.githubminer.model.*;
import aiss.githubminer.modelTransformed.CommentTransformed;
import aiss.githubminer.modelTransformed.CommitTransformed;
import aiss.githubminer.modelTransformed.IssueTransformed;
import aiss.githubminer.modelTransformed.ProjectTransformed;
import aiss.githubminer.service.GithubMinerService;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "GithubMiner", description = "Github API Miner")
@RestController
@RequestMapping("/github")
public class GitHubMinerController {

    @Autowired
    GithubMinerService githubMinerService;

    @Autowired
    RestTemplate template;

    @Value("${gitminer.base}")
    private String base;

    @Operation(
            summary = "Retrieve a project",
            description = "Find a project from Github, given its owner and its repository's name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProjectTransformed.class),
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

    // GET http://localhost:8082/github/{owner}/{repo}
    @GetMapping("/{owner}/{repoName}")
    public ProjectTransformed findOne(
            @Parameter(description = "Owner of the project") @PathVariable String owner,
            @Parameter(description = "Name of the project's repository") @PathVariable String repoName,
            @Parameter(description = "Recent commits to return from the last X days, where X is sinceCommits") @RequestParam(name = "sinceCommits", required = false, defaultValue = "2") int sinceCommits,
            @Parameter(description = "Recent issues to return from the last X days, where X is sinceIssues") @RequestParam(name = "sinceIssues", required = false, defaultValue = "20") int sinceIssues,
            @Parameter(description = "Number of pages to retrieve") @RequestParam(defaultValue = "2") int maxPages
    ) throws ProjectNotFoundException {
        Repos rawProject = this.githubMinerService.getGitHubRepo(owner, repoName, maxPages);
        if(rawProject == null){
            throw new ProjectNotFoundException();
        }

        List<CommitOuter> commits = githubMinerService.getGitHubCommits(owner, repoName, sinceCommits, maxPages);
        List<Issue> issues = githubMinerService.getGitHubIssues(owner, repoName, sinceIssues, maxPages);
        List<Comment> comments = githubMinerService.getGitHubComments(owner, repoName);

        return new ProjectTransformed(rawProject, commits, issues, comments);
    }

    @Operation(
            summary = "Insert a project",
            description = "Insert a project from Github, given its owner and its repository's name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProjectTransformed.class),
                                    mediaType = "application/json"
                            ),
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
    @PostMapping("/{owner}/{repoName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectTransformed insertOne(
            @Parameter(description = "Owner of the project") @PathVariable String owner,
            @Parameter(description = "Name of the project's repository") @PathVariable String repoName,
            @Parameter(description = "Recent commits to return from the last X days, where X is sinceCommits") @RequestParam(name = "sinceCommits", required = false, defaultValue = "2") int sinceCommits,
            @Parameter(description = "Recent issues to return from the last X days, where X is sinceIssues") @RequestParam(name = "sinceIssues", required = false, defaultValue = "20") int sinceIssues,
            @Parameter(description = "Number of pages to retrieve") @RequestParam(defaultValue = "2") int maxPages
    ) throws ProjectNotFoundException, ProjectBadRequestException {
        ProjectTransformed project = this.findOne(owner, repoName, sinceCommits, sinceIssues, maxPages);

        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<ProjectTransformed> entity = new HttpEntity<>(project, headers);

            ResponseEntity<ProjectTransformed> response = this.template.exchange(
                    this.base,
                    HttpMethod.POST,
                    entity,
                    ProjectTransformed.class
            );

            return response.getBody();
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ProjectBadRequestException();
        }
    }

}
