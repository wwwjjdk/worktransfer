package com.example.coursework070822.layers;

import com.example.coursework070822.exception.ConfirmOperationException;
import com.example.coursework070822.exception.TransferException;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class ControllerCourseWork {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerCourseWork.class);

    private  final ServiceCourseWork serviceCourseWork;

    public ControllerCourseWork(ServiceCourseWork serviceCourseWork){
        this.serviceCourseWork = serviceCourseWork;
    }

    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody Transfer transfer){
        LOGGER.info("translation request ");
        return serviceCourseWork.transfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(@RequestBody ConfirmOperationCode confirmOperationCode){
        LOGGER.info("code verification request");
        return serviceCourseWork.confirmOperation(confirmOperationCode);
    }
    @GetMapping("/{number}")
    public Card search(@PathVariable String number){
         return serviceCourseWork.search(number);
    }

    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ex1(TransferException transferException){
        LOGGER.error("transfer error " + transferException.getMessage());
        return transferException.getMessage();
    }

    @ExceptionHandler(ConfirmOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ex2(ConfirmOperationException confirmOperationException){
        LOGGER.error("confirmOperationException " + confirmOperationException.getMessage());
        return confirmOperationException.getMessage();
    }
}
