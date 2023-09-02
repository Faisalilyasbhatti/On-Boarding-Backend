package com.evantagesoft.api.business;

import com.evantagesoft.service.business.BusinessService;
import com.evantagesoft.vo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value="/register", method= RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> userLogin(@RequestBody Map<String, Object> input, HttpServletRequest request) {
        return new ResponseEntity<Response>(businessService.createBusiness(input), HttpStatus.OK);
    }

    @RequestMapping(value="/search", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam("id") Long id, HttpServletRequest request) {
        return new ResponseEntity<Response>(businessService.searchBusiness(id), HttpStatus.OK);
    }
    
    
//    @RequestMapping(value="/register", method= RequestMethod.POST, produces="application/json", consumes="application/json")
//    public ResponseEntity<?> userLogin(@RequestBody Map<String, Object> input, HttpServletRequest request) {
//        return new ResponseEntity<Response>(businessService.createBusiness(input), HttpStatus.OK);
//    }

}
