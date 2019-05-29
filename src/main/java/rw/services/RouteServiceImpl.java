package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.entity.Route;
import rw.entity.Train;
import rw.repository.RouteRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    public Set<Route> getAllRouts() {
        return routeRepository.getAllRouts();
    }

    public Route createRoute(String arrStation, String depStation, Train train) {
        Route route = new Route();
        route.setArrivalStation(arrStation);
        route.setDepartureStation(depStation);
        Set<Train> trains = new HashSet<Train>();
        trains.add(train);
        route.setTrains(trains);

        return route;
    }
}
