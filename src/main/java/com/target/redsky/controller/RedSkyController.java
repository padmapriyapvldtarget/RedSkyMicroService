package com.target.redsky.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.target.redsky.entity.RedSkyEntity;
import com.target.redsky.exception.NoContentException;
import com.target.redsky.model.RedSky;
import com.target.redsky.repository.RedSkyRepository;
import com.target.redsky.service.RedSkyService;


@RestController
@RequestMapping("/redsky")
public class RedSkyController {

	
	private RedSkyService redSkyService;
	
	@Autowired
    public void setRedSkyService(RedSkyService redSkyService) {
        this.redSkyService = redSkyService;
    }
	
	@Autowired
	private RedSkyRepository redSkyRepository;
	
	
	@PostMapping("/save")
	public ResponseEntity<RedSky> saveIntoRedSkyTable(@RequestBody RedSky redSky) {
		return new ResponseEntity<>(redSkyService.saveIntoRedSkyTable(redSky), HttpStatus.OK);
	}

	@GetMapping(path = "/getRedSky/{id}")
	public ResponseEntity<RedSky> fetchRecordFromRedSkyTable(@PathVariable("id") Integer productId){
		RedSky redSkyData = null;
		try {
			redSkyData = redSkyService.fetchRecordFromRedSkyTable(productId);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(redSkyData, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateRedSky/{id}")
	public ResponseEntity<RedSky> updateRedSkyTable(@PathVariable("id") Integer productId, @RequestBody RedSky redSky) {
		
		
		 Optional<RedSkyEntity> redSkyData = redSkyRepository.findById(productId);

	  if (redSkyData.isPresent()) {
		
		return new ResponseEntity<>(redSkyService.updateRecordIntoRedSkyTable(redSky), HttpStatus.OK);
		
		 } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}
	@DeleteMapping("/deleteRedSky/{id}")
	public ResponseEntity<HttpStatus> deleteRedSky(@PathVariable("id") Integer productId) {
	  try {
		
			
		  redSkyRepository.deleteById(productId);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	  }
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts() {
	  try {
	    List<RedSkyEntity> products = new ArrayList<RedSkyEntity>();

	    if(redSkyRepository!=null) {
	   
	    	redSkyRepository.findAll().forEach(products::add);
	    }
	    
	    if (products.isEmpty()) {
	      return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(products, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

}
