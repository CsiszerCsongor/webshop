package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOP_NEWS_IMAGES")
public class TopNewsImage extends BaseEntity {
    @NonNull
    private String filePath;
    @NonNull
    private boolean isActive;
}
