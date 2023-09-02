package com.evantagesoft.api.user;

import com.evantagesoft.service.user.UserOnboardingService;
import com.evantagesoft.vo.onBoarding.UsersVo;
import com.evantagesoft.vo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("/onboarding")
public class UserOnboardingController {

    @Autowired
    private UserOnboardingService onboardingService;

    @RequestMapping(value="list", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        return new ResponseEntity<Response>(onboardingService.findAll(), HttpStatus.OK);
    }
    
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> saveUser(@RequestBody HashMap<String, UsersVo> body, HttpServletRequest request) {
		return new ResponseEntity<Response>(onboardingService.saveUser(body), HttpStatus.OK);
	}

	 @RequestMapping(value="/user/{id}", method=RequestMethod.GET, produces="application/json")
	    public ResponseEntity<?> getUserById(@PathVariable(value = "id") long id) {
	        return new ResponseEntity<Response>(onboardingService.findUser(id), HttpStatus.OK);
	    }

}
