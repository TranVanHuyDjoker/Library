package com.example.librarycrud.model.dto;

import java.time.LocalDateTime;

public interface ListMember {
    Integer getMemberId();
    String getName();
    String getEmail();
    String getMajor();
    LocalDateTime getExpired();
}
