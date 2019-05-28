package rw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

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
    private List<AbstractCarrage> carrages;

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

    public List<AbstractCarrage> getAbstractCarrages() {
        return carrages;
    }

    public void setAbstractCarrages(List<AbstractCarrage> carrages) {
        this.carrages = carrages;
    }

    public Set<CarrageType> getTypesOfCarrages(){
        Set<CarrageType> typesOfCarrages = new HashSet<CarrageType>();
        for (AbstractCarrage carrage : this.getAbstractCarrages()){
            typesOfCarrages.add(carrage.getCarrageType());
        }
        return typesOfCarrages;
    }

    public Map<CarrageType, Integer> getFreeTickets(){
        Map<CarrageType, Integer> freeTickets = new HashMap<CarrageType, Integer>();
        for (CarrageType carrageType : this.getTypesOfCarrages()){
            freeTickets.put(carrageType, 0);
        }
        for (Map.Entry<CarrageType, Integer> entry : freeTickets.entrySet()) {
            int count = 0;
            for (AbstractCarrage carrage : this.getAbstractCarrages()) {
                PassangerCarrage pCarrage = (PassangerCarrage) carrage;
                if (entry.getKey().equals(carrage.getCarrageType())){
                    count += pCarrage.getFreeSeats();
                }
            }
            entry.setValue(count);
        }
        return freeTickets;
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
        return !(carrages != null ? !carrages.equals(train.carrages) : train.carrages != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}
