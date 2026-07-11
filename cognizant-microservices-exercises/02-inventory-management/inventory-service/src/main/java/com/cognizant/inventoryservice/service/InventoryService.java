package com.cognizant.inventoryservice.service;

import com.cognizant.inventoryservice.client.ProductClient;
import com.cognizant.inventoryservice.dto.ProductDTO;
import com.cognizant.inventoryservice.entity.Inventory;
import com.cognizant.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public Inventory addInventory(Long productId, Integer stockLevel, String warehouseLocation) {
        // Verify the product exists via Feign call to product-service (resolved through Eureka)
        ProductDTO product = productClient.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Cannot add inventory: product " + productId + " does not exist");
        }

        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setStockLevel(stockLevel);
        inventory.setWarehouseLocation(warehouseLocation);
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("No inventory record for product " + productId));
    }

    public Inventory adjustStock(Long productId, Integer delta) {
        Inventory inventory = getByProductId(productId);
        inventory.setStockLevel(inventory.getStockLevel() + delta);
        return inventoryRepository.save(inventory);
    }
}
