package com.evantagesoft.api.user.role.permission;

import com.evantagesoft.service.user.role.permission.PermissionService;
import com.evantagesoft.vo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableWebMvc
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value="list", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        return new ResponseEntity<Response>(permissionService.findAll(), HttpStatus.OK);
    }
}
