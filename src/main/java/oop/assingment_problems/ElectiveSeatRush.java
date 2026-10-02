package oop.assingment_problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface CreditPolicy {
    int getMaxCredits();
    String getTypeName();
}

class RegularStudentPolicy implements CreditPolicy {
    @Override
    public int getMaxCredits() {
        return 24;
    }

    @Override
    public String getTypeName() {
        return "Regular";
    }
}

class HonorsStudentPolicy implements CreditPolicy {
    @Override
    public int getMaxCredits() {
        return 28;
    }

    @Override
    public String getTypeName() {
        return "Honors";
    }
}

class ExchangeStudentPolicy implements CreditPolicy {
    @Override
    public int getMaxCredits() {
        return 20;
    }

    @Override
    public String getTypeName() {
        return "Exchange";
    }
}

class Student {
    private String name;
    private CreditPolicy creditPolicy;
    private int currentCredits;

    public Student(String name, CreditPolicy creditPolicy, int currentCredits) {
        this.name = name;
        this.creditPolicy = creditPolicy;
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public CreditPolicy getCreditPolicy() {
        return creditPolicy;
    }

    public void addCredits(int credits) {
        this.currentCredits += credits;
    }

    public void deductCredits(int credits) {
        this.currentCredits -= credits;
    }
}

class Elective {
    private String name;
    private int credits;
    private int capacity;
    private List<Student> enrolledStudents = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public void enrollStudent(Student student) {
        int projectedCredits = student.getCurrentCredits() + credits;
        if (projectedCredits > student.getCreditPolicy().getMaxCredits()) {
            System.out.println("Enrollment failed: " + student.getName() + " would exceed the " + 
                    student.getCreditPolicy().getTypeName() + " credit limit (" + 
                    projectedCredits + "/" + student.getCreditPolicy().getMaxCredits() + ").");
            return;
        }

        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            student.addCredits(credits);
            System.out.println(student.getName() + " enrolled in " + name + " (credits: " + 
                    student.getCurrentCredits() + "/" + student.getCreditPolicy().getMaxCredits() + ").");
        } else {
            if (waitlist.isEmpty()) {
                System.out.println(name + " is full.");
            }
            waitlist.add(student);
            System.out.println(student.getName() + " added to waitlist (position " + waitlist.size() + ").");
        }
    }

    public void dropStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            student.deductCredits(credits);
            System.out.println(student.getName() + " dropped " + name + " (credits: " + 
                    student.getCurrentCredits() + "/" + student.getCreditPolicy().getMaxCredits() + ").");

            promoteNextWaitlistedStudent();
        }
    }

    private void promoteNextWaitlistedStudent() {
        while (!waitlist.isEmpty()) {
            Student nextStudent = waitlist.poll();
            int projectedCredits = nextStudent.getCurrentCredits() + credits;
            if (projectedCredits <= nextStudent.getCreditPolicy().getMaxCredits()) {
                enrolledStudents.add(nextStudent);
                nextStudent.addCredits(credits);
                System.out.println(nextStudent.getName() + " promoted from waitlist and enrolled in " + name + 
                        " (credits: " + nextStudent.getCurrentCredits() + "/" + nextStudent.getCreditPolicy().getMaxCredits() + ").");
                return;
            }
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective cloudComputing = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularStudentPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsStudentPolicy(), 22);
        Student neha = new Student("Neha", new ExchangeStudentPolicy(), 12);
        Student kiran = new Student("Kiran", new RegularStudentPolicy(), 22);

        cloudComputing.enrollStudent(asha);
        cloudComputing.enrollStudent(ravi);
        cloudComputing.enrollStudent(neha);
        cloudComputing.enrollStudent(kiran);

        cloudComputing.dropStudent(asha);
    }
}