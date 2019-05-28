package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.Train;
import rw.repository.RouteRepository;
import rw.repository.TrainRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CarrageService carrageService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;

    public List<Train> getAllTrains() {
        return trainRepository.getAllTrains();
    }

    public void createTrain(String arrStation, String depStation, Map<String, String> numberOfCarrages, Timestamp arTime, Timestamp dpTime) {
        Train train = new Train();

        train.setArrivalTime(arTime);
        train.setDepartureTime(dpTime);

        train.setRoute(routeService.createRoute(arrStation, depStation, train));

        train.setAbstractCarrages(carrageService.getCarragesByFormData(numberOfCarrages, train));

        routeRepository.saveRoute(train.getRoute());
    }
}
