package com.example.coursework070822.pattern;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class Card {
    @Size(min = 16, max = 16)
    String number;
    @Size(min = 4, max = 5)
    transient String data;
    @Size(min = 3, max = 3)
    transient String cvv;
    @Min(100)
    int balance;
}
