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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/project")
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
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
        if(errorMap != null) {
            return errorMap;
        }

        Project savedProject = projectService.saveOrUpdateProject(project);
        //Debug logging
        logger.info(savedProject.toString());

        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
}
