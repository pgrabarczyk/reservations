package com.pgrabarczyk.reservations.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import com.pgrabarczyk.reservations.model.Reservation;
import com.pgrabarczyk.reservations.model.ReservationStatusEnum;
import com.pgrabarczyk.reservations.model.User;

public class TestData {

    public static final Date dt_2001_01_01 = new GregorianCalendar(2001, 1, 1, 1, 1).getTime();
    public static final Date dt_2002_02_02 = new GregorianCalendar(2002, 2, 2, 2, 2).getTime();

    public static final User userMarian = new User(new BigInteger("1"), "Andrew", "Kowalski", "90112200800");

    public static final Reservation reservationMarian1 = new Reservation(new BigInteger("1"), dt_2001_01_01,
	    dt_2002_02_02, userMarian, "Comment reservationMarian1", new BigDecimal("1234.99"),
	    ReservationStatusEnum.NEW);

    static {
	userMarian.getReservations().add(reservationMarian1);
    }

}
