package string.class_problems;

public class StringBasicsDemo {
    public static void main(String[] args) {

        String a = "Java";
        String b = "Java";

        System.out.println(a == b);

        String c = new String("Java");

        System.out.println(a == c);
        System.out.println(a.equals(c));

        String greeting = "Hello";
        greeting = greeting + " World";

        System.out.println(greeting);
    }
}