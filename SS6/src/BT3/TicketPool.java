package BT3;

public class TicketPool {

    private String roomName;
    private int tickets;
    private int ticketCounter = 1;

    public TicketPool(String roomName, int tickets) {
        this.roomName = roomName;
        this.tickets = tickets;
    }

    public synchronized Ticket sellTicket(String counterName) {

        if (tickets > 0) {
            String code = roomName + "-" + String.format("%03d", ticketCounter++);
            tickets--;

            System.out.println(counterName + " đã bán vé " + code);

            return new Ticket(code);
        }

        return null;
    }

    public synchronized void addTickets(int count) {

        tickets += count;

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public synchronized int getRemainingTickets() {
        return tickets;
    }
}