package com.webshop.backend.repository;

import com.webshop.backend.model.TopNewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopNewsImageRepository extends JpaRepository<TopNewsImage, Long> {

}
