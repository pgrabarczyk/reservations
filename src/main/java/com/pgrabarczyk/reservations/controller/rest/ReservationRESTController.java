package com.pgrabarczyk.reservations.controller.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrabarczyk.reservations.ApplicationConfig;
import com.pgrabarczyk.reservations.exception.RecordNotFoundException;
import com.pgrabarczyk.reservations.model.Reservation;
import com.pgrabarczyk.reservations.model.ReservationStatusEnum;
import com.pgrabarczyk.reservations.repository.ReservationRepository;

@Controller
public class ReservationRESTController extends AbstractRESTController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = "/reservation/findAll")
    public @ResponseBody List<Reservation> findAll() {
	return reservationRepository.findAll();
    }

    @RequestMapping(value = "/reservation/findById")
    public @ResponseBody Reservation findById(@RequestParam BigInteger id) throws RecordNotFoundException {
	Reservation reservation = reservationRepository.findOne(id);
	if (reservation == null) {
	    throw new RecordNotFoundException("Reservation with id " + id + " not found");
	}
	return reservation;
    }

    @RequestMapping(value = "/reservation/findAllByCreateDatesBetween")
    public @ResponseBody List<Reservation> findAllByCreateDatesBetween(
	    @RequestParam @DateTimeFormat(pattern = ApplicationConfig.PATTERN_DATETIME) Date startDate,
	    @RequestParam @DateTimeFormat(pattern = ApplicationConfig.PATTERN_DATETIME) Date endDate) {
	return reservationRepository.findAllByCreateDatesBetween(startDate, endDate);
    }

    @RequestMapping(value = "/reservation/findAllExpiringInMinutes")
    public @ResponseBody List<Reservation> findAllExpiringInMinutes(@RequestParam int minutes) {
	return reservationRepository.findAllExpiringInMinutes(minutes);
    }

    @RequestMapping(value = "/reservation/findAllByUserLastName")
    public @ResponseBody List<Reservation> findAllByUserLastName(@RequestParam String userLastName) {
	return reservationRepository.findAllByUserLastName(userLastName);
    }

    @RequestMapping(value = "/reservation/findAllByUserPesel")
    public @ResponseBody List<Reservation> findAllByUserPesel(@RequestParam String pesel) {
	return reservationRepository.findAllByUserPesel(pesel);
    }

    @RequestMapping(value = "/reservation/findAllByPriceBetween")
    public @ResponseBody List<Reservation> findAllByPriceBetween(
	    @RequestParam @NumberFormat(pattern = ApplicationConfig.PATTERN_CURRENCY) BigDecimal minPrice,
	    @RequestParam @NumberFormat(pattern = ApplicationConfig.PATTERN_CURRENCY) BigDecimal maxPrice) {
	return reservationRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @RequestMapping(value = "/reservation/findAllByStatus")
    public @ResponseBody List<Reservation> findAllByStatus(@RequestParam ReservationStatusEnum status) {
	return reservationRepository.findAllByStatus(status);
    }
}
