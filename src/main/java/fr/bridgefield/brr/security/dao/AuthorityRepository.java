package fr.bridgefield.brr.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bridgefield.brr.security.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

	List<Authority> findByName( String Name);
}
