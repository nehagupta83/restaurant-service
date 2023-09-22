package com.food.delivery.restaurantservice.respository;

import com.food.delivery.restaurantservice.model.MenuCategory;
import com.food.delivery.restaurantservice.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
