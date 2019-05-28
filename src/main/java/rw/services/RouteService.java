package rw.services;

import rw.entity.Route;
import rw.entity.Train;

import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface RouteService {

    Set<Route> getAllRouts();

    Route createRoute(String arrStation, String depStation, Train train);
}
