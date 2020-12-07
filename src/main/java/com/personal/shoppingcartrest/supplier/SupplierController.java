package com.personal.shoppingcartrest.supplier;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @RequestMapping("/suppliers")
    public ResponseEntity getSuppliers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplierRepository.findAll());
    }

    @RequestMapping("/suppliers/pages/{page}/{size}")
    public ResponseEntity getSupplierByPages(@PathVariable Integer page, @PathVariable Integer size){
        if(page == null || size == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [page] or [size] cannot be empty",
                            "/suppliers/pages/{page}/{size}"));
        }

        Pageable currentPage = PageRequest.of(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplierRepository.findAll(currentPage));
    }

    @RequestMapping("/supplier/{id}")
    public ResponseEntity getSupplier(@PathVariable long id){
        Supplier supplier = supplierRepository.findById(id);

        if(supplier == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the supplier with the id "+id+" cannot be found",
                            "/supplier/"+id));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplier);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/supplier")
    public ResponseEntity createSupplier(@RequestBody Supplier supplier){
        if(supplier.getName() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [name] cannot be empty",
                            "/supplier"));
        }else if(supplier.getAddress() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [address] cannot be empty",
                            "/supplier"));
        }else if(supplier.getPhone() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [phone] cannot be empty",
                            "/supplier"));
        }else if(supplier.getUser() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [user] cannot be empty",
                            "/supplier"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(supplierRepository.save(supplier));

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/supplier/{id}")
    public ResponseEntity updateSupplier(@PathVariable long id, @RequestBody Supplier supplier){
        Supplier existingSupplier = supplierRepository.findById(id);

        if(existingSupplier != null){
            if(!existingSupplier.getName().equals(supplier.getName())){
                existingSupplier.setName(supplier.getName());
            }else if(!existingSupplier.getAddress().equals(supplier.getAddress())){
                existingSupplier.setAddress(supplier.getAddress());
            }else if(!existingSupplier.getPhone().equals(supplier.getPhone())){
                existingSupplier.setPhone(supplier.getPhone());
            }else if(existingSupplier.isActive() != supplier.isActive()){
                existingSupplier.setActive(supplier.isActive());
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(supplierRepository.save(existingSupplier));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(404,
                        new Date(),
                        "Not Found",
                        "Error, the supplier with the id "+id+" cannot be found",
                        "/supplier/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/supplier/{id}")
    public ResponseEntity deleteSupplier(@PathVariable long id){
        Supplier supplier = supplierRepository.findById(id);

        if(supplier == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the supplier with id "+id+" cannot be found",
                            "/supplier/"+id));
        }
        supplierRepository.delete(supplier);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}
