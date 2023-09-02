package com.evantagesoft.api.auth;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evantagesoft.service.auth.OtpService;
import com.evantagesoft.util.email.EmailUtil;
import com.evantagesoft.vo.auth.OtpVo;
import com.evantagesoft.vo.response.Response;

@RestController
@RequestMapping("/auth")
public class OtpController {

    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping(value="/otp", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<?> sendOtp(@RequestBody OtpVo body, HttpServletRequest request) {
        return new ResponseEntity<Response>(otpService.sendOtp(body), HttpStatus.OK);
    }

    @RequestMapping(value="/verify/otp", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVo body, HttpServletRequest request) {
        return new ResponseEntity<Response>(otpService.verifyOtp(body), HttpStatus.OK);
    }

    @RequestMapping(value="/send", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> sendEmail(HttpServletRequest request) {
        return new ResponseEntity<String>(emailUtil.sendEmail("nandkhatri1265@gmail.com","DAO onboarding OTP","Your onboarding verification OTP is : "), HttpStatus.OK);
    }
    
    @RequestMapping(value="/sendEmail", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findById(@RequestParam("toEmail") String email, @RequestParam("fullName") String fullName, 
    		HttpServletRequest request) {
        emailUtil.sendOtpEmail(email, "OTP", "122232", fullName);
    	return new ResponseEntity<String>("Email send", HttpStatus.OK);
    }

}
