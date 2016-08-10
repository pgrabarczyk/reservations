package com.pgrabarczyk.reservations.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "reservations")
public @Data class User {

    @Id
    @GeneratedValue
    private BigInteger id;
    private String firstName;
    private String lastName;
    private String pesel;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Reservation> reservations = new ArrayList<>();

    public User(String firstName, String lastName, String pesel) {
	this(null, firstName, lastName, pesel);
    }

    public User(BigInteger id, String firstName, String lastName, String pesel) {
	this(id, firstName, lastName, pesel, new ArrayList<Reservation>());
    }

}
