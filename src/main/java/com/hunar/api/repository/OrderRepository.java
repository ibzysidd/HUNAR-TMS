package com.hunar.api.repository;

import com.hunar.api.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByIdCustomer(int custId);

    Order findByOrderNo(String orderNo);

    @Query("SELECT o FROM Order o WHERE o.deliveryDate = :targetDate")
    List<Order> findOrdersWithDeliveryDate(LocalDate targetDate);

//    List<Order> findbyBookingDate(LocalDate date);
}
