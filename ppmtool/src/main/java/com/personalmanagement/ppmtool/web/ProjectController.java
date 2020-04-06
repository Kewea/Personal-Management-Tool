package com.personalmanagement.ppmtool.web;

import com.personalmanagement.ppmtool.domain.Project;
import com.personalmanagement.ppmtool.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        Project savedProject = projectService.saveOrUpdateProject(project);
        logger.info(savedProject.toString());
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
}
