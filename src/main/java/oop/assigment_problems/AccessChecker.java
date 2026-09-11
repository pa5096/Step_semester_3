package oop.assigment_problems;

public class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }

            if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                return "DENIED";
            }

            return accessorContext.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] parts = accessorContext.split("_");
        String result = "";

        for (String part : parts) {
            result += part.substring(0, 1).toUpperCase()+ part.substring(1).toLowerCase() + " ";
        }

        return result.trim();
    }

    public static void main(String[] args) {
        System.out.println(
            classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
            classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")
        );

        System.out.println(
            describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );
    }
}