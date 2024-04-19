package com.example.finalBookProject.repositories;

import com.example.finalBookProject.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long>  {

    Roles findByRole(String role);
}
