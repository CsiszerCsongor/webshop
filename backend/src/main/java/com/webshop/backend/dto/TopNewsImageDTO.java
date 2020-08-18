package com.webshop.backend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopNewsImageDTO {
    private Long id;
    private String filePath;
    private byte[] content;
}
