package oop.assigment_problems;

public class LibraryMember {
    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class FixedLibraryMember {
        String name;
        String memberId;
        int booksIssued;
        static String libraryName = "City Library";
        static int memberCount = 0;

        FixedLibraryMember(String name) {
            this.name = name;
            memberCount++;
            this.memberId = "LM-100" + memberCount;
            booksIssued = 0;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
            new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
            new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);

        System.out.println();
        System.out.println("Fixed version:");

        FixedLibraryMember m1 =
            new FixedLibraryMember("Aditi");

        FixedLibraryMember m2 =
            new FixedLibraryMember("Rohan");

        m1.printMemberCard();
        m2.printMemberCard();

        FixedLibraryMember.printTotalMembers();
    }
}