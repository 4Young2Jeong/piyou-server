package com.ssafy.springserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "child")
public class Child {
    @Id
    @Column(name = "child_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "default 0")
    private Integer experience;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "status_id", nullable = false, unique = true)
    private Status status;

    @PrePersist
    private void prePersist() {
        this.experience = 0;
    }
}