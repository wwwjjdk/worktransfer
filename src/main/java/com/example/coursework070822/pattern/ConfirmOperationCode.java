package com.example.coursework070822.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmOperationCode {

    private String operationId;
    private String code;
}
