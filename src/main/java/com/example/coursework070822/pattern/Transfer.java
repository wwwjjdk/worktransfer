package com.example.coursework070822.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Validated
public class Transfer {
    //  минимальный размер 16 ✅
    @Size(min = 16)
    private String cardFromNumber;
    // todo минимальный размер даты 4 ✅  патерн сделать
    @Size(min = 4)
    private String cardFromValidTill;
    // cvv минимальный 3 ✅
    @Size(min = 3)
    private String cardFromCVV;
    // минимальный размер ✅
    @Size(min = 16)
    private String cardToNumber;

    //доделать как класс
     private Amount amount;

    public Transfer(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber,
                    Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        //amount = new Amount(value,currency);
        this.amount = amount;
    }
}
