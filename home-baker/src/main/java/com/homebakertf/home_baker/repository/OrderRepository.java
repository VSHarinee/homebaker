package com.homebakertf.home_baker.repository;

import com.homebakertf.home_baker.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByVendorId(Long vendorId);
}
