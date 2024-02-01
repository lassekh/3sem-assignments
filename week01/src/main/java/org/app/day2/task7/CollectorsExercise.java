package org.app.day2.task7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsExercise {
    public static void main(String[] args) {
        List<Transaction> transactions = transactionData();

        //Total sum of all transactions
        double totalAmountOfTransactions = transactions.stream()
                .collect(Collectors.summingDouble(Transaction::getAmount));
        System.out.println(totalAmountOfTransactions);

        //Total sum of transactions grouped by currency
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency))
                .forEach((currency, transactions1) -> {
                    System.out.println(currency);
                    double amount = transactions1.stream()
                            .collect(Collectors.summingDouble(Transaction::getAmount));
                    System.out.println(amount);
                });
    }
    public static List<Transaction> transactionData(){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1,100,"DKK"));
        transactions.add(new Transaction(2,1000,"DKK"));
        transactions.add(new Transaction(3,2200,"USD"));
        transactions.add(new Transaction(4,200.5,"USD"));
        transactions.add(new Transaction(5,555,"DKK"));
        transactions.add(new Transaction(6,777,"DKK"));
        transactions.add(new Transaction(7,700,"DKK"));
        transactions.add(new Transaction(8,1000,"DKK"));
        transactions.add(new Transaction(9,100,"USD"));
        transactions.add(new Transaction(10,1000,"DKK"));
        transactions.add(new Transaction(11,2200,"DKK"));
        transactions.add(new Transaction(12,200.5,"USD"));
        transactions.add(new Transaction(13,555,"DKK"));
        transactions.add(new Transaction(14,777,"DKK"));
        transactions.add(new Transaction(15,700,"USD"));
        transactions.add(new Transaction(16,1000,"EUR"));
        transactions.add(new Transaction(17,1000,"DKK"));
        transactions.add(new Transaction(18,100,"USD"));
        transactions.add(new Transaction(19,110,"EUR"));
        transactions.add(new Transaction(20,22000,"DKK"));
        transactions.add(new Transaction(21,200.5,"USD"));
        transactions.add(new Transaction(22,555,"DKK"));
        transactions.add(new Transaction(23,777,"EUR"));
        transactions.add(new Transaction(24,700,"USD"));
        transactions.add(new Transaction(25,10000,"DKK"));
        return transactions;
    }
}
