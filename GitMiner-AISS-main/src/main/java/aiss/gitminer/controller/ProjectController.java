package aiss.gitminer.controller;

import aiss.gitminer.exception.NotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "GitMiner Projects", description = "GitMiner Projects Collection")
@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Operation(
            summary = "Retrieve all projects",
            description = "Retrieve all projects that are stored",
            tags = { "GET" }
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Success",
                content = {
                        @Content(
                                array = @ArraySchema(schema = @Schema(implementation = Project.class)),
                                mediaType ="application/json"
                        )
                }
        )
    })
    @GetMapping
    public List<Project> findAll(
            @Parameter(description = "Page number of the result set (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of projects to retrieve per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Filter projects by name") @RequestParam(required = false) String name,
            @Parameter(description = "Order results (prefix with '-' for descending order)") @RequestParam(required = false) String order
    ) {
        Page<Project> pageProjects;
        // Order results if order param is supplied
        Pageable paging;
        if(order != null) {
            if (order.startsWith("-")) // Descending order
                    paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            else // Ascending order
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
        }
        else
            paging = PageRequest.of(page, size);

        // Filter results if value for name is supplied
        if (name != null)
            pageProjects = projectRepository.findByName(name, paging);
        else
            pageProjects = projectRepository.findAll(paging);
        return pageProjects.getContent();
    }

    @Operation(
            summary = "Retrieve a project",
            description = "Retrieve a project that is stored given its ID",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    schema =  @Schema(implementation = Project.class),
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
    public Project findOne(
            @Parameter(description = "ID of the project") @PathVariable String id
    ) throws NotFoundException {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException();
        }

        return projectRepository.findById(id).get();
    }

    @Operation(
            summary = "Insert a project",
            description = "Insert a project to store it",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Success",
                    content = {
                            @Content(
                                    schema =  @Schema(implementation = Project.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(
            @Parameter(description = "Data of the project") @RequestBody @Valid Project project
    ) {
        return projectRepository.save(
                new Project(
                        project.getId(),
                        project.getName(),
                        project.getWeb_url(),
                        project.getCommits(),
                        project.getIssues()
                )
        );
    }

    @Operation(
            summary = "Update a project",
            description = "Update a project that is stored given its ID and the new data",
            tags = { "PUT" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = {
                            @Content(
                                    schema =  @Schema()
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
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @Parameter(description = "ID of the project") @PathVariable String id,
            @Parameter(description = "Data of the project") @RequestBody @Valid Project project
    ) throws NotFoundException {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException();
        }

        Project oldProject = projectRepository.findById(id).get();
        oldProject.setName(project.getName());
        oldProject.setWeb_url(project.getWeb_url());
        oldProject.setCommits(project.getCommits());
        oldProject.setIssues(project.getIssues());

        projectRepository.save(oldProject);
    }

    @Operation(
            summary = "Delete a project",
            description = "Delete a project that is stored given its ID",
            tags = { "DELETE" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = {
                            @Content(
                                    schema =  @Schema()
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
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "ID of the project") @PathVariable String id
    ) throws NotFoundException {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException();
        }

        projectRepository.deleteById(id);
    }

}
