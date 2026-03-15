package BT2;

public class TicketPool {
    private String roomName;
    private int tickets;
    private int ticketCounter = 1;

    public TicketPool(String roomName, int tickets) {
        this.roomName = roomName;
        this.tickets = tickets;
    }

    public synchronized String sellTicket(String counterName) {
        while (tickets == 0) {
            try {
                System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String ticket = roomName + "-" + String.format("%03d", ticketCounter++);
        tickets--;
        System.out.println(counterName + " bán vé " + ticket);
        return ticket;
    }

    public synchronized void addTickets(int amount) {
        tickets += amount;
        System.out.println("Nhà cung cấp: Đã thêm " + amount + " vé vào phòng " + roomName);
        notifyAll();
    }
}