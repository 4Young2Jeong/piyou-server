package com.b106_402jeoung.PIYOU.repository;

import com.b106_402jeoung.PIYOU.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID> {
    List<Child> findByName(String name);
    List<Child> findAllByIsMealFalse();

    List<Child> findAllByIsMealTrue();
}