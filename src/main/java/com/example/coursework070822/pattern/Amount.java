package com.example.coursework070822.pattern;

import com.example.coursework070822.exception.TransferException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

import static lombok.AccessLevel.PRIVATE;

@Validated
@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = PRIVATE)
public class Amount {
    @Min(0)
    int value;
    String currency;


}
