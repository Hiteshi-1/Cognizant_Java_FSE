package com.cognizant.inventoryservice.controller;

import com.cognizant.inventoryservice.entity.Inventory;
import com.cognizant.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestParam Long productId,
                                                    @RequestParam Integer stockLevel,
                                                    @RequestParam String warehouseLocation) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryService.addInventory(productId, stockLevel, warehouseLocation));
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getByProductId(productId));
    }

    @PatchMapping("/product/{productId}/adjust")
    public ResponseEntity<Inventory> adjustStock(@PathVariable Long productId, @RequestParam Integer delta) {
        return ResponseEntity.ok(inventoryService.adjustStock(productId, delta));
    }
}
