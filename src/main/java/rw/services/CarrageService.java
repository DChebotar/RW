package rw.services;

import rw.entity.AbstractCarrage;
import rw.entity.Train;

import java.util.List;
import java.util.Map;

/**
 * Created by Chebotar_do on 27.05.2019.
 */
public interface CarrageService {

    List<AbstractCarrage> getCarragesByFormData(Map<String, String> numberOfCarrages, Train train);
}
