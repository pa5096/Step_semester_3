package oop.class_problems;

abstract class PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    public PaymentMethod() {
        counter++;
        this.transactionId = "TXN-" + counter;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        super();
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {
    public CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " + getTransactionId();
    }
}

public class CheckoutPaymentHandler {
    public static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        // Direct instantiation check:
        // PaymentMethod pm = new PaymentMethod(); // Will NOT compile because PaymentMethod is abstract.

        System.out.println("--- Test 1: CreditCardPayment ---");
        CreditCardPayment cc = new CreditCardPayment("4471");
        System.out.println(cc.processPayment(250.0));

        System.out.println("\n--- Test 2: CashPayment ---");
        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        System.out.println("\n--- Test 3: Overloaded processPayment with Note ---");
        System.out.println(cc.processPayment(250.0, "Birthday gift"));

        System.out.println("\n--- Test 4: Upcasting & Polymorphic Print Confirmation ---");
        // Upcasting: Storing a CreditCardPayment reference in a PaymentMethod-typed variable
        PaymentMethod ref = cc; 
        printConfirmation(ref, 250.0);
    }
}