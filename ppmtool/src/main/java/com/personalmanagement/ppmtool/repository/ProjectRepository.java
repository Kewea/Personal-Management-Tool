package com.personalmanagement.ppmtool.repository;

import com.personalmanagement.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Optional<Project> findByProjectIdentifier(String projectIdentifier);
    Long countByProjectIdentifier(String projectIdentifier);
}
