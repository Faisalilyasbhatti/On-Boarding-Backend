package com.evantagesoft.api.industry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.service.industry.IndustryService;


@Controller
@EnableWebMvc
@RequestMapping("/industry")
public class IndustryController {
	
	@Autowired
	IndustryService industryService;
	
	 @RequestMapping(value="/list", method= RequestMethod.GET, produces="application/json")
	    public ResponseEntity<?> findAll(HttpServletRequest request) {
	        return new ResponseEntity<Response>(industryService.findAll(), HttpStatus.OK);
	    }
}
