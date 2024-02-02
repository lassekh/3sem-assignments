package org.app.day1.task1;

import org.app.ArithmeticOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExercise {
    public static void main(String[] args) {
        lambdaExercise();
    }
    public static void lambdaExercise() {
        //addition
        ArithmeticOperation add = (int a, int b) -> (a + b);
        //subtraction
        ArithmeticOperation subtract = (int a, int b) -> (a - b);
        //multiplication
        ArithmeticOperation multiply = (int a, int b) -> (a * b);
        //division
        ArithmeticOperation divide = (int a, int b) -> (a / b);
        //modulus
        ArithmeticOperation remainder = (int a, int b) -> (a % b);
        //power
        ArithmeticOperation power = (int a, int b) -> ((int)Math.pow(a, b));

        System.out.println(operate(5, 5, add));
        int[] numbers1 = {1,2,3,4,5,6,7,8,9};
        int[] numbers2 = {2,2,2,2,2,2,2,2,2};
        System.out.println(Arrays.toString(operate(numbers1, numbers2, add)));
    }
    public static int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a,b);
    }

    public static int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            numbers.add(op.perform(a[i],b[i]));
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
