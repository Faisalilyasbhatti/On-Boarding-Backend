package com.evantagesoft.api.user.role;

import com.evantagesoft.service.user.role.RoleService;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.role.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@EnableWebMvc
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="list", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<?> createRole(@RequestBody HashMap<String, RoleVo> body, HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.saveRole(body), HttpStatus.OK);
    }

    @RequestMapping(value="exists/{code}", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> checkRoleExists(@PathVariable String code, HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.findRoleAlreadyExistByCode(code), HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method= RequestMethod.PUT, produces="application/json")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody HashMap<String, RoleVo> body, HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.updateRole(id,body), HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method= RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<?> deleteRole(@PathVariable Long id, HttpServletRequest request) {
        return new ResponseEntity<Response>(roleService.deleteRole(id), HttpStatus.OK);
    }

}
