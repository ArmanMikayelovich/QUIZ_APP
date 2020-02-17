package com.workfront.quiz.service.impl;

import com.workfront.quiz.entity.ImageEntity;
import com.workfront.quiz.entity.SmallImageEntity;
import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.repository.ImageRepository;
import com.workfront.quiz.repository.SmallImageRepository;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.service.ImageService;
import com.workfront.quiz.service.util.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    private UserRepository userRepository;
    private SmallImageRepository smallImageRepository;

    @Override

    public byte[] resize(byte[] bytes, int width, int height) {
        try {
            Image image = imageFromBytes(bytes);
            BufferedImage resizedImage = resizeImage(image, width, height);
            return bytesFromImage(resizedImage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional()
    public void deleteImage(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        SmallImageEntity smallImage = userEntity.getSmallImage();
        ImageEntity profileImage = userEntity.getProfileImage();
        if (smallImage != null) {
            smallImageRepository.delete(smallImage);
        }
        if (profileImage != null) {
            imageRepository.delete(profileImage);
        }
    }

    private byte[] bytesFromImage(RenderedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        return outputStream.toByteArray();
    }


    private Image imageFromBytes(byte[] arr) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        return ImageIO.read(bis);
    }

    /**
     * This function resize the image file and returns the BufferedImage object that can be saved to file system.
     */
    private BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
    @Override
    @Transactional
    public void saveOriginalImage(byte[] imagesBytes, Long userId) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPicture(imagesBytes);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        imageRepository.save(imageEntity);
        userEntity.setProfileImage(imageEntity);
        userRepository.save(userEntity);

    }
    @Override
    @Transactional
    public void saveSmallImage(byte[] imageBytes, Long userId) {
        byte[] resizedImageBytes = resize(imageBytes, 50, 50);
        SmallImageEntity smallImageEntity = new SmallImageEntity();
        smallImageEntity.setPicture(resizedImageBytes);
        smallImageEntity = smallImageRepository.save(smallImageEntity);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userEntity.setSmallImage(smallImageEntity);
        userRepository.save(userEntity);
    }

}
