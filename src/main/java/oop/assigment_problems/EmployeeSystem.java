package oop.assigment_problems;

public class EmployeeSystem {

    static class Employee {

        private int empId;
        private String empName;
        private double salary;

        Employee(int empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {

        private double teamBonus;

        ManagerEmployee(int id, String name, double salary, double teamBonus) {
            super(id, name, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {

        private double stipendCap;

        InternEmployee(int id, String name, double salary, double stipendCap) {
            super(id, name, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {

            if (getSalary() < stipendCap) {
                return getSalary();
            }

            return stipendCap;
        }
    }

    public static void main(String[] args) {

        Employee plain =
                new Employee(101, "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee(102, "Aditi", 70000, 8000);

        InternEmployee intern =
                new InternEmployee(103, "Rohan", 12000, 10000);

        if (plain instanceof Employee) {
            System.out.println(
                "Plain employee pay: Rs " + plain.getSalary()
            );
        }

        if (manager instanceof ManagerEmployee) {
            System.out.println(
                "Manager effective pay: Rs "
                + manager.effectiveSalary()
            );
        }

        if (intern instanceof InternEmployee) {
            System.out.println(
                "Intern effective pay: Rs "
                + intern.effectiveSalary()
            );
        }
    }
}