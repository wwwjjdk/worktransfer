package com.example.coursework070822.pattern;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

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
    String data;
    @Size(min = 3, max = 3)
    String cvv;
    @Min(100)
    int balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return balance == card.balance && Objects.equals(number, card.number) && Objects.equals(data, card.data) && Objects.equals(cvv, card.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, data, cvv, balance);
    }
}
