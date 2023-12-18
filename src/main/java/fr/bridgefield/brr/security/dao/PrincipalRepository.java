package fr.bridgefield.brr.security.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bridgefield.brr.security.entity.Principal;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long>{
	
	List<Principal> findByUsername( String username);
}
