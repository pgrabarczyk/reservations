package com.pgrabarczyk.reservations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pgrabarczyk.reservations.model.Reservation;
import com.pgrabarczyk.reservations.model.ReservationStatusEnum;
import com.pgrabarczyk.reservations.model.User;
import com.pgrabarczyk.reservations.repository.ReservationRepository;
import com.pgrabarczyk.reservations.repository.UserRepository;

@Component
public class Bootstrap {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @PostConstruct
    public void bootstrap() {
	System.out.println("[Bootstrap] Start working.");

	User marian = new User("Andrew", "Kowalski", "90112200800");
	userRepository.save(marian);

	List<Reservation> marianReservationList = Arrays.asList(new Reservation(createDate, expiredDate, marian,
		"Comment reservationMarian1", new BigDecimal("15.00"), ReservationStatusEnum.NEW));
	for (Reservation reservation : marianReservationList) {
	    reservationRepository.save(reservation);
	}

	System.out.println("[Bootstrap] Finish working.");
    }

    private final Date createDate = new GregorianCalendar(2001, 1, 1, 1, 1).getTime();
    private final Date expiredDate = new GregorianCalendar(2002, 2, 2, 2, 2).getTime();
}
