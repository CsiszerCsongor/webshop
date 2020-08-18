package com.webshop.backend.repository;

import com.webshop.backend.model.TopNewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopNewsImageRepository extends JpaRepository<TopNewsImage, Long> {
    List<TopNewsImage> findByIsActiveTrueAndIsDeletedFalse();
}
