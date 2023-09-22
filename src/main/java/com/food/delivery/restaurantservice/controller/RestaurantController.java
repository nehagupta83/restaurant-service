package com.food.delivery.restaurantservice.controller;


import com.food.delivery.restaurantservice.model.MenuCategory;
import com.food.delivery.restaurantservice.model.MenuItem;
import com.food.delivery.restaurantservice.model.Restaurant;
import com.food.delivery.restaurantservice.model.Review;
import com.food.delivery.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurant), HttpStatus.OK);
    }

    @PostMapping("/item")
    public void addMenuItems(@RequestBody List<MenuItem> items){
        restaurantService.addMenuItems(items);
    }

    @PostMapping("/category")
    public void addMenuCategory(@RequestBody List<MenuCategory> categories){
        restaurantService.addMenuCategories(categories);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurant(@RequestParam(required = false) String name,
                                                          @RequestParam(required = true) int page,
                                                          @RequestParam(required = true) int size){
        return new ResponseEntity<>(restaurantService.getAllOpenRestaurants(name, page,size), HttpStatus.OK);
    }

    @GetMapping("/category/item")
    public ResponseEntity<List<MenuCategory>> getMenuItems(@RequestParam(required = false) String category,
                                                          @RequestParam(required = true) int restaurantId,
                                                          @RequestParam(required = true) int page,
                                                          @RequestParam(required = true) int size){
        return new ResponseEntity<>(restaurantService.getMenuItems(category, restaurantId, page,size), HttpStatus.OK);
    }

    @GetMapping("/item")
    public ResponseEntity<List<MenuItem>> getItems(@RequestParam(required = false) String category,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = true) int restaurantId,
                                                           @RequestParam(required = true) int page,
                                                           @RequestParam(required = true) int size){
        return new ResponseEntity<>(restaurantService.getItems(category, name, restaurantId, page,size), HttpStatus.OK);
    }

    @PostMapping("/review")
    public Review writeReview(@RequestBody Review review){
        return restaurantService.writeReview(review);
    }
}
