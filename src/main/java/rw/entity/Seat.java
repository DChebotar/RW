package rw.entity;

import javax.persistence.*;

/**
 * Created by Chebotar_do on 24.05.2019.
 */
@Entity
@Table(name = "SEATS")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEAT_ID", nullable = false)
    private long id;
    @Column(name = "SEAT_STATUS", nullable = false)
    private boolean status;
    @Column(name = "SEAT_NUMBER", nullable = false)
    private String number;
    @ManyToOne
    @JoinColumn(name = "CARRAGE_ID", nullable = false)
    private AbstractCarrage carrage;
    @OneToOne(mappedBy = "seat")
    private Ticket ticket;

    public Seat(boolean status, String number, PassangerCarrage trainCar) {
        this.status = status;
        this.number = number;
        this.carrage = trainCar;
    }

    public Seat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AbstractCarrage getCarrage() {
        return carrage;
    }

    public void setCarrage(PassangerCarrage carrage) {
        this.carrage = carrage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (id != seat.id) return false;
        if (status != seat.status) return false;
        if (number != null ? !number.equals(seat.number) : seat.number != null) return false;
        if (carrage != null ? !carrage.equals(seat.carrage) : seat.carrage != null) return false;
        return !(ticket != null ? !ticket.equals(seat.ticket) : seat.ticket != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}
