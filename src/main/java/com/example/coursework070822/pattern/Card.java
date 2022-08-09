package com.example.coursework070822.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private String number;
    private String data;
    private String cvv;
    private int balance;
}
