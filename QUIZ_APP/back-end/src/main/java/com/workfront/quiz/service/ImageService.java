package com.workfront.quiz.service;

public interface ImageService {
    byte[] resize(byte[] bytes, int width, int height);

    void deleteImage(Long userId);

    void saveOriginalImage(byte[] imagesBytes, Long userId);

    void saveSmallImage(byte[] imageBytes, Long userId);
}
