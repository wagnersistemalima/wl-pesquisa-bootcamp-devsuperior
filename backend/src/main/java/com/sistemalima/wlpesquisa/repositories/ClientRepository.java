package com.sistemalima.wlpesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalima.wlpesquisa.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
