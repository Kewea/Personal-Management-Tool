package com.personalmanagement.ppmtool.services;

import com.personalmanagement.ppmtool.domain.Project;
import com.personalmanagement.ppmtool.exceptions.ProjectIdException;
import com.personalmanagement.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project ID: '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Optional<Project> project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(!project.isPresent()) {
            throw new ProjectIdException("Project ID does not exists");
        }

        return project.get();
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByProjectIdentifier(String projectIdentifier){
        Project project = this.findProjectByIdentifier(projectIdentifier.toUpperCase());
        projectRepository.delete(project);
    }
}
