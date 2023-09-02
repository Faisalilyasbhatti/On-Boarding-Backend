package com.evantagesoft.api.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.evantagesoft.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.evantagesoft.service.user.UserService;
import com.evantagesoft.vo.response.Response;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
@RestController
@EnableWebMvc
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<?> userLogin(@RequestBody Map<String, String> input, HttpServletRequest request) {
		return new ResponseEntity<Response>(userService.userLogin(input), HttpStatus.OK);
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> findAll(HttpServletRequest request) {
		return new ResponseEntity<Response>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> saveUser(@RequestBody Map<String, UserVo> input, HttpServletRequest request) {
		return new ResponseEntity<Response>(userService.saveUser(input), HttpStatus.OK);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpServletRequest request) {
		return new ResponseEntity<Response>(userService.deleteUser(id), HttpStatus.OK);
	}

	@RequestMapping(value="password/reset", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> input, HttpServletRequest request) {
		return new ResponseEntity<Response>(userService.resetPassword(input), HttpStatus.OK);
	}
}
