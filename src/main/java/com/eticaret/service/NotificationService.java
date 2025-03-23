package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.NotificationDTO;

public interface NotificationService {
	void sendNotification(NotificationDTO notificationDTO);
    List<NotificationDTO> getAllNotifications();

}
