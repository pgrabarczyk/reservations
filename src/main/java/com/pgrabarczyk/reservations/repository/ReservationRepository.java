package com.pgrabarczyk.reservations.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pgrabarczyk.reservations.model.Reservation;
import com.pgrabarczyk.reservations.model.ReservationStatusEnum;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, BigInteger> {

    @Query("SELECT r FROM Reservation r WHERE r.createDate BETWEEN :startDate AND :endDate")
    List<Reservation> findAllByCreateDatesBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT r FROM Reservation r WHERE TIME_TO_SEC(TIMEDIFF(r.expiredDate, r.createDate))/60 < :minutes")
    List<Reservation> findAllExpiringInMinutes(@Param("minutes") int minutes);

    @Query("SELECT r FROM Reservation r, User u WHERE r.owner = u AND u.lastName = :userLastName")
    List<Reservation> findAllByUserLastName(@Param("userLastName") String userLastName);

    @Query("SELECT r FROM Reservation r, User u WHERE r.owner = u AND u.pesel = :pesel")
    List<Reservation> findAllByUserPesel(@Param("pesel") String pesel);

    @Query("SELECT r FROM Reservation r WHERE r.price BETWEEN :minPrice AND :maxPrice")
    List<Reservation> findAllByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    List<Reservation> findAllByStatus(@Param("status") ReservationStatusEnum status);

}
