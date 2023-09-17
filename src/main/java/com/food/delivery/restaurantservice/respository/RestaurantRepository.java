package com.food.delivery.restaurantservice.respository;

import com.food.delivery.restaurantservice.dto.Status;
import com.food.delivery.restaurantservice.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    //@Query("select r from restaurant r where r.status=:status")
    List<Restaurant> findAllByStatus(String status, Pageable paging);

    @Transactional
    @Query("select r from restaurant r where r.name like %:name% and r.status=:status")
    List<Restaurant> findAllByNameAndStatus(String name, String status, Pageable paging);
}
