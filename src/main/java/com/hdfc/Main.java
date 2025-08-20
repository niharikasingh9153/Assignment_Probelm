package com.hdfc;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 60000),
                new Employee(2, "Bob", "Finance", 45000),
                new Employee(3, "Charlie", "IT", 70000),
                new Employee(4, "David", "HR", 40000),
                new Employee(5, "Eva", "Finance", 80000)
        );
        List<String> names= employees.stream()
                        .filter(e->e.getSalary()>50000)
                                .map(Employee::getName)
                                        .collect(Collectors.toList());
        System.out.println("Employees having salary greater than 50000 -> "+names);

        List<String> NamesInUpperCase=employees.stream()
                .map(f->f.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Employees Names in UpperCase -> "+NamesInUpperCase);

        System.out.println("Grouping according to dept "+employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList()))));

        List<String> sortedSal=employees.stream()
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .map(Employee::getName)
                                        .collect(Collectors.toList());
        System.out.println("Employees names in sorted salary order -> "+sortedSal);

        System.out.println("average salary -> "+ employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary)));

        Optional<Employee> firstEmp = Optional.ofNullable(employees)   // handles null list
                .flatMap(list -> list.stream().findFirst());           // handles empty list

// ✅ Safely use it
        firstEmp.ifPresent(e -> System.out.println("First Employee: " + e.getName()));




        CalculateBonus calculateBonus = new CalculateBonus();
        List<Double> updatedSal= employees.stream()
              .map(Employee::getSalary)
                .map(e->calculateBonus.calculate(e))
                    .collect(Collectors.toList());
        System.out.println("Updated salary -> "+updatedSal);

    }
}