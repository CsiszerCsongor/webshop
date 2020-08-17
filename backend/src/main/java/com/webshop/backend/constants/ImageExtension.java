package com.webshop.backend.constants;

public enum ImageExtension {
    JPG("JPG"),
    jpg("jpg"),
    JPEG("JPEG"),
    jpeg("jpeg"),
    PNG("PNG"),
    png("png"),
    GIF("GIF"),
    PSD("PSD");

    private final String label;

    private ImageExtension(String label){
        this.label = label;
    }
}
