package com.pgrabarczyk.reservations.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pgrabarczyk.reservations.ApplicationConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class Reservation {

    @Id
    @GeneratedValue
    private BigInteger id;

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = ApplicationConfig.PATTERN_DATETIME, timezone = ApplicationConfig.TIMEZONE_WARSAW)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = ApplicationConfig.PATTERN_DATETIME, timezone = ApplicationConfig.TIMEZONE_WARSAW)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    @ManyToOne
    private User owner;

    private String comment;

    @NumberFormat(pattern = ApplicationConfig.PATTERN_CURRENCY)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ReservationStatusEnum status;

    public Reservation(Date createDate, Date expiredDate, User owner, String comment, BigDecimal price,
	    ReservationStatusEnum status) {
	this(null, createDate, expiredDate, owner, comment, price, status);
    }

}
