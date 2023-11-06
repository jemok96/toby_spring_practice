package springbook.user.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void sumOfNumbers() throws Exception{
        Calculator calculator = new Calculator();
        Integer sum = calculator.CalcSum(getClass().getResource("/numbers.txt").getPath());
        System.out.println("sum = " + sum);
    }
}