package com.example.coursework070822.layers;

import com.example.coursework070822.exception.ConfirmOperationException;
import com.example.coursework070822.exception.TransferException;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
@Slf4j
public class ControllerCourseWork {
    private final ServiceCourseWork serviceCourseWork;

    public ControllerCourseWork(ServiceCourseWork serviceCourseWork) {
        this.serviceCourseWork = serviceCourseWork;
    }

    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody Transfer transfer) {
        log.info("translation request");
        return serviceCourseWork.transfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(@Valid @RequestBody ConfirmOperationCode confirmOperationCode) {
        log.info("method confirmOperation is starting");
        return serviceCourseWork.confirmOperation(confirmOperationCode);
    }

    @GetMapping("/{number}")
    public Card search(@PathVariable String number) {
        return serviceCourseWork.search(number);
    }

    @PostMapping("/createCard")
    public String createCard(@Valid @RequestBody Card card){
        log.info("creating card");
        return serviceCourseWork.createCard(card);
    }

    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ex1(TransferException transferException) {
        log.error("getClient with {} - get error", transferException.getMessage());
        return transferException.getMessage();
    }

    @ExceptionHandler(ConfirmOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ex2(ConfirmOperationException confirmOperationException) {
        log.error("getClient with {} - get error", confirmOperationException.getMessage());
        return confirmOperationException.getMessage();
    }
}
