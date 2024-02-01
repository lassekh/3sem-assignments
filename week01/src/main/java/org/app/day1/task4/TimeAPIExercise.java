package org.app.day1.task4;

import org.app.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Supplier;

public class TimeAPIExercise {
    public static void main(String[] args) {
        List<Employee> employeeList = employeeData();
        //Calculate age of Employees
        List<Employee> employeeListWithAge = calculateAge(employeeList);

        //Calculate average age of Employees
        calculateAverageAge(employeeListWithAge);

        //Display employees by month they have birthday
        getEmployeesByMonth(employeeList);
    }
    public static List<Employee> employeeData() {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<Employee> generateEmployee = () -> {
            int randomIndex = new Random().nextInt(names.size());
            int randomAge = new Random().nextInt(60);
            String randomName = names.get(randomIndex);
            LocalDate birthDate = LocalDate.now().minusYears(randomAge);
            return new Employee(randomName, birthDate);
        };
        List<Employee> allEmployees = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            allEmployees.add(generateEmployee.get());
        }
        return allEmployees;
    }

    public static List<Employee> calculateAge(List<Employee> employees){
        int age;
        System.out.println("--------Calculated age of each Employee---------");
        for(Employee e : employees){
            LocalDate birthDate = e.getBirthDate();
            age = Period.between(birthDate,LocalDate.now()).getYears();
            e.setAge(age);
            System.out.println(e.getAge());
        }
        return employees;
    }

    public static void calculateAverageAge(List<Employee> employees){
        int totalAge = 0;
        for(Employee e : employees){
            totalAge = totalAge + e.getAge();
        }
        int average = totalAge/employees.size();
        System.out.println("--------Average age of employees---------");
        System.out.println(average);
    }

    public static void getEmployeesByMonth(List<Employee> employees){
        Map<Integer, List<Employee>> employeesByMonth = new HashMap<>();
        for(int i = 1; i < 12; i++){
            List<Employee> month = new ArrayList<>();
            for(Employee e : employees){
                if(e.getBirthDate().getMonthValue() == i){
                    month.add(e);
                }
            }
            employeesByMonth.put(i,month);
        }
        employeesByMonth.forEach((month,birthdays) -> {
            System.out.println(month);
            birthdays.forEach(System.out::println);
        });
    }
}
