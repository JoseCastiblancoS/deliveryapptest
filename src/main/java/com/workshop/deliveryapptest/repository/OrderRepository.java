package com.workshop.deliveryapptest.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workshop.deliveryapptest.model.DeliveryOrder;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Integer>{
    
    @Query(value = "Select assigned_to "
            + "from delivery_order "
            + "where created_at > :date "
            + "group by assigned_to "
            + "order by count(*) desc "
            + "limit 1", nativeQuery = true)
    public Optional<String> getPartnerFromTheHour(@Param("date") LocalDateTime date);

}
