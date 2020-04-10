package com.personalmanagement.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Project name should not be blank")
    private String projectName;

    @Column(name = "project_identifier", unique = true, updatable = false)
    @NotBlank(message = "Project Identifier should not be blank")
    @Size(min = 4, max = 6, message = "Please use 4 to 6 characters")
    private String projectIdentifier;

    @NotBlank(message = "Project description must not be blank")
    private String description;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt =  new Date();
    }

    @PostUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}