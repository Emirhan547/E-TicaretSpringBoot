package com.eticaret.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.NotificationDTO;
import com.eticaret.entity.Notification;

@Mapper
public interface NotificationMapper {
	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(target = "createdAt", expression = "java(notification.getCreatedAt())")
    NotificationDTO toDto(Notification notification);

    Notification toEntity(NotificationDTO notificationDTO);
}

