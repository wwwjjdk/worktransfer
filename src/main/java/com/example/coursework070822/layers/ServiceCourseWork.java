package com.example.coursework070822.layers;

import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;
import org.springframework.stereotype.Service;

@Service
public class ServiceCourseWork {

    private  final RepositoryCourseWork repositoryCourseWork;

    public ServiceCourseWork(RepositoryCourseWork repositoryCourseWork){
        this.repositoryCourseWork = repositoryCourseWork;
    }

    public String transfer(Transfer transfer){
        return repositoryCourseWork.transfer(transfer);
    }

    public String confirmOperation(ConfirmOperationCode confirmOperationCode){
        return repositoryCourseWork.confirmOperation(confirmOperationCode);
    }

    public Card search(String number){
        return repositoryCourseWork.search(number);
    }
}
