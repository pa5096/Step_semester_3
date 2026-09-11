package oop.class_problems;

public class StudentAttendance {

    static class Student {

        String name;
        String regNo;
        int attendance;

        Student(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void checkEligibility() {

            if (attendance >= 75) {
                System.out.println(name + " - Hall ticket released");
            } else {
                System.out.println(name + " - Detained");
            }
        }
    }

    public static void main(String[] args) {

        Student s1 = new Student("Ravi", "101", 82);
        Student s2 = new Student("Anitha", "102", 68);

        s1.checkEligibility();
        s2.checkEligibility();
    }
}