package com.example.coursework070822.pattern;

import com.example.coursework070822.exception.TransferException;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@Data
public class Amount {
    @Min(0)
    private int value;
    private String currency;

    public Amount(int value, String currency) {
        /*if(value < 0){
            throw new TransferException("сумма не должна быть меньше 0");
        }*/
        this.value = value;
        this.currency = currency;
    }
}
