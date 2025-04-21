package com.example.OnlinestoreSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.OnlinestoreSystem.modal.OnlineStore;
import com.example.OnlinestoreSystem.repository.OnlineStorerepository;

@RestController
public class OnlineStoreController {

    @Autowired
    private OnlineStorerepository onlinestorerepository;

    @PostMapping("/storeSave")
    public String insertstore(@RequestBody OnlineStore onlinestore) {
        try {
            OnlineStore savedStore = onlinestorerepository.save(onlinestore);
            return "Item saved successfully with ID: " + savedStore.getStoreId();
        } catch (Exception e) {
            return "Failed to save item: " + e.getMessage();
        }
    }

    @PutMapping("/storeUpdate")
    public String updatestore(@RequestBody OnlineStore onlinestore) {
        Long id = onlinestore.getStoreId();

        if (id == null || !onlinestorerepository.existsById(id)) {
            return "Item with ID " + id + " not found. Cannot update.";
        }

        try {
            OnlineStore updatedStore = onlinestorerepository.save(onlinestore);
            return "Item updated successfully with ID: " + updatedStore.getStoreId();
        } catch (Exception e) {
            return "Failed to update item: " + e.getMessage();
        }
    }

    @PostMapping("/multipleStoreSave")
    public String insertMultipleStores(@RequestBody List<OnlineStore> onlinestores) {
        onlinestorerepository.saveAll(onlinestores);
        return "Records saved successfully!";
    }

    @GetMapping("/getAllItem")
    public List<OnlineStore> getAllStores() {
        return onlinestorerepository.findAll();
    }

    @GetMapping("/getByStoreName/{storeName}")
    public List<OnlineStore> getByStoreName(@PathVariable("storeName") String storeName) {
        return onlinestorerepository.findByStoreName(storeName);
    }

    @GetMapping("/getByStoreId/{storeId}")
    public Optional<OnlineStore> getByStoreId(@PathVariable("storeId") Long id) {
        return onlinestorerepository.findById(id);
    }

    @DeleteMapping("/deleteStore/{storeId}")
    public String deleteStore(@PathVariable("storeId") Long id) {
        if (onlinestorerepository.existsById(id)) {
            onlinestorerepository.deleteById(id);
            return "Item deleted successfully with ID: " + id;
        } else {
            return "Item not found with ID: " + id;
        }
    }
}
