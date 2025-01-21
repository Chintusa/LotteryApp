package com.org.lottery.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.lottery.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
