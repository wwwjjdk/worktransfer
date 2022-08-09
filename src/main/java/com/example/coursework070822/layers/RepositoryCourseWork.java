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
    private static final int CODE = 1212;
    private long index;

    //private ConcurrentHashMap<String , Card> array = new ConcurrentHashMap<>();
    private Map<String, Card> array = Map.of(
            "1111111111111111", new Card("1111111111111111", "12/25", "321", 10000),
            "2222222222222222", new Card("2222222222222222", "12/25", "123", 5000)
    );
    private AtomicLong atomicLong = new AtomicLong();

    public Card search(String number) {
        if (array.containsKey(number)) {
            return array.get(number);
        } else {
            throw new RuntimeException();
        }
    }

    public String transfer(Transfer transfer) {
        //проверка номеров
        if (array.containsKey(transfer.getCardFromNumber()) && array.containsKey(transfer.getCardToNumber())) {
            //проверка данных карты отправителя
            if (array.get(transfer.getCardFromNumber()).getCvv().equals(transfer.getCardFromCVV()) &&
                    array.get(transfer.getCardFromNumber()).getData().equals(transfer.getCardFromValidTill())) {
                //проверка баланса
                if (array.get(transfer.getCardFromNumber()).getBalance() > transfer.getAmount().getValue()) {

                    index = atomicLong.incrementAndGet();

                    doingTransferBetweenCards(transfer);

                    return "Operation id:" + index;

                } else {
                    throw new TransferException("There are not enough funds on the balance to transfer");
                }
            } else {
                throw new TransferException("Cvv or date is incorrect");
            }
        } else {
            throw new TransferException("Number is incorrect");
        }

    }

    public String confirmOperation(ConfirmOperationCode confirmOperationCode) {
        if (Integer.parseInt(confirmOperationCode.getOperationId()) == index &&
                Integer.parseInt(confirmOperationCode.getCode()) == CODE) {
            return "Operation id: " + index;
        } else {
            throw new ConfirmOperationException("operation number or code entered incorrectly");
        }
    }

    public void doingTransferBetweenCards(Transfer transfer) {
        int balanceBeforeFrom = array.get(transfer.getCardFromNumber()).getBalance();
        int balanceAfterFrom = balanceBeforeFrom - transfer.getAmount().getValue();
        array.get(transfer.getCardFromNumber()).setBalance(balanceAfterFrom);
        array.get(transfer.getCardToNumber()).setBalance(
                array.get(transfer.getCardToNumber()).getBalance() + (
                        transfer.getAmount().getValue() - (int) (transfer.getAmount().getValue() * 0.01)));
    }
}
