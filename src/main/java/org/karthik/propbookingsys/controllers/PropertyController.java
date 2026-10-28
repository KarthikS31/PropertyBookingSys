package org.karthik.propbookingsys.controllers;


import org.karthik.propbookingsys.models.Properties;
import org.karthik.propbookingsys.services.PropertyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/props")
public class PropertyController {

    @Autowired
    PropertyServices propertyServices;

    @GetMapping
    ResponseEntity<List<Properties>> getAllProperties(){
        return new ResponseEntity<>(propertyServices.getAllProperties(), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<Properties> addProperty(@RequestBody Properties body ){
        return new ResponseEntity<>( propertyServices.addProperty(body),HttpStatus.CREATED);
    }

}
