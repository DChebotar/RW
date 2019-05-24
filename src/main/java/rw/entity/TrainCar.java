package rw.entity;



import javax.persistence.*;
import java.util.Set;

/**
 * Created by Chebotar_do on 24.05.2019.
 */
@Entity
@Table(name = "TRAIN_CAR")
public class TrainCar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRAIN_CAR_ID", nullable = false)
    private long id;
    @ElementCollection(targetClass = TrainCarType.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "TRAIN_CAR_TYPE", joinColumns = @JoinColumn(name = "TRAIN_CAR_ID", nullable = false))
    private TrainCarType trainCarType;
    @Column(name = "TRAIN_CAR_TRAIN", nullable = false)
    private Train train;
    @Column(name = "NAMBER_OF_SEATS", nullable = false)
    private int numberOfSeats;
    @OneToMany(mappedBy = "trainCar", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Seat> seats;

    public TrainCar() {
        if (trainCarType.equals(TrainCarType.ECONOMCLASS)){
            numberOfSeats = 75;
        }
        if (trainCarType.equals(TrainCarType.FIRSTCLASS)){
            numberOfSeats = 50;
        }
        for (int i = 0; i < numberOfSeats; i++) {
            seats.add(new Seat(true, String.valueOf(i+1), this));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TrainCarType getTrainCarType() {
        return trainCarType;
    }

    public void setTrainCarType(TrainCarType trainCarType) {
        this.trainCarType = trainCarType;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainCar trainCar = (TrainCar) o;

        if (id != trainCar.id) return false;
        if (numberOfSeats != trainCar.numberOfSeats) return false;
        if (trainCarType != trainCar.trainCarType) return false;
        if (train != null ? !train.equals(trainCar.train) : trainCar.train != null) return false;
        return !(seats != null ? !seats.equals(trainCar.seats) : trainCar.seats != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (trainCarType != null ? trainCarType.hashCode() : 0);
        result = 31 * result + (train != null ? train.hashCode() : 0);
        result = 31 * result + numberOfSeats;
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        return result;
    }
}
