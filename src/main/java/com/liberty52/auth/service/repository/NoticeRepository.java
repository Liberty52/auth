package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, String> {
}