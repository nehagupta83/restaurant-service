package com.food.delivery.restaurantservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.food.delivery.restaurantservice.dto.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "owner-id")
    private String ownerId;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = MenuCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private List<Review> reviews;

    @OneToMany(targetEntity = MenuCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private List<MenuCategory> categories;
}
