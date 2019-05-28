package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.AbstractCarrage;
import rw.entity.CarrageType;
import rw.entity.Train;
import rw.repository.CarrageRepository;
import rw.util.CarrageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Service
@Transactional
public class CarrageServiceImpl implements CarrageService {

    @Autowired
    private CarrageRepository carrageRepository;

    @Autowired
    private CarrageFactory carrageFactory;

    public List<AbstractCarrage> getCarragesByFormData(Map<String, String> numberOfCarrages, Train train) {
        List<AbstractCarrage> carrages = new ArrayList<AbstractCarrage>();
        for (Map.Entry<String, String> entry : numberOfCarrages.entrySet()){
            if (entry.getKey().startsWith("numberOfCarrages")){
                int numberOfType = Integer.parseInt(entry.getKey().substring(16));
                int numberOfCarrage = Integer.parseInt(entry.getValue());
                for (int i = 0; i < numberOfCarrage; i++) {
                    AbstractCarrage carrage = carrageFactory.createCarrage(CarrageType.values()[numberOfType], train);
                    carrage.setTrain(train);
                    carrages.add(carrage);
                }
            }
        }

        return carrages;
    }
}
