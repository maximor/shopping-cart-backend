package com.personal.shoppingcartrest.status;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @RequestMapping("/status")
    public ResponseEntity getStatus(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statusRepository.findAllByActiveTrue());
    }

    @RequestMapping("/status/inactive")
    public ResponseEntity getStatusInactive(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statusRepository.findAllByActiveFalse());
    }

    @RequestMapping("/status/{id}")
    public ResponseEntity getStatus(@PathVariable long id){
        Status status = statusRepository.findById(id);

        if(status == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, the status with the id "+id+" cannot be found",
                            "/status/"+id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(status);
    }

    @RequestMapping(method =  RequestMethod.POST, value = "status")
    public ResponseEntity createStatus(@RequestBody Status status){
        if(status.getName() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [name] cannot be empty",
                            "/status"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(statusRepository.save(status));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/status/{id}")
    public ResponseEntity updateStatus(@PathVariable long id, @RequestBody Status status){
        Status existingStatus = statusRepository.findById(id);

        if(existingStatus != null){
            if(!existingStatus.getName().equals(status.getName())){
                existingStatus.setName(status.getName());
            }else if(!existingStatus.getDescription().equals(status.getDescription())){
                existingStatus.setDescription(status.getDescription());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(statusRepository.save(existingStatus));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(
                        404,
                        new Date(),
                        "Not Found",
                        "Error, the status with the id "+id+" cannot be found",
                        "/status/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/brand/{id}")
    public ResponseEntity deleteStatus(@PathVariable long id){
        Status status = statusRepository.findById(id);

        if(status == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, the status with the id "+id+" cannot be found",
                            "/status/"+id));
        }

        statusRepository.delete(status);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}
