package com.eticaret.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync // ✅ Async işlemleri aktifleştiriyoruz
public class AsyncConfig {

}
