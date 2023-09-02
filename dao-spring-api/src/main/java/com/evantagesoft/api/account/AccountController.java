package com.evantagesoft.api.account;
import com.evantagesoft.service.account.AccountService;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.onBoarding.UsersVo;
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
import java.util.HashMap;

@RestController
@RequestMapping("/onboarding")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/account", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<?> saveUser(@RequestBody HashMap<String, AccountVo> body, HttpServletRequest request) {
        return new ResponseEntity<Response>(accountService.saveUser(body), HttpStatus.OK);
    }

  
    @RequestMapping(value="/account/search", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam("email") String email, HttpServletRequest request) {
        return new ResponseEntity<Response>(accountService.searchCustomer(email), HttpStatus.OK);
    }

}