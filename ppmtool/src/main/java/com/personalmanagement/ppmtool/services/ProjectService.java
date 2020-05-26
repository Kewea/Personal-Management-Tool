package com.personalmanagement.ppmtool.services;

import com.personalmanagement.ppmtool.domain.Project;
import com.personalmanagement.ppmtool.exceptions.ProjectIdException;
import com.personalmanagement.ppmtool.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Service
public class ProjectService {
    Logger logger = LoggerFactory.getLogger(ProjectService.class);
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

        if(project.getId() != null) {
            //Validation for not updating project identifier
            Optional<Project> optionalProject = projectRepository.findById(project.getId());

            if(optionalProject.isPresent()) {
                if(!optionalProject.get().getProjectIdentifier().equals(project.getProjectIdentifier())) {
                    throw new ProjectIdException("Project identifier should not be updated");
                }
            }
        } else {
            //Validation of project identifier for creating a new project
            if (projectRepository.countByProjectIdentifier(project.getProjectIdentifier()) > 0) {
                throw new ProjectIdException("Project identifier has been used");
            }
        }

        try {
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project cannot be updated or saved");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Optional<Project> project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(!project.isPresent()) {
            throw new ProjectIdException("Project identifier does not exists");
        }

        return project.get();
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByProjectIdentifier(String projectIdentifier){
        Optional<Project> project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(!project.isPresent()) {
            throw new ProjectIdException("Cannot delete project with identifier " + projectIdentifier + ". Project identifier does not exist.");
        }

        projectRepository.delete(project.get());
    }
}
