package com.cailam.springdatajpaangularpoc.controller;

import java.util.List;

import com.cailam.springdatajpaangularpoc.model.Cloth;
import com.cailam.springdatajpaangularpoc.service.ClothService;
import com.cailam.springdatajpaangularpoc.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ClothService clothService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Clothes---------------------------------------------

    @RequestMapping(value = "/cloth/", method = RequestMethod.GET)
    public ResponseEntity<List<Cloth>> listAllClothes() {
        List<Cloth> clothes = clothService.findAllClothes();
        if (clothes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Cloth>>(clothes, HttpStatus.OK);
    }

    // -------------------Retrieve Single Cloth------------------------------------------

    @RequestMapping(value = "/cloth/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCloth(@PathVariable("id") long id) {
        logger.info("Fetching Cloth with id {}", id);
        Cloth cloth = clothService.findById(id);
        if (cloth == null) {
            logger.error("Cloth with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Cloth with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cloth>(cloth, HttpStatus.OK);
    }

    // -------------------Create a Cloth-------------------------------------------

    @RequestMapping(value = "/cloth/", method = RequestMethod.POST)
    public ResponseEntity<?> createCloth(@RequestBody Cloth cloth, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Cloth : {}", cloth);

        if (clothService.isClothExist(cloth)) {
            logger.error("Unable to create. A Cloth with name {} already exist", cloth.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Cloth with name " +
                    cloth.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        clothService.saveCloth(cloth);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/cloth/{id}").buildAndExpand(cloth.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Cloth ------------------------------------------------

    @RequestMapping(value = "/cloth/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCloth(@PathVariable("id") long id, @RequestBody Cloth cloth) {
        logger.info("Updating Cloth with id {}", id);

        Cloth currentCloth = clothService.findById(id);

        if (currentCloth == null) {
            logger.error("Unable to update. Cloth with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Cloth with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCloth.setName(cloth.getName());
        currentCloth.setType(cloth.getType());
        currentCloth.setCost(cloth.getCost());

        clothService.updateCloth(currentCloth);
        return new ResponseEntity<Cloth>(currentCloth, HttpStatus.OK);
    }

    // ------------------- Delete a CLoth-----------------------------------------

    @RequestMapping(value = "/cloth/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCloth(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Cloth with id {}", id);

        Cloth cloth = clothService.findById(id);
        if (cloth == null) {
            logger.error("Unable to delete. Cloth with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Cloth with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        clothService.deleteClothById(id);
        return new ResponseEntity<Cloth>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Clothes-----------------------------

    @RequestMapping(value = "/cloth/", method = RequestMethod.DELETE)
    public ResponseEntity<Cloth> deleteAllClothes() {
        logger.info("Deleting All CLothes");

        clothService.deleteAllClothes();
        return new ResponseEntity<Cloth>(HttpStatus.NO_CONTENT);
    }

}
