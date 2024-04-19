package com.example.finalBookProject.repositories;

import com.example.finalBookProject.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface CountryRepository extends JpaRepository <Countries, Long> {

}
