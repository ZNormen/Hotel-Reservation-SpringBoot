package com.normenzorgo.lil.learningspring.webservice;

import com.normenzorgo.lil.learningspring.business.ReservationService;
import com.normenzorgo.lil.learningspring.business.RoomReservation;
import com.normenzorgo.lil.learningspring.data.Guest;
import com.normenzorgo.lil.learningspring.data.Room;
import com.normenzorgo.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }


    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path="/guests", method = RequestMethod.GET) //@GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @RequestMapping(path="/rooms", method = RequestMethod.GET) //@GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getHotelRooms();
    }

//    @PostMapping(path="/guests")
//    public List<Guest> newGuest(String firstName, String lastName, String emailAddress, String address, String country, String state, String phoneNumber) {
//        return this.reservationService.addGuest(firstName, lastName, emailAddress, address, country, state, phoneNumber);
//    }

    @PostMapping(path="/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }



}
