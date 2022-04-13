package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

}
