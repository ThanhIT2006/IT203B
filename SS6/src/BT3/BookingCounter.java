package BT3;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool pool;
    private int soldCount = 0;

    public BookingCounter(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run() {

        while (true) {

            Ticket ticket = pool.sellTicket(name);

            if (ticket != null) {
                soldCount++;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}