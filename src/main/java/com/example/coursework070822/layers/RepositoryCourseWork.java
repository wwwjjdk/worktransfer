package com.example.coursework070822.layers;

import com.example.coursework070822.exception.ConfirmOperationException;
import com.example.coursework070822.exception.TransferException;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RepositoryCourseWork {
    private static ConcurrentHashMap<String, Card> arrayTrue = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> codes = new ConcurrentHashMap<>();

    private static AtomicLong atomicLong = new AtomicLong();

    public Card search(String number) {
        if (arrayTrue.containsKey(number)) {
            return arrayTrue.get(number);
        } else {
            throw new TransferException("User is not found");
        }
    }

    public String transfer(String index, String uuid) {
        codes.put(index , uuid);
        return "Operation id:" + atomicLong + " code: " + uuid;
    }

    public String confirmOperation(String index) {
         return "Operation id:" + index + " performed correctly";
    }

    public String createCard(Card card) {
        if (arrayTrue.containsKey(card.getNumber())) {
            throw new TransferException("this debit card already exists");
        } else {
            arrayTrue.put(card.getNumber(), card);
            return "map created successfully";
        }
    }


    public ConcurrentHashMap<String, Card> getArrayTrue() {
        return arrayTrue;
    }
    public ConcurrentHashMap<String, String> getCodes(){
        return  codes;
    }

    public AtomicLong getAtomicLong() {
        return atomicLong;
    }


}
