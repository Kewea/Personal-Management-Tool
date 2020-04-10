package com.personalmanagement.ppmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectIdExceptionResponse {
    private String projectIdIdentifier;
}
