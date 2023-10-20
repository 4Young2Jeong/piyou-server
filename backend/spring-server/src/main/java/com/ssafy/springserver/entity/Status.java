package com.ssafy.springserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "status_id", nullable = false)
    private Long id;

    @Column(name = "piyou_id")
    private Long piyouId;

    @Column(columnDefinition = "default 0")
    private Long hungry;

    @Column(columnDefinition = "default 0")
    private Long health;

    @PrePersist
    private void prePersist() {
        this.hungry = 0L;
        this.health = 0L;
    }
}