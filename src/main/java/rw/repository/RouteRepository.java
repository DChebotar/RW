package rw.repository;

import rw.entity.Route;

import java.util.Set;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface RouteRepository {

    Set<Route> getAllRouts();

    void saveRoute(Route route);
}
