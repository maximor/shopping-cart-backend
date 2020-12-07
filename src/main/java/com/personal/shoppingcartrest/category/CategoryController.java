package com.personal.shoppingcartrest.category;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public ResponseEntity getCategories(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryRepository.findAll());
    }

    @RequestMapping("/categories/pages/{page}/{size}")
    public ResponseEntity getCategoriesByPages(@PathVariable Integer page, @PathVariable Integer size){
        if(page == null || size == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [page] or [size] cannot be empty",
                            "/categories/pages/{page}/{size}"));
        }

        Pageable currentPage = PageRequest.of(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryRepository.findAll(currentPage));
    }

    @RequestMapping("/category/{id}")
    public ResponseEntity getCategory(@PathVariable long id){
        Category category = categoryRepository.findById(id);

        if(category == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the category with the id "+id+" cannot be found",
                            "/category/"+id));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/category")
    public ResponseEntity createCategory(@RequestBody Category category){
        if(category.getName() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [name] cannot be empty",
                            "/category"));
        }else if(category.getUser() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [user] cannot be empty",
                            "/category"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryRepository.save(category));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/category/{id}")
    public ResponseEntity updateCategory(@PathVariable long id, @RequestBody Category category){
        Category existingCategory = categoryRepository.findById(id);

        if(existingCategory != null){
            if(!existingCategory.getName().equals(category.getName())){
                existingCategory.setName(category.getName());
            }else if(!existingCategory.getDescription().equals(category.getDescription())){
                existingCategory.setDescription(category.getDescription());
            }else if(existingCategory.isActive() != category.isActive()){
                existingCategory.setActive(category.isActive());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(categoryRepository.save(existingCategory));

        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(404,
                        new Date(),
                        "Not Found",
                        "Error, the category with the id "+id+" cannot be found",
                        "/category/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/category/{id}")
    public ResponseEntity deleteCategory(@PathVariable long id){
        Category category = categoryRepository.findById(id);

        if(category == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the category with the id "+id+" cannot be found",
                            "/category/"+id));
        }

        categoryRepository.delete(category);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}
