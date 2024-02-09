package org.app.json.task3;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.Arrays;

public class JSONExercise {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        //Reading all accounts in to an array of Customer
        Customer[] allCustomers = getAccounts();
        //Converting array to list and printing
        System.out.println("----------List of Customer printed out-----------");
        Arrays.stream(allCustomers).toList().forEach(System.out::println);
        //Converting Customer array til a list og CustomerDTO and printing
        System.out.println("----------List of CustomerDTO printed out-----------");
        Arrays.stream(convertCustomersToDTO(allCustomers)).toList().forEach(System.out::println);

    }

    public static Customer[] getAccounts(){
        Customer[] customers = null;
        try(FileReader reader = new FileReader("/Users/lassekh/Documents/Datamatiker/3-semester/3sem-assignments/week02/src/main/java/org/app/json/task2/account.json")){
            customers = gson.fromJson(reader, Customer[].class);
        }catch(Exception e){

        }
        return customers;
    }

    public static CustomerDTO convertCustomerToDTO(Customer c){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFullName(c.getFirstName() + " " + c.getLastName());
        customerDTO.setCity(c.getAddress().getCity());
        customerDTO.setZipCode(c.getAddress().getZipCode());
        customerDTO.setActive(c.getAccount().isActive());
        return customerDTO;
    }

    public static CustomerDTO[] convertCustomersToDTO(Customer[] c){
        CustomerDTO[] customers = new CustomerDTO[c.length];
        for(int i = 0; i < c.length; i++){
            String fullName = c[i].getFirstName() + " " + c[i].getLastName();
            String city = c[i].getAddress().getCity();
            int zipCode = c[i].getAddress().getZipCode();
            boolean isActive = c[i].getAccount().isActive();
            customers[i] = new CustomerDTO(fullName,city,zipCode,isActive);
        }
        return customers;
    }
}
