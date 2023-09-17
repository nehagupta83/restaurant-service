package com.food.delivery.restaurantservice.respository;

import com.food.delivery.restaurantservice.model.MenuCategory;
import com.food.delivery.restaurantservice.model.MenuItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<MenuCategory, Integer> {
    @Transactional
    @Query("select m from menu_category m where m.name like %:category% and m.restaurant_id=:restaurant_id")
    List<MenuCategory> findAllByCategoryAndRestaurantId(String category, int restaurant_id, Pageable paging);

    @Transactional
    @Query("select m from menu_category m where m.restaurant_id=:restaurant_id")
    List<MenuCategory> findAllByRestaurantId(int restaurant_id, Pageable paging);
}
