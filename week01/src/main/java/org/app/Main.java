package org.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
public class Main {
    public static void main(String[] args) {
        //---------------------Task one: Lambda expressions-------------------------
        //new Main().lambdaExercise();

        //---------------------Task Two: Functional Programming-----------------------
        //new Main().functionalProgramming();

        //---------------------Task Three: Functional Interfaces-----------------------
        //new Main().functionalInterfacesPredicate();

        //---------------------Task Three: Functional Interfaces-----------------------
        supplierExample();

        //---------------------Task Five: Method references-----------------------
        //See task two map()

    }

    //--------------Task one functions
    public void lambdaExercise() {
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
        System.out.println(operate(numbers1, numbers2, add));

    }
    public int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a,b);
    }
    public int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            numbers.add(op.perform(a[i],b[i]));
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    //---------------Task two functions
    public void functionalProgramming(){
        int[] numbers = {1,3,5,7,9};
        for (int i : map(numbers,(int a) -> tripleUp(a))){
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

    //----------------Task three functions
    public void functionalInterfacesPredicate(){
        //Generate list of number up to 100
        List<Integer> listOfIntegers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }
        //Get a list that only contains numbers that can be divided by seven
        divideBy7(listOfIntegers, (number) -> number % 7 == 0).forEach(System.out::println);
    }
    public List<Integer> divideBy7(List<Integer> numbers, Predicate<Integer> p){
        List<Integer> newListOfIntegers = new ArrayList<>();
        for (int i : numbers) {
            if(p.test(i)){
                newListOfIntegers.add(i);
            }
        }
        return newListOfIntegers;
    }
    public static void supplierExample() {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<Employee> generateEmployee = () -> {
            int randomIndex = new Random().nextInt(names.size());
            int randomAge = new Random().nextInt(60);
            String randomName = names.get(randomIndex);
            return new Employee(randomName, randomAge);
        };
        List<Employee> allEmployees = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            allEmployees.add(generateEmployee.get());
        }

        Consumer<Employee> print = (list) -> System.out.println(list);
        allEmployees.forEach(print);

        List<String> employeeNames = new ArrayList<>();
        Function<List<Employee>, List<String>> convertList = employees -> {
            for(Employee e : employees){
                employeeNames.add(e.getName());
            }
            return employeeNames;
        };
        convertList.apply(allEmployees).forEach(System.out::println);
    }

    //---------------Task five functions

    public static int tripleUp(int i) {
        return i * 3;
    }

}