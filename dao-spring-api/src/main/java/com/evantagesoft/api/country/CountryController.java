package com.evantagesoft.api.country;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evantagesoft.service.country.CountryService;
import com.evantagesoft.vo.response.Response;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;
	
	 @RequestMapping(value="list", method=RequestMethod.GET, produces="application/json")
	    public ResponseEntity<?> findAll(HttpServletRequest request) {
	        return new ResponseEntity<Response>(countryService.findAll(), HttpStatus.OK);
	    }
}
