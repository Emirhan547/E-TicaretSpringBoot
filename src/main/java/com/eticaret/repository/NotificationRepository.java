package com.eticaret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
