package BT2;

public class BookingCounter extends Thread {
    private String name;
    private TicketPool pool;

    public BookingCounter(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run() {
        while (true) {
            pool.sellTicket(name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}
