package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.*;
import rw.repository.CarrageRepository;
import rw.util.CarrageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                int count = 0;
                for (int i = 1; i <= numberOfCarrage; i++) {
                    count++;
                    AbstractCarrage carrage = carrageFactory.createCarrage(CarrageType.values()[numberOfType], train);
                    carrage.setTrain(train);
                    carrage.setCarrageNumber(count);
                    carrages.add(carrage);
                }
            }
        }

        return carrages;
    }

    public List<PassangerCarrage> getCarragesByTrain(Train train) {
        return carrageRepository.getCarragesByTrain(train);
    }

    public List<Seat> getSeats(PassangerCarrage carrage) {
        return carrageRepository.getSeats(carrage);
    }

    public PassangerCarrage getCarrageByTrainAndCarrageNumber(Train train, int carrageNumber) {
        return carrageRepository.getCarrageByTrainAndCarrageNumber(train, carrageNumber);
    }

    public Seat getSeatByCarrageAndNumber(PassangerCarrage carrage, String numberOfSeat) {
        return carrageRepository.getSeatByCarrageAndNumber(carrage, numberOfSeat);
    }
}
