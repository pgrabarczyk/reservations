package com.pgrabarczyk.reservations.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pgrabarczyk.reservations.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

}
