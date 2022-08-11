package com.example.coursework070822.layers;

import com.example.coursework070822.exception.ConfirmOperationException;
import com.example.coursework070822.exception.TransferException;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceCourseWork {
    private static final double COMMISSION = 0.01;
    private final RepositoryCourseWork repositoryCourseWork;

    public ServiceCourseWork(RepositoryCourseWork repositoryCourseWork) {
        this.repositoryCourseWork = repositoryCourseWork;
    }

    public String transfer(Transfer transfer) {
        if (repositoryCourseWork.getArrayTrue().containsKey(transfer.getCardFromNumber()) && repositoryCourseWork.getArrayTrue().containsKey(transfer.getCardToNumber())) {
            if (repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).getCvv().equals(transfer.getCardFromCVV()) &&
                    repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).getData().equals(transfer.getCardFromValidTill())) {
                if (repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).getBalance() > transfer.getAmount().getValue()) {

                    doingTransferBetweenCards(transfer);

                    return repositoryCourseWork.transfer(Long.toString(
                            repositoryCourseWork.getAtomicLong().incrementAndGet()), UUID.randomUUID().toString());

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
        if (repositoryCourseWork.getCodes().containsKey(confirmOperationCode.getOperationId()) &&
        confirmOperationCode.getCode().equals(repositoryCourseWork.getCodes().get(confirmOperationCode.getOperationId()))) {
            return repositoryCourseWork.confirmOperation(confirmOperationCode.getOperationId());
        } else {
            throw new ConfirmOperationException("operation number or code entered incorrectly");
        }

    }

    public String createCard(Card card) {
        return repositoryCourseWork.createCard(card);
    }

    public Card search(String number) {
        return repositoryCourseWork.search(number);
    }

    public void doingTransferBetweenCards(Transfer transfer) {
        int balanceBeforeFrom = repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).getBalance();
        int balanceAfterFrom = balanceBeforeFrom - transfer.getAmount().getValue();
        repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).setBalance(balanceAfterFrom);
        repositoryCourseWork.getArrayTrue().get(transfer.getCardToNumber()).setBalance(
                repositoryCourseWork.getArrayTrue().get(transfer.getCardToNumber()).getBalance() + (
                        transfer.getAmount().getValue() - (int) (transfer.getAmount().getValue() * COMMISSION)));
    }
}
