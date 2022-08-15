package com.example.coursework070822.layers;

import com.example.coursework070822.exception.ConfirmOperationException;
import com.example.coursework070822.exception.TransferException;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
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
        int[] forLogFromCard = doingTransferFromCard(transfer);
        log.info("БАЛАНС:\nДо перевода->{};\n После перевода->{}.", forLogFromCard[0], forLogFromCard[1]);

        int[] forLogToCard = doingTransferToCard(transfer);
        log.info("БАЛАНС:\n До перевода->{};\n После перевода->{};\n Сумма перевода с учетом коммиссии->{}.",
                forLogToCard[0],forLogToCard[1],forLogToCard[2]);
    }

    public int returnCommission(Transfer transfer) {
        int answer = (int) (transfer.getAmount().getValue() * COMMISSION);
        log.info("коммисия при переводе составляет:" + answer);
        return answer;
    }

    public int[] doingTransferFromCard(Transfer transfer) {
        int balanceBeforeFrom = repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).getBalance();
        int balanceAfterFrom = balanceBeforeFrom - transfer.getAmount().getValue();

        repositoryCourseWork.getArrayTrue().get(transfer.getCardFromNumber()).setBalance(balanceAfterFrom);
        return new int[]{balanceBeforeFrom, balanceAfterFrom};
    }

    public int[] doingTransferToCard(Transfer transfer) {
        int balanceBeforeTransfer = repositoryCourseWork.getArrayTrue().get(transfer.getCardToNumber()).getBalance();
        int transferAmountIncludingCommission = transfer.getAmount().getValue() - returnCommission(transfer);
        int balanceAfterTransfer = balanceBeforeTransfer + transferAmountIncludingCommission;

        repositoryCourseWork.getArrayTrue().get(transfer.getCardToNumber()).setBalance(balanceAfterTransfer);
        return new int[]{balanceBeforeTransfer, balanceAfterTransfer, transferAmountIncludingCommission};
    }
}
