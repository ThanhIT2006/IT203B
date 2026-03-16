package BT3;

public class PaymentProcessor {

    public void processPayment(PaymentMethod method, double amount) {

        if (method instanceof CODPayable) {
            ((CODPayable) method).processCOD(amount);

        } else if (method instanceof CardPayable) {
            ((CardPayable) method).processCreditCard(amount);

        } else if (method instanceof EWalletPayable) {
            ((EWalletPayable) method).processMomo(amount);
        }

    }
}
