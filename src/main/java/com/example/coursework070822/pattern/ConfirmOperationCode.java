package com.example.coursework070822.pattern;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Validated
public class ConfirmOperationCode {
    String operationId;
   @Size(min = 4)
    String code;
}
