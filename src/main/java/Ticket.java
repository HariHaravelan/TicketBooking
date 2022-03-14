public class Ticket {
    private String user;

    public Ticket(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("This ticket belongs to %s", user);
    }

    @Override
    public boolean equals(Object obj) {
        return this.user.equals(((Ticket) obj).user);
    }
}