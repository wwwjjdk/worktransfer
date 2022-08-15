package com.example.coursework070822;

import com.example.coursework070822.layers.RepositoryCourseWork;
import com.example.coursework070822.pattern.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRepository {
    private final RepositoryCourseWork repositoryCourseWork  = new RepositoryCourseWork();
    private final Card card = new Card("1111111111111111","12/15","321",100);

    @Test
    public void testSearchCardTrue(){
        repositoryCourseWork.getArrayTrue().put("1111111111111111",
                card);

        String number = "1111111111111111";
        Card cardAnswer = repositoryCourseWork.search(number);

        Assertions.assertEquals(card, cardAnswer);
    }

    @Test
    public void testTransfer(){
        String index = Long.toString(
                repositoryCourseWork.getAtomicLong().incrementAndGet());
        String answer = repositoryCourseWork.transfer(index,"1");
        String expected  = "Operation id:" + "1" + " code: " + "1";

        Assertions.assertEquals(expected,answer);
    }

    @Test
    public void testConfirmOperation(){
        String index = "1";
        String answer = repositoryCourseWork.confirmOperation(index);
        String expected = "Operation id:" + index + " performed correctly";
        Assertions.assertEquals(expected, answer);
    }

    @Test
    public void testCreatCard(){
        repositoryCourseWork.getArrayTrue().clear();
      String answer =   repositoryCourseWork.createCard(card);
      String expected = "map created successfully";

      Assertions.assertEquals(expected,answer);
    }

}
