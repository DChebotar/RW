package rw.entity;

import javax.persistence.*;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKET_ID", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SEAT_ID", nullable = false, referencedColumnName = "SEAT_ID")
    private Seat seat;

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (user != null ? !user.equals(ticket.user) : ticket.user != null) return false;
        return !(seat != null ? !seat.equals(ticket.seat) : ticket.seat != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}
