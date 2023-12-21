package fr.bridgefield.brr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bridgefield.brr.dao.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
