package com.eticaret.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.eticaret.dto.NotificationDTO;
import com.eticaret.entity.Notification;
import com.eticaret.mapper.NotificationMapper;
import com.eticaret.repository.NotificationRepository;
import com.eticaret.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Async // ✅ Bildirim gönderme işlemi arka planda çalışacak
    public void sendNotification(NotificationDTO notificationDTO) {
        logger.info("Bildirim gönderiliyor - Alıcı: {}", notificationDTO.getRecipient());
        try {
            Thread.sleep(3000); // 🔥 Simüle edilmiş gecikme (Gerçek sistemde API çağrısı olabilir)
            Notification notification = NotificationMapper.INSTANCE.toEntity(notificationDTO);
            notificationRepository.save(notification);
            logger.info("Bildirim başarıyla kaydedildi. Alıcı: {}", notificationDTO.getRecipient());
        } catch (Exception e) {
            logger.error("Bildirim gönderilirken hata oluştu! Hata: {}", e.getMessage());
            throw new RuntimeException("Bildirim kaydedilemedi!", e);
        }
    }
    

    @Override
    public List<NotificationDTO> getAllNotifications() {
        logger.info("Tüm bildirimler getiriliyor...");
        return notificationRepository.findAll().stream()
                .map(NotificationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}

