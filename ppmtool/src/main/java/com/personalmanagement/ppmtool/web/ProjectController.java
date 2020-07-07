package com.personalmanagement.ppmtool.web;

import com.personalmanagement.ppmtool.domain.Project;
import com.personalmanagement.ppmtool.services.MapValidationErrorService;
import com.personalmanagement.ppmtool.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private MapValidationErrorService mapValidationErrorService;
    private ProjectService projectService;

    @Autowired
    public ProjectController(
            MapValidationErrorService mapValidationErrorService,
            ProjectService projectService
    ){
        this.mapValidationErrorService = mapValidationErrorService;
        this.projectService = projectService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        Map<String, String> errorMap = mapValidationErrorService.mapValidationError(bindingResult);

        if(!errorMap.isEmpty()) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        Project savedProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProjects(){
        return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
        projectService.deleteProjectByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<>("Project was deleted", HttpStatus.OK);
    }
}
