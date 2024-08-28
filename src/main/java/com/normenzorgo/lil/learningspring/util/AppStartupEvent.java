package com.normenzorgo.lil.learningspring.util;

import java.util.Date;
import java.util.List;

import com.normenzorgo.lil.learningspring.business.ReservationService;
import com.normenzorgo.lil.learningspring.business.RoomReservation;
import com.normenzorgo.lil.learningspring.data.Guest;
import com.normenzorgo.lil.learningspring.data.Reservation;
import com.normenzorgo.lil.learningspring.data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);
    }
}
