package com.hunar.api.repository;

import com.hunar.api.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByIdCustomer(int custId);

    Order findByOrderNo(String orderNo);

//    List<Order> findbyBookingDate(LocalDate date);
}
