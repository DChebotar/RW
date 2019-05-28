package rw.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rw.entity.*;
import rw.repository.CarrageRepository;

/**
 * Created by Chebotar_do on 28.05.2019.
 */
@Component
public class CarrageFactory {

    @Autowired
    private CarrageRepository carrageRepository;

    public AbstractCarrage createCarrage(CarrageType carrageType, Train train){
        if (carrageType.equals(CarrageType.FIRSTCLASS_CARRAGE) || carrageType.equals(CarrageType.ECONOMCLASS_CARRAGE)) {
            PassangerCarrage passangerCarrage = new PassangerCarrage(carrageType);
            return passangerCarrage;
        }
        return null;
    }
}
