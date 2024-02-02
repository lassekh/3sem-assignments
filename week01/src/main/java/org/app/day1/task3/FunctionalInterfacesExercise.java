package org.app.day1.task3;

import org.app.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesExercise {
    public static void main(String[] args) {

        //Predicate interface examble
        predicateExamble();

        //Supplier interface example
        supplierExample();
    }
    public static void predicateExamble(){
        //Generate list of number up to 100
        List<Integer> listOfIntegers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }
        //Get a list that only contains numbers that can be divided by seven
        divideBy7(listOfIntegers, (number) -> number % 7 == 0).forEach(System.out::println);
    }
    public static List<Integer> divideBy7(List<Integer> numbers, Predicate<Integer> p){
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
}
