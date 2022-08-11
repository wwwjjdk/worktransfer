package com.example.coursework070822.pattern;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;


@Validated
@ToString
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Transfer {
    @Size(min = 16)
    String cardFromNumber;
    @Size(min = 4)
    String cardFromValidTill;
    @Size(min = 3)
    String cardFromCVV;
    @Size(min = 16)
    String cardToNumber;
    @Valid
    Amount amount;

}
