package com.personal.shoppingcartrest.brand;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping("/brands")
    public ResponseEntity getBrands(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandRepository.findAll());
    }

    @RequestMapping("/brands/pages/{page}/{size}")
    public ResponseEntity getBrandsByPages(@PathVariable Integer page, @PathVariable Integer size) {
        if(page == null || size == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [page] or [size] cannot be empty",
                            "/brands/pages/{page}/{size}"));
        }

        Pageable currentPage = PageRequest.of(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandRepository.findAll(currentPage));
    }

    @RequestMapping("/brand/{id}")
    public ResponseEntity getBrand(@PathVariable long id){
        Brand brand = brandRepository.findById(id);

        if(brand == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, the brand with the id "+id+" cannot be found",
                            "/brand/"+id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brand);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/brand")
    public ResponseEntity createBrand(@RequestBody Brand brand){
        if(brand.getName() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [name] cannot be empty",
                            "/brand"));
        }else if(brand.getUser() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [user] cannot be empty",
                            "/brand"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(brandRepository.save(brand));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/brand/{id}")
    public ResponseEntity updateBrand(@PathVariable long id, @RequestBody Brand brand){
        Brand existingBrand = brandRepository.findById(id);

        if(existingBrand != null){
            if(!existingBrand.getName().equals(brand.getName())){
                existingBrand.setName(brand.getName());
            }else if(!existingBrand.getDescription().equals(brand.getDescription())){
                existingBrand.setDescription(brand.getDescription());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(brandRepository.save(existingBrand));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(
                        404,
                        new Date(),
                        "Not Found",
                        "Error, the brand with the id "+id+" cannot be found",
                        "/brand/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/brand/{id}")
    public ResponseEntity deleteBrand(@PathVariable long id){
        Brand brand = brandRepository.findById(id);

        if(brand == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, the brand with the id "+id+" cannot be found",
                            "/brand/"+id));
        }

        brandRepository.delete(brand);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


}
