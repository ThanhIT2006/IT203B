package BT1;

public class BookingCounter implements Runnable {
    private String name;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean lockAFirst;

    public BookingCounter(String name, TicketPool roomA, TicketPool roomB, boolean lockAFirst) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
        this.lockAFirst = lockAFirst;
    }

    public void sellCombo() {
        if (lockAFirst) {
            synchronized (roomA) {
                System.out.println(name + ": Đã lấy vé phòng A");
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (roomB) {
                    processSale();
                }
            }
        } else {
            synchronized (roomB) {
                System.out.println(name + ": Đã lấy vé phòng B");
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (roomA) {
                    processSale();
                }
            }
        }
    }

    private void processSale() {
        if (roomA.hasTicket() && roomB.hasTicket()) {
            String ticketA = roomA.getTicket();
            String ticketB = roomB.getTicket();
            System.out.println(name + " bán combo thành công: " + ticketA + " & " + ticketB);
        } else {
            System.out.println(name + ": Hết vé, bán combo thất bại");
        }
    }

    @Override
    public void run() {
        sellCombo();
    }
}
