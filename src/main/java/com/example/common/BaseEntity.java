package com.example.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BaseEntity {
    @Id
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;

    protected BaseEntity(Long id) {
        this.id = id;
    }

    protected BaseEntity(Long id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
