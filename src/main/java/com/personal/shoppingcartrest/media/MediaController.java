package com.personal.shoppingcartrest.media;

import com.personal.shoppingcartrest.errormessage.ErrorMessage;
import com.personal.shoppingcartrest.product.Product;
import com.personal.shoppingcartrest.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MediaController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @RequestMapping("/medias/{productId}")
    public ResponseEntity<? extends Object> getMediaByProducts(@PathVariable long productId){
        Product product = productRepository.findById(productId);

        if(product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the product with the id "+productId+" cannot be found",
                            "/products/"+productId));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mediaRepository.findAllByProductIdAndActiveTrue(productId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/media")
    public ResponseEntity createMedia(@RequestBody Media media){
        if(media.getMediaPath() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the field [mediaPath] cannot be empty",
                            "/media"));
        }else if(media.getType() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [type] cannot be empty",
                            "/media"));
        }else if(media.getProduct() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [product] cannot be empty",
                            "/media"));
        }else if(media.getUser() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(400,
                            new Date(),
                            "Bad Request",
                            "Error, the object [user] cannot be empty",
                            "/media"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mediaRepository.save(media));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/media")
    public ResponseEntity updateMedia(@PathVariable long id, @RequestBody Media media){
        Media existingMedia = mediaRepository.findById(id);

        if(existingMedia != null){
            if(!existingMedia.getMediaPath().equals(media.getMediaPath())){
                existingMedia.setMediaPath(media.getMediaPath());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mediaRepository.save(existingMedia));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(404,
                        new Date(),
                        "Not Found",
                        "Error, the media with the id "+id+" cannot be found",
                        "/media/"+id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/media")
    public ResponseEntity deleteMedia(@PathVariable long id){
        Media media = mediaRepository.findById(id);

        if(media == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(404,
                            new Date(),
                            "Not Found",
                            "Error, the media with the id "+id+" cannot be found",
                            "/media/"+id));
        }

        mediaRepository.delete(media);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}
