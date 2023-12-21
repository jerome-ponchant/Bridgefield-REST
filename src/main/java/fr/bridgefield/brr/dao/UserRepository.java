package fr.bridgefield.brr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bridgefield.brr.dao.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
