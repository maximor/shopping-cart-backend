package com.personal.shoppingcartrest.inventory;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import com.personal.shoppingcartrest.product.Product;
import com.personal.shoppingcartrest.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/inventory/{productId}")
    public ResponseEntity getInventoryByProduct(@PathVariable long productId){
        Product product = productRepository.findById(productId);

        if(product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the supplier with the id "+productId+" cannot be found",
                            "/product/"+productId));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryRepository.findByProductId(productId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/inventory/{productId}")
    public ResponseEntity updateInventoryByProduct(@PathVariable long productId, @RequestBody Inventory inventory){
        Product product = productRepository.findById(productId);

        if(product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the supplier with the id "+productId+" cannot be found",
                            "/product/"+productId));
        }

        Inventory existingInventory = inventoryRepository.findByProductId(productId);

        if(existingInventory != null){
            if(existingInventory.getTotalUnitSold() < inventory.getTotalUnitSold() && inventory.getTotalUnitSold() > 0){
                existingInventory.setTotalUnitSold(inventory.getTotalUnitSold());
            }else if(existingInventory.getGrossSale() < inventory.getGrossSale() && inventory.getGrossSale() > 0){
                existingInventory.setGrossSale(inventory.getGrossSale());
            }else if(existingInventory.getUnitInStock() < inventory.getUnitInStock() && inventory.getUnitInStock() > 0){
                existingInventory.setUnitInStock(inventory.getUnitInStock());
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(inventoryRepository.save(existingInventory));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the Inventory with the product id "+productId+" cannot be found",
                            "/inventory/"+productId));
        }


    }
}
