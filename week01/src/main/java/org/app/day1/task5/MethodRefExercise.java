package org.app.day1.task5;

import org.app.day1.task2.FunctionalProgrammingExercise;
import org.app.day1.task3.FunctionalInterfacesExercise;

public class MethodRefExercise {
    public static void main(String[] args) {
        functionalProgramming();
    }
    public static void functionalProgramming(){
        int[] numbers = {1,3,5,7,9};
        for (int i : FunctionalProgrammingExercise.map(numbers, MethodRefExercise::tripleUp)){
            System.out.println(i);
        }
        for (int i : FunctionalProgrammingExercise.filter(numbers, MethodRefExercise::numberEquals3)) {
            System.out.println(i);
        }
    }
    public static int tripleUp(int i) {
        return i * 3;
    }
    public static boolean numberEquals3(int i) { return i == 3; }

}
