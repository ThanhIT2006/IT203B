package BT2;

public class Supplier extends Thread {
    private TicketPool pool;
    public Supplier(TicketPool pool) {
        this.pool = pool;
    }
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        pool.addTickets(3);
    }
}