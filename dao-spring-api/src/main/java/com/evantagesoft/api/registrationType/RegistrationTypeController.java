package com.evantagesoft.api.registrationType;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.evantagesoft.service.registrationType.RegistrationTypeService;
import com.evantagesoft.vo.response.Response;

@Controller
@EnableWebMvc
@RequestMapping("/registrationType")
public class RegistrationTypeController {
	
	@Autowired
	RegistrationTypeService registrationTypeService;
	
	 @RequestMapping(value="/list", method= RequestMethod.GET, produces="application/json")
	    public ResponseEntity<?> findAll(HttpServletRequest request) {
	        return new ResponseEntity<Response>(registrationTypeService.findAll(), HttpStatus.OK);
	    }
}
