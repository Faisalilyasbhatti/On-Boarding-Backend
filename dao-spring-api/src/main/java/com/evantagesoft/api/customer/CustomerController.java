package com.evantagesoft.api.customer;

import com.evantagesoft.service.customer.CustomerDocumentService;
import com.evantagesoft.service.customer.CustomerService;
import com.evantagesoft.vo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;
    
    @Autowired 
    private CustomerDocumentService customerDocumentService;

    @RequestMapping(value="/register", method= RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> customerRegister(@RequestBody Map<String, Object> input, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.createBusinessCustomer(input), HttpStatus.OK);
    }
    
    @RequestMapping(value="/document", method= RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> customerDocument(@RequestBody Map<String, Object> input, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerDocumentService.uploadCustomerDocument(input), HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/signup", method= RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> userLogin(@RequestBody Map<String, Object> input, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.createCustomer(input), HttpStatus.OK);
    }

    @RequestMapping(value="/search", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam("email") String email, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.searchCustomer(email), HttpStatus.OK);
    }

    @RequestMapping(value="/search/account", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ResponseEntity<?> searchCustomerAccount(@RequestParam("id") Long id, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.searchCustomerByAccount(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(value="list", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> findAll(@RequestParam("status") int status, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.findByStatus(status), HttpStatus.OK);
    }

    @RequestMapping(value="/update/status", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, String> input, HttpServletRequest request) {
        return new ResponseEntity<Response>(customerService.updateStatus(input), HttpStatus.OK);
    }

}
