package org.app.day1.task4;

import org.app.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TimeAPIExercise {
    public static void main(String[] args) {
        //List of random employees
        List<Employee> employeeList = employeeData();

        //Calculate age of Employees
        List<Employee> employeeListWithAge = calculateAge(employeeList);

        //Calculate average age of Employees
        calculateAverageAge(employeeListWithAge);

        //Get employees by month they have birthday
        System.out.println("-------Employees with birthday in chosen month------");
        getEmployeesWithBirthdayInMonth(employeeList, 12);

        //Display employees by month they have birthday
        System.out.println("---------Employees by birthday month-----------");
        getEmployeesByMonth(employeeList);
    }
    public static List<Employee> employeeData() {
        //Names to work with in the list
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        //Supplier to generate employee objects
        Supplier<Employee> generateEmployee = () -> {
            int randomIndex = new Random().nextInt(names.size());
            int randomAge = new Random().nextInt(60);
            int randomMonth = new Random().nextInt(12);
            String randomName = names.get(randomIndex);
            LocalDate birthDate = LocalDate.now().minusYears(randomAge).plusMonths(randomMonth);
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

    public static void getEmployeesWithBirthdayInMonth(List<Employee> employees, int month){
        List<Employee> employeesWithBirthdayInMonth = employees.stream()
                .filter(employee -> employee.getBirthDate().getMonthValue() == month)
                .toList();
        if(!employeesWithBirthdayInMonth.isEmpty()){
            employeesWithBirthdayInMonth.forEach(employee ->
                    System.out.println(employee.getName() + " has birthday in month no. " + month));
        }else{
            System.out.println("No employees have birthday in this month");
        }
    }

    public static void getEmployeesByMonth(List<Employee> employees){
        Map<Integer, List<Employee>> employeesByMonth = new HashMap<>();
        for(int i = 1; i <= 12; i++){
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
            birthdays.forEach(employee -> System.out.println(employee.getName()));
        });
    }
}
