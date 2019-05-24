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
    @JoinColumn(name = "TRAIN_CAR_ID", nullable = false)
    private TrainCar trainCar;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = true)
    private User user;

    public Seat(boolean status, String number, TrainCar trainCar) {
        this.status = status;
        this.number = number;
        this.trainCar = trainCar;
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

    public TrainCar getTrainCar() {
        return trainCar;
    }

    public void setTrainCar(TrainCar trainCar) {
        this.trainCar = trainCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (id != seat.id) return false;
        if (status != seat.status) return false;
        if (number != null ? !number.equals(seat.number) : seat.number != null) return false;
        return !(trainCar != null ? !trainCar.equals(seat.trainCar) : seat.trainCar != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (trainCar != null ? trainCar.hashCode() : 0);
        return result;
    }
}
