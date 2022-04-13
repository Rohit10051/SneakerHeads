package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
