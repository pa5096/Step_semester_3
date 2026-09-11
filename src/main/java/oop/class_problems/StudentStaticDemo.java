package oop.class_problems;

public class StudentStaticDemo {

    static class BrokenStudent {

        static String name;
        static String regNo;
        static int attendance;

        BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }

    static class Student {

        String name;
        String regNo;
        int attendance;

        static String university = "SRM";
        static int admissionCount = 0;

        Student(String name) {

            this.name = name;

            admissionCount++;

            this.regNo = "RA23110030101" + admissionCount;
        }

        void printIdCard() {
            System.out.println(regNo + " | " + name);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: "
                    + admissionCount);
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent ravi =
                new BrokenStudent("Ravi", "101", 82);

        BrokenStudent meera =
                new BrokenStudent("Meera", "102", 74);

        System.out.println(ravi.name);
        System.out.println(meera.name);

        System.out.println();
        System.out.println("Fixed version:");

        Student student1 =
                new Student("Ravi");

        Student student2 =
                new Student("Meera");

        student1.printIdCard();
        student2.printIdCard();

        Student.printTotalAdmissions();
    }
}