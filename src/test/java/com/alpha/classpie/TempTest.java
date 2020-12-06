package com.alpha.classpie;


import static org.assertj.core.api.Assertions.*;

import com.alpha.classpie.util.RedisDaoUtil;
import com.alpha.classpie.util.RedisUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author 杨能
 * @create 2020/12/1
 */
public class TempTest {

    @Test
    public void equalsTest(){
        Assertions.assertEquals(8*8,64);
    }

    @Test
    public void catchExceptionTest(){
        Assertions.assertThrows(ArithmeticException.class,()->{int a = 1/0;});
    }

    static class Duck{
        void feed(){}
        void quack(){System.out.print("quack");}
        int eat(DuckFood food){
            return food.getTastiness();
        }
    }


    static class DuckPond {
    public int haveFunAtDuckPond(Duck d,int numFeedings){
        if (d==null||numFeedings<=0)
            return 0;
        int amountOfFun = 0;
        for (int j=0;j<numFeedings;j++){
            amountOfFun++;
            d.feed();
            d.quack();
        }
        return amountOfFun;
    }
}
    static class DuckFood{
        int getTastiness(){
            return 0;
        }
    }


    @Test
    public void testHaveFunDuckPond(){
        DuckPond dp = new DuckPond();
        Duck d = new Duck();
        DuckFood mockDuckFood = Mockito.mock(DuckFood.class);
        Mockito.when(mockDuckFood.getTastiness()).thenReturn(3);
        int happiness = dp.haveFunAtDuckPond(d,mockDuckFood.getTastiness());
        Assertions.assertEquals(3,happiness);
    }

    @Nested
    class DuckPondTest{
        private ByteArrayOutputStream out = new ByteArrayOutputStream();
        @BeforeEach
        public void setUp(){
            System.setOut(new PrintStream(out));
        }
        @AfterEach
        public void tearDown(){
            System.setOut(null);
        }

        @Test
        public void testHaveFunAtDuckPond(){
            DuckPond dp = new DuckPond();
            Duck d = new Duck();
            dp.haveFunAtDuckPond(d,1);
            Assertions.assertEquals("quack!quack!quack!\n",out.toString());
        }

    }
}
