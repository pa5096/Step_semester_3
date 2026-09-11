package oop.assigment_problems;

public class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid membership ID");
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public"))
            return "ALLOWED";
        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        if (fieldModifier.equals("default"))
            return accessorContext.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";
        if (fieldModifier.equals("protected"))
            return accessorContext.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";
        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (String modifier : modifiers) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifier)) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (!result.isEmpty())
                result += " | ";

            result += modifier + ": " + allowed + " allowed / " + denied + " denied";
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}