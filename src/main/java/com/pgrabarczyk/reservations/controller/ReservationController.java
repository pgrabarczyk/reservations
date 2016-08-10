package com.pgrabarczyk.reservations.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ReservationController {

    @RequestMapping(value = "/index")
    public String index(Model model) {
	List<String> methods = Arrays.asList(
		"/reservation/findAll.json", "/reservation/findById.json?id=2",
		"/reservation/findAllByCreateDatesBetween.json?startDate=2000-01-01%2001:00:00.000&endDate=2020-01-01%2001:00:00.000",
		"/reservation/findAllExpiringInMinutes.json?minutes=1",
		"/reservation/findAllByUserLastName.json?userLastName=Kowalski",
		"/reservation/findAllByUserPesel.json?pesel=90112200800",
		"/reservation/findAllByPriceBetween.json?minPrice=10.0&maxPrice=100",
		"/reservation/findAllByStatus.json?status=NEW");
	model.addAttribute("methods", methods);
	return "reservation";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String handleAllExceptions(Exception e) {
	return "redirect:/error.html";
    }

}
