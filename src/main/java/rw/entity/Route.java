package rw.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Chebotar_do on 24.05.2019.
 */
@Entity
@Table(name = "ROUTES")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUT_ID", nullable = false)
    private long id;
    @Column(name = "DEP_STATION", nullable = false)
    private String departureStation;
    @Column(name = "ARR_STATION", nullable = false)
    private String arrivalStation;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Train> trains;

    public Route() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (departureStation != null ? !departureStation.equals(route.departureStation) : route.departureStation != null)
            return false;
        if (arrivalStation != null ? !arrivalStation.equals(route.arrivalStation) : route.arrivalStation != null)
            return false;
        return !(trains != null ? !trains.equals(route.trains) : route.trains != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (departureStation != null ? departureStation.hashCode() : 0);
        result = 31 * result + (arrivalStation != null ? arrivalStation.hashCode() : 0);
        result = 31 * result + (trains != null ? trains.hashCode() : 0);
        return result;
    }
}
