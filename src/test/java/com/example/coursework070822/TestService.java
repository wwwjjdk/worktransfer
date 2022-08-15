package com.example.coursework070822;
import com.example.coursework070822.layers.RepositoryCourseWork;
import com.example.coursework070822.layers.ServiceCourseWork;
import com.example.coursework070822.pattern.Amount;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestService {

    private  final  RepositoryCourseWork repositoryCourseWork = new RepositoryCourseWork();
    private  final  ServiceCourseWork serviceCourseWork = new ServiceCourseWork(repositoryCourseWork);
    private final Amount amount = new Amount(100,"RUR");
    private  final Transfer transfer = new Transfer("1111111111111111","12/25",
            "321","2222222222222222",amount);
    private final  Card card1= new Card("1111111111111111","12/25","321",200);
    private final Card card2 = new Card("2222222222222222","12/25","123",100);


    @Test
    public void testDoingTransferFromCard(){
        repositoryCourseWork.getArrayTrue().put(card1.getNumber(),
                card1);
        repositoryCourseWork.getArrayTrue().put(card2.getNumber(),
                card2);

        int [] answer = serviceCourseWork.doingTransferFromCard(transfer);
        int [] expected = {200,100};

        Assertions.assertArrayEquals(expected, answer);
    }

    @Test
    public void testCommission(){
     int answer =  serviceCourseWork.returnCommission(transfer);
     int expected = 1;

     Assertions.assertEquals(expected,answer);
    }

    @Test
    public void testDoingTransferToCard(){
        repositoryCourseWork.getArrayTrue().put(card2.getNumber(),
                card2);

        int [] answer = serviceCourseWork.doingTransferToCard(transfer);
        int [] expected = {100, 199, 99};
        Assertions.assertArrayEquals(expected,answer);

    }
}
