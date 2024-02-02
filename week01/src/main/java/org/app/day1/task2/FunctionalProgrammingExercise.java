package org.app.day1.task2;

import org.app.MyTransformingType;
import org.app.MyValidatingType;

import java.util.ArrayList;
import java.util.List;

public class FunctionalProgrammingExercise {
    public static void main(String[] args) {
        functionalProgramming();
    }
    public static void functionalProgramming(){
        int[] numbers = {1,3,5,7,9};
        for (int i : map(numbers,(int a) -> a * 3)){
            System.out.println(i);
        }
        for (int i : filter(numbers,(int a) -> a == 3)) {
            System.out.println(i);
        }
    }

    public static int[] map(int[] a, MyTransformingType op) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = op.transform(a[i]);
        }
        return b;
    }
    public static int[] filter(int[] a, MyValidatingType op) {
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++){
            if(op.validate(a[i])){
                b.add(a[i]);
            }
        }
        return b.stream().mapToInt(Integer::intValue).toArray();
    }
}
