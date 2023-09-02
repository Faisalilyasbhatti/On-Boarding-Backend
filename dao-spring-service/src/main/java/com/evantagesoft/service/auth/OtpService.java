package com.evantagesoft.service.auth;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.auth.Otp;
import com.evantagesoft.repository.account.AccountRepository;
import com.evantagesoft.repository.auth.OtpRepository;
import com.evantagesoft.util.email.EmailUtil;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.auth.OtpVo;
import com.evantagesoft.vo.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

@Service
public class OtpService {
    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private EmailUtil emailUtil;
    
	@Autowired
	private AccountRepository accountRepository;

    public Response sendOtp(OtpVo otpVo){
        Response response = new Response();
        try {
            if(StringUtils.isEmpty( otpVo.getAccountVo())) {
                response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            else {
                Otp otp= null;
                otp = OtpVo.getAuth(otpVo);
                otp = otpRepository.save(otp);
                if (otp == null) {
                    response.setResponse(EVSResponse.SYSTEM_ERROR);
                } else {
//                    String message = "Hi "+otp.getAccount().getFullName()+"," +
//                            "\n\nYour onboarding verification OTP is : "+otp.getEmailOtp()+"" +
//                            "\n\n" +
//                            "Regards:\n" +
//                            "DAO TEAM";
                    emailUtil.sendOtpEmail(otpVo.getAccountVo().getEmail(), "DAO onboarding OTP", otp.getEmailOtp(), otpVo.getAccountVo().getFullName());
//                    emailUtil.sendEmail(otpVo.getAccountVo().getEmail(),
//                            "DAO onboarding OTP",
//                            message);
                    response.setResponse(EVSResponse.SUCCESS);
                    response.setData("user", OtpVo.getAuthVo(otp));
                }
            }
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }

//    public boolean dateChecker(Date yourDate){
//        final long DAY = 24 * 60 * 60 * 1000;
//        return yourDate.getTime() > System.currentTimeMillis() - DAY;
//    }

    public boolean dateValidater(Date expiredDate){
       return expiredDate.getTime() > System.currentTimeMillis();
    }

    public Response verifyOtp(OtpVo otpVo) {
        Response response = new Response();
        try{
            if(StringUtils.isEmpty( otpVo.getAccountVo())) {
                response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            else if(StringUtils.isEmpty( otpVo.getMobileOtp())) {
                response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            else{
                Otp otp= null;
                Otp otpTemp= otpRepository.findByOtpAndUserId(otpVo.getMobileOtp(),otpVo.getAccountVo().getId());
                if(otpTemp != null) {
                    if(otpVo.getMobileOtp().equals(otpTemp.getMobileOtp())
                            && otpVo.getAccountVo().getId().equals(otpTemp.getAccount().getId())) {
                        if (dateValidater(otpTemp.getExpiredDate())){
                        otp = OtpVo.updateAuth(otpTemp);
                        otp = otpRepository.save(otp);
                        
                        Account isExistAcc= accountRepository.findById(otpVo.getAccountVo().getId());
                       
                        if(isExistAcc!=null) {
                            if(isExistAcc.getStatus()==0) {
                            	isExistAcc.setStatus(1);
                     		   accountRepository.save(isExistAcc);
                            }
                        }

                            response.setResponse(EVSResponse.SUCCESS);
                            response.setData("user", OtpVo.getAuthVo(otp));
                        }
                        else {
                            response.setResponse(EVSResponse.EXPIRED_OTP);
                            return response;
                        }
                    }
                }
                else{
                    response.setResponse(EVSResponse.INVALID_CREDENTIALS);
                    return response;
                }
            } }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
        }

        public Date expiredDateMaker(){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime();
    }
}
