package com.alibou.demo.tu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleClassT {

    ExampleClass exp = new ExampleClass();

    @Test
    public void should_calculate_the_sum_of_two_strings(){
        int res= exp.add("1","1");

        Assertions.assertEquals(3,res);
    }

    // should not accept characters

    // should not accept doubles as string

    @Test
    public void should_calculate_the_sum_of_two_strings_null(){
        int res= exp.add("1","1");

        Assertions.assertEquals(3,res);
    }


    @Test
    public void should_calculate_the_sum_of_two_ints(){
        int res= exp.add(1,1);

        Assertions.assertEquals(3,res);
    }

    @Test
    public void should_calculate_the_sum_of_two_null_ints(){
        int res= exp.add((String) null,(String) null);

        Assertions.assertEquals(0,res);
    }

    @Test
    public void should_calculate_the_sum_of_first_null_int(){
        int res= exp.add(null,2);

        Assertions.assertEquals(0,res);
    }
    @Test
    public void should_calculate_the_sum_of_second_null_int(){
        int res= exp.add(2,null);

        Assertions.assertEquals(0,res);
    }

    @Test
    public void should_calculate_the_div_of_two_ints(){
        int res= exp.div(4,2);

        Assertions.assertEquals(2,res);
    }

    @Test
    public void should_calculate_the_div_of_two_if_den_is_zero(){
        //int res= exp.div(4,0);

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,()->exp.div(4,0));
        Assertions.assertEquals("b Should not be zero!",exception.getMessage());
    }



}
