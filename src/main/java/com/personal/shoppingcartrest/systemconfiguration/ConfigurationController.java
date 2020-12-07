package com.personal.shoppingcartrest.systemconfiguration;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ConfigurationController {
    @Autowired
    private ConfigurationRepository configurationRepository;

    @RequestMapping("/configuration/{id}")
    public ResponseEntity getConfiguration(@PathVariable long id){
        Configuration configuration = configurationRepository.findById(id);

        if(configuration == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the configuration with the id "+id+" cannot be found",
                            "/configuration/"+id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(configuration);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/configuration")
    public ResponseEntity createConfiguration(@RequestBody Configuration configuration){
        if(configuration.getPercentageA() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [percentageA] cannot be empty",
                            "/configuration"));
        }else if(configuration.getPercentageB() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [percentageB] cannot be empty",
                            "/configuration"));
        }else if(configuration.getPercentageC() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [percentageC] cannot be empty",
                            "/configuration"));
        }else if(configuration.getPercentageABC() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [percentageABC] cannot be empty",
                            "/configuration"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(configurationRepository.save(configuration));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/configuration/{id}")
    public ResponseEntity updateConfiguration(@PathVariable long id, @RequestBody Configuration configuration){
        Configuration existingConfiguration = configurationRepository.findById(id);

        if(existingConfiguration != null){
            if(existingConfiguration.getPercentageA() != configuration.getPercentageA()){
                existingConfiguration.setPercentageA(configuration.getPercentageA());
            }else if(existingConfiguration.getPercentageB() != configuration.getPercentageB()){
                existingConfiguration.setPercentageB(configuration.getPercentageB());
            }else if(existingConfiguration.getPercentageC() != configuration.getPercentageC()){
                existingConfiguration.setPercentageC(configuration.getPercentageC());
            }else if(existingConfiguration.getPercentageABC() != configuration.getPercentageABC()){
                existingConfiguration.setPercentageABC(configuration.getPercentageABC());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(configurationRepository.save(existingConfiguration));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(404,
                        new Date(),
                        "Not Found",
                        "Error, the configuration with the id "+id+" cannot be found",
                        "/configuration/"+id));
    }
}
