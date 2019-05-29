package rw.entity;

import javax.persistence.*;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Entity
@Table(name = "CARRAGES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE", discriminatorType = DiscriminatorType.STRING)
public class AbstractCarrage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CARRAGE_ID", nullable = false)
    private long id;
    @Enumerated
    @Column(name = "CARRAGE_TYPE", columnDefinition = "smallint")
    private CarrageType carrageType;
    @ManyToOne
    @JoinColumn(name = "TRAIN_ID", nullable = false)
    private Train train;

    public AbstractCarrage() {
    }

    public AbstractCarrage(CarrageType carrageType) {
        this.carrageType = carrageType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarrageType getCarrageType() {
        return carrageType;
    }

    public void setCarrageType(CarrageType carrageType) {
        this.carrageType = carrageType;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCarrage that = (AbstractCarrage) o;

        if (id != that.id) return false;
        if (carrageType != that.carrageType) return false;
        return !(train != null ? !train.equals(that.train) : that.train != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}
