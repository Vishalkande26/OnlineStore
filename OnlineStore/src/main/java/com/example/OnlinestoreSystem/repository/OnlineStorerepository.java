package com.example.OnlinestoreSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlinestoreSystem.modal.OnlineStore;

public interface OnlineStorerepository extends JpaRepository<OnlineStore, Long> {
    List<OnlineStore> findByStoreName(String storeName);
}
