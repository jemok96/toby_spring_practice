package springbook.user.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springbook.user.test.template.Calculator;

public class CalculatorTest {
    Calculator calculator;
    String numFilepath;
    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }
    @Test
    public void sumOfNumbers() throws Exception{
        Integer sum = calculator.calcSum(numFilepath);
        System.out.println("sum = " + sum);
        Assertions.assertEquals(10, (int) calculator.calcSum(this.numFilepath));

    }
    @Test
    public void multiplyOfNumbers() throws Exception{
        Integer multiply = calculator.calcMultiply(numFilepath);
        System.out.println("multiply = " + multiply);
        Assertions.assertEquals(24, (int) calculator.calcMultiply(this.numFilepath));


    }

}