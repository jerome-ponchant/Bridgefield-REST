package fr.bridgefield.brr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bridgefield.brr.dao.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
