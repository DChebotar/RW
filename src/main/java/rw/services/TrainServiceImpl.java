package rw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.entity.*;
import rw.repository.CarrageRepository;
import rw.repository.RouteRepository;
import rw.repository.TrainRepository;
import rw.repository.UserRepository;

import java.sql.Timestamp;
import java.util.*;

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

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarrageRepository carrageRepository;

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

    public Set<CarrageType> getTypesOfCarrages(Train train){
        Set<CarrageType> typesOfCarrages = new HashSet<CarrageType>();
        List<PassangerCarrage> carrages = carrageService.getCarragesByTrain(train);
        for (AbstractCarrage carrage : carrages){
            typesOfCarrages.add(carrage.getCarrageType());
        }
        return typesOfCarrages;
    }

    public Train getTrainById(long trainid) {
        return trainRepository.getTrainById(trainid);
    }


    public void buyTicket(long id, int numberOfCarrage, String numberOfSeat, User user) {
        Ticket ticket = new Ticket();
        PassangerCarrage carrage = carrageService.getCarrageByTrainAndCarrageNumber(getTrainById(id), numberOfCarrage);
        Seat seat = carrageService.getSeatByCarrageAndNumber(carrage, numberOfSeat);
        ticket.setSeat(seat);
        seat.setStatus(false);
        ticket.setUser(user);
        trainRepository.saveTicket(ticket);
    }

    public List<Ticket> getTicketsByUser(User user) {
        return trainRepository.getTicketsByUser(user);
    }

    public void returnTicketById(long id) {
        Ticket ticket = trainRepository.getTicketById(id);
        ticket.getUser().getTickets().remove(ticket);
        userRepository.saveUser(ticket.getUser());

        ticket.getSeat().setStatus(true);
        carrageRepository.saveSeat(ticket.getSeat());
        trainRepository.deleteTicket(id);

    }

    public Map<CarrageType, Integer> getFreeTickets(Train train){
        Map<CarrageType, Integer> freeTickets = new HashMap<CarrageType, Integer>();
        for (CarrageType carrageType : getTypesOfCarrages(train)){
            freeTickets.put(carrageType, 0);
        }
        for (Map.Entry<CarrageType, Integer> entry : freeTickets.entrySet()) {
            int count = 0;
            List<PassangerCarrage> carrages = carrageService.getCarragesByTrain(train);
            for (PassangerCarrage carrage : carrages) {
                if (entry.getKey().equals(carrage.getCarrageType())){
                    count += carrage.getFreeSeats();
                }
            }
            entry.setValue(count);
        }
        return freeTickets;
    }
}
