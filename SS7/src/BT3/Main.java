package BT3;

public class Main {

    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        System.out.println("COD");
        PaymentMethod cod = new CODPayment();
        processor.processPayment(cod, 500000);

        System.out.println("\nThẻ tín dụng");
        PaymentMethod card = new CreditCardPayment();
        processor.processPayment(card, 1000000);

        System.out.println("\nVí MoMo");
        PaymentMethod momo = new MomoPayment();
        processor.processPayment(momo, 750000);

        System.out.println("\nKiểm tra LSP");

        PaymentMethod payment = new CreditCardPayment();
        processor.processPayment(payment, 1000000);

        payment = new MomoPayment();
        processor.processPayment(payment, 750000);

    }
}
