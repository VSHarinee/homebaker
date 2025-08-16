package com.homebakertf.home_baker.repository;

import com.homebakertf.home_baker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByVendorId(Long vendorId);
    @Query("SELECT u.username, u.phone FROM Product p JOIN p.vendor u WHERE u.id = :vendorId")
    List<Object[]> findVendorUsernameAndPhoneByVendorId(@Param("vendorId") Long vendorId);
}
