package com.simple.serviceDeskApplication.repository;

import com.simple.serviceDeskApplication.model.TicketBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketBO,Long>{
    List<TicketBO> findAllByActiveIsTrue();
}
