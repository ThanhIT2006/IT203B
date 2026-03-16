package BT3;

public class MomoPayment implements EWalletPayable {

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }

}