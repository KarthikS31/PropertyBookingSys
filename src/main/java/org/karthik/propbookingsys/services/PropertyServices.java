package org.karthik.propbookingsys.services;

import org.karthik.propbookingsys.models.Properties;
import org.karthik.propbookingsys.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServices {

    @Autowired
    PropertyRepository propertyRepository;


    public List<Properties> getAllProperties(){
        return propertyRepository.findAll();
    }

    public Properties addProperty(Properties property){
        return propertyRepository.save(property);
    }
}
