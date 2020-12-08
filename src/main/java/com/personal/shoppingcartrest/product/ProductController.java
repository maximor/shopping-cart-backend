package com.personal.shoppingcartrest.product;

import com.personal.shoppingcartrest.brand.Brand;
import com.personal.shoppingcartrest.brand.BrandRepository;
import com.personal.shoppingcartrest.category.Category;
import com.personal.shoppingcartrest.category.CategoryRepository;
import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import com.personal.shoppingcartrest.inventory.Inventory;
import com.personal.shoppingcartrest.inventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @RequestMapping("/products")
    public ResponseEntity getProducts(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findAll());
    }

    @RequestMapping("/products/pages/{page}/{size}")
    public ResponseEntity getProductsByPages(@PathVariable Integer page, @PathVariable Integer size){
        if(page == null || size == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [page] or [size] cannot be empty",
                            "/products/pages/{page}/{size}"));
        }

        Pageable currentPage = PageRequest.of(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findAll(currentPage));
    }

    @RequestMapping("/products/{categoryId}")
    public ResponseEntity getProductsByCategory(@PathVariable long categoryId){
        Category category = categoryRepository.findById(categoryId);

        if(category == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the category with the id "+categoryId+" cannot be found",
                            "/products/"+categoryId));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findAllByCategoryId(categoryId));
    }

    @RequestMapping("/products/{brandId}")
    public ResponseEntity getProductsByBrand(@PathVariable long brandId){
        Brand brand = brandRepository.findById(brandId);

        if(brand == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the brand with the id "+brandId+" cannot be found",
                            "/products/"+brandId));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findAllByBrandId(brandId));
    }

    @RequestMapping("/products/{categoryId}/{brandId}")
    public ResponseEntity getProductsByCategoryBrand(@PathVariable long categoryId, @PathVariable long brandId){
        Category category = categoryRepository.findById(categoryId);
        Brand brand = brandRepository.findById(brandId);

        if(category == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the category with the id "+categoryId+" cannot be found",
                            "/products/"+categoryId+"/"+brandId));
        }else if(brand == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the brand with the id "+brandId+" cannot be found",
                            "/products/"+categoryId+"/"+brandId));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findAllByCategoryIdAndBrandId(categoryId, brandId));

    }

    @RequestMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable long id){
        Product product = productRepository.findById(id);

        if(product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the product with the id "+id+" cannot be found",
                            "/product/"+id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        if(product.getName() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [name] cannot be empty",
                            "/product"));
        }else if(product.getUser() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [user] cannot be empty",
                            "/product"));
        }else if(product.getCategory() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [category] cannot be empty",
                            "/product"));
        }else if(product.getBrand() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [brand] cannot be empty",
                            "/product"));
        }

        product = productRepository.save(product);
        inventoryRepository.save(new Inventory(0,0,0, product));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/product")
    public ResponseEntity updateProduct(@PathVariable long id, @RequestBody Product product){
        Product existingProduct = productRepository.findById(id);

        if(existingProduct != null){
            if(!existingProduct.getName().equals(product.getName())){
                existingProduct.setName(product.getName());
            }else if(!existingProduct.getDescription().equals(product.getDescription())){
                existingProduct.setDescription(product.getDescription());
            }else if(existingProduct.isActive() != product.isActive()){
                existingProduct.setActive(product.isActive());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(productRepository.save(existingProduct));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(404,
                        new Date(),
                        "Not Found",
                        "Error, the product with the id "+id+" cannot be found",
                        "/product/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/product")
    public ResponseEntity deleteProduct(@PathVariable long id){
        Product product = productRepository.findById(id);

        if(product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the product with the id "+id+" cannot be found",
                            "/product/"+id));
        }

        productRepository.delete(product);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}
