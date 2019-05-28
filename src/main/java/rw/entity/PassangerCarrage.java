package rw.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Entity
@DiscriminatorValue("PAS_CARRAGE")
@SecondaryTable(name = "PASSANGER_CARRAGE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID"))
public class PassangerCarrage extends AbstractCarrage {

    @Column(name = "NUMBER_OF_SEATS", nullable = false)
    private int numberOfSeats;

    @OneToMany(mappedBy = "carrage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Seat> seats;

    public PassangerCarrage(){}

    public PassangerCarrage(CarrageType carrageType) {
        super(carrageType);
        if (carrageType.equals(CarrageType.ECONOMCLASS_CARRAGE)){
            numberOfSeats = 75;
        }
        if (carrageType.equals(CarrageType.FIRSTCLASS_CARRAGE)){
            numberOfSeats = 50;
        }
        seats = new HashSet<Seat>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(true, String.valueOf(i), this));
        }
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats){
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getFreeSeats(){
        int count = 0;
        for (Seat seat : this.getSeats()){
            if (seat.isStatus() == false){
                count++;
            }
        }
        return count;
    }
}
