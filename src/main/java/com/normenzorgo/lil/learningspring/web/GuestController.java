package com.normenzorgo.lil.learningspring.web;

import com.normenzorgo.lil.learningspring.business.ReservationService;
import com.normenzorgo.lil.learningspring.business.RoomReservation;
import com.normenzorgo.lil.learningspring.data.Guest;
import com.normenzorgo.lil.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        model.addAttribute("guests", this.reservationService.getHotelGuests());
        return "hotel-guests";
    }

}
