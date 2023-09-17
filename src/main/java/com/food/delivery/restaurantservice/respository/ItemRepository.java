package com.food.delivery.restaurantservice.respository;

import com.food.delivery.restaurantservice.model.MenuItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    @Query("select i from menu_item i where i.name like %:name% and i.category_id IN (select m.categoryId from menu_category m where m.restaurant_id = :restaurant_id)")
    List<MenuItem> findAllByNameAndRestaurantId(String name, int restaurant_id, Pageable paging);
    @Transactional
    @Query("select i from menu_item i where i.category_id IN (select m.categoryId from menu_category m where m.restaurant_id = :restaurant_id)")
    List<MenuItem> findAllByRestaurantId(int restaurant_id, Pageable paging);
}
