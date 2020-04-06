package com.personalmanagement.ppmtool.services;

import com.personalmanagement.ppmtool.domain.Project;
import com.personalmanagement.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        //Logic
        return projectRepository.save(project);
    }
}
