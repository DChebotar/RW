package rw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Chebotar_do on 24.05.2019.
 */
@Entity
@Table(name = "TRAINS")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRAIN_ID", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "ROUTE_ID", nullable = false)
    private Route route;
    @Column(name = "TRAIN_ARR_TS", nullable = false)
    private Timestamp arrivalTime;
    @Column(name = "TRAIN_DEP_TS", nullable = false)
    private Timestamp departureTime;
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<TrainCar> trainCars;

    public Train() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Set<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(Set<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (id != train.id) return false;
        if (route != null ? !route.equals(train.route) : train.route != null) return false;
        if (arrivalTime != null ? !arrivalTime.equals(train.arrivalTime) : train.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(train.departureTime) : train.departureTime != null)
            return false;
        return !(trainCars != null ? !trainCars.equals(train.trainCars) : train.trainCars != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (trainCars != null ? trainCars.hashCode() : 0);
        return result;
    }
}
