package com.nbenzekri.fastsurvey;

import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

enum Position {DEV, DEV_OPS, QA}

/**
 * @author Nouriddin BEN ZEKRI
 * @ This class is just for educational purposes
 */
public class Main {

    public static void main(String[] args) {
        List<Employee> company = createCompany();
        String skills = Main.printEmployeeSkills(company, Position.DEV);
        System.out.println(skills);
        Map<Position, IntSummaryStatistics> salaries = Main.salaryStatistics(company);
        salaries.forEach((position, intSummaryStatistics) -> System.out.println(position + " -> " + intSummaryStatistics));

        System.out.println("Position with lowest average: " + Main.positionWithLowestAverageSalary(company));

        Main.employeesWithDuplicatedFirstName(company).forEach(System.out::println);

    }

    public static Position positionWithLowestAverageSalary(
            List<Employee> employees) {
        return salaryStatistics(employees)
                .entrySet()
                .stream()
                .min(Comparator.comparingDouble(entry -> entry.getValue().getAverage()))
                .get()
                .getKey();
    }

    // like SQL group By
    public static Map<Position, List<Employee>> coutEmplooyesByPosition(List<Employee> employees) {

        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.toList()));
    }

    public static List<String> gatherEmployeeSkills(List<Employee> employees, Position... positions) {
        positions = positions == null || positions.length == 0
                ? Position.values() : positions;
        List<Position> searchPositions = Arrays.stream(positions)
                .collect(Collectors.toList());
        return employees == null ? Collections.emptyList() : employees.stream()
                .filter(employee -> searchPositions.contains(employee.getPosition()))
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static Map<Position, IntSummaryStatistics> salaryStatistics(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition,
                        Collectors.summarizingInt(Employee::getSalary)));
    }

    public static String printEmployeeSkills(List<Employee> employees, Position position) {
        List<String> skills = gatherEmployeeSkills(employees, position);
        return skills.stream()
                .collect(Collectors.joining("; ",
                        "Our " + position + "s have: ", " skills"));
    }

    public static List<Employee> employeesWithDuplicatedFirstName(List<Employee> employees) {

        return employees
                .stream()
                .peek(element -> System.out.println("Before: " + element))
                .collect(Collectors.groupingBy(Employee::getFirstName, Collectors.toList()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue().size() > 1)
                .flatMap(stringListEntry -> stringListEntry.getValue().stream())
                .peek(element -> System.out.println("After: " + element))
                .collect(Collectors.toList());
    }

    public static List<Employee> createCompany() {
        Employee dev1 = new Employee("John", "Doe", Position.DEV, 110);
        dev1.setSkills("C#", "ASP.NET", "React", "AngularJS");
        Employee dev2 = new Employee("Peter", "Doe", Position.DEV, 120);
        dev2.setSkills("Java", "MongoDB", "Dropwizard", "Chef");
        Employee dev3 = new Employee("John", "Smith", Position.DEV, 115);
        dev3.setSkills("Java", "JSP", "GlassFish", "MySql");
        Employee dev4 = new Employee("Brad", "Johston", Position.DEV, 100);
        dev4.setSkills("C#", "MSSQL", "Entity Framework");
        Employee dev5 = new Employee("Philip", "Branson", Position.DEV, 140);
        dev5.setSkills("JavaScript", "React", "AngularJS", "NodeJS");
        Employee dev6 = new Employee("Nathaniel", "Barth", Position.DEV, 99);
        dev6.setSkills("Java", "Dropwizard");
        Employee qa1 = new Employee("Ronald", "Wynn", Position.QA, 100);
        qa1.setSkills("Selenium", "C#", "Java");
        Employee qa2 = new Employee("Erich", "Kohn", Position.QA, 105);
        qa2.setSkills("Selenium", "JavaScript", "Protractor");
        Employee devOps1 = new Employee("Harold", "Jess", Position.DEV_OPS, 116);
        devOps1.setSkills("CentOS", "bash", "c", "puppet", "chef", "Ansible");
        Employee devOps2 = new Employee("Karl", "Madsen", Position.DEV_OPS, 123);
        devOps2.setSkills("Ubuntu", "bash", "Python", "chef");

        return Arrays.asList(dev1, dev2, dev3, dev4, dev5, dev6,
                qa1, qa2, devOps1, devOps2);
    }
}

@Data
@ToString
class Employee {
    private String firstName;
    private String lastName;
    private Position position;
    private List<String> skills;
    private int salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName,
                    Position position, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }


    public void setSkills(String... skills) {
        this.skills = Arrays.stream(skills).collect(Collectors.toList());
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}
