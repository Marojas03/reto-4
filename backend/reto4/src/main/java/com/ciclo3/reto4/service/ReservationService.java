package com.ciclo3.reto4.service;

import com.ciclo3.reto4.model.Reservation;
import com.ciclo3.reto4.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Miguel Rojas
 */

/**
 * Clase principal ReservationService marcada como @Service con las anotaciones de Spring
 */

@Service
public class ReservationService {

    /**
     * Instancia de la interface ReservationRepository
     */
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Metodo GET de una lista de Reservation
     * @return reservationRepository
     */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    /**
     * Metodo GET Resrvation que filtra por id, recibiendo dicho parametro
     * @param id
     * @return reservationRepository.getReservation(id)
     */

    public Optional<Reservation> getReservation(int id) {
        return  reservationRepository.getReservation(id);
    }

    /**
     * Metodo para guardar datos que recibe la clase Reservation y los mete en la variable p
     * @param p
     * @return reservationRepository.save(p)
     */

    public Reservation save(Reservation p){
        if(p.getIdReservation() == null){
            return reservationRepository.save(p);
        }else{
            Optional<Reservation> reservationOptional = reservationRepository.getReservation(p.getIdReservation());
            if(reservationOptional.isEmpty()){
                return reservationRepository.save(p);
            }else{
                return p;
            }
        }
    }

    /**
     * Metodo para actualizar datos de la clase Reservation y los mete en la variable r
     * @param r
     * @return reservationRepository.save(g.get())
     */

    public Reservation update(Reservation r){
        if(r.getIdReservation()!=null){
            Optional<Reservation>g=reservationRepository.getReservation(r.getIdReservation());
            if(!g.isEmpty()){
                if(r.getStartDate()!=null){
                    g.get().setStartDate(r.getStartDate());
                }
                if(r.getDevolutionDate() !=null){
                    g.get().setDevolutionDate(r.getDevolutionDate());
                }
                if(r.getStatus() != null){
                    g.get().setStatus(r.getStatus());
                }
                if(r.getScore() !=null){
                    g.get().setScore(r.getScore());
                }
                return reservationRepository.save(g.get());
            }
        }
        return r;
    }

    /**
     * Metodo booleano que borra los registros de la clase Reservation, recibiendo el parametro id
     * @param id
     * @return true o false
     */

    public boolean deleteReservation(int id){
        Optional<Reservation> c = getReservation(id);
        if(!c.isEmpty()){
            reservationRepository.delete((c.get()));
            return true;
        }
        return false;
    }

}//Fin de la clase
