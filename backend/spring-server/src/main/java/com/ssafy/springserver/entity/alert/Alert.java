package com.ssafy.springserver.entity.alert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id", nullable = false)
    private Long id;

    private UUID child_id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created_at;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_enum", nullable = false, length = 10)
    private AlertType alertType;

    @PrePersist
    public void prePersist() {
        this.created_at = new Timestamp(System.currentTimeMillis());
    }
}