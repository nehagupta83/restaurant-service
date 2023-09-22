package com.food.delivery.restaurantservice.service;

import com.food.delivery.restaurantservice.model.MenuCategory;
import com.food.delivery.restaurantservice.model.MenuItem;
import com.food.delivery.restaurantservice.model.Restaurant;
import com.food.delivery.restaurantservice.model.Review;
import com.food.delivery.restaurantservice.respository.CategoryRepository;
import com.food.delivery.restaurantservice.respository.ItemRepository;
import com.food.delivery.restaurantservice.respository.RestaurantRepository;
import com.food.delivery.restaurantservice.respository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllOpenRestaurants(String name, int page, int size) {
        List<Restaurant> restaurants = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        if(name!=null)
            restaurants = restaurantRepository.findAllByNameAndStatus(name, "OPEN", paging);
        else
            restaurants = restaurantRepository.findAllByStatus("OPEN", paging);
        return restaurants;
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
    }

    public void updateRestaurant(Integer id, Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = getRestaurantById(id);
        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        // Update other properties as needed
        restaurantRepository.save(existingRestaurant);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public void addMenuItems(List<MenuItem> items) {
        itemRepository.saveAll(items);
    }

    public void addMenuCategories(List<MenuCategory> categories) {
        categoryRepository.saveAll(categories);
    }

    public List<MenuCategory> getMenuItems(String category, int restaurantId, int page, int size) {
        List<MenuCategory> items = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        if(category!=null)
            items = categoryRepository.findAllByCategoryAndRestaurantId(category, restaurantId, paging);
        else
            items = categoryRepository.findAllByRestaurantId(restaurantId, paging);
        return items;
    }

    public List<MenuItem> getItems(String category, String name, int restaurantId, int page, int size) {
        List<MenuItem> items = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        if(name!=null)
            items = itemRepository.findAllByNameAndRestaurantId(name, restaurantId, paging);
        else
            items = itemRepository.findAllByRestaurantId(restaurantId, paging);
        return items;
    }

    public Review writeReview(Review review) {
        review.setReviewDate(LocalDate.now().toString());
        return reviewRepository.save(review);
    }
}
