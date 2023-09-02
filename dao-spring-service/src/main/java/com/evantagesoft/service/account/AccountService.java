package com.evantagesoft.service.account;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.auth.Otp;
import com.evantagesoft.repository.account.AccountRepository;
import com.evantagesoft.service.auth.OtpService;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.auth.OtpVo;
import com.evantagesoft.vo.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpService otpService;

    public Response saveUser(Map<String, AccountVo> input){
        Response response = new Response();
        try {
            AccountVo accountVo = input.get("user");
            if(StringUtils.isEmpty(accountVo.getEmail())) {
                response.setResponse(EVSResponse.EMAIL_IS_EMPTY);
                return response;
            }
            else if(StringUtils.isEmpty(accountVo.getFullName())) {
                response.setResponse(EVSResponse.CONTACT_IS_EMPTY);
                return response;
            }
            else {
                Account account= null;
                OtpVo otpVo=new OtpVo();
                Otp otp=null;
                Account accountTemp= accountRepository.findByEmail(accountVo.getEmail());
                if(accountTemp != null) {
                    if(accountVo.getId() == null && accountVo.getId() != accountTemp.getId()) {
                        response.setResponse(EVSResponse.EMAIL_ALREADY_EXIST);
                        return response;
                    }
                }
                
                account =accountVo.getAccount(accountVo);
                account = accountRepository.save(account);
                otpVo.setAccountVo(AccountVo.getAccountVo(account));
                otpService.sendOtp(otpVo);

                if (account == null) {
                    response.setResponse(EVSResponse.SYSTEM_ERROR);
                } else {
                    response.setResponse(EVSResponse.SUCCESS);
                    response.setData("user", accountVo.getAccountVo(account));
                }
            }
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }


    public Response searchCustomer(String email) {
        Response response = new Response();
        try {
            if (StringUtils.isEmpty(email)) {
                response.setResponse(EVSResponse.INVALID_EMAIL);
            } else {
                Account account = accountRepository.findByEmail(email);
                if (account == null) {
                    response.setResponse(EVSResponse.DATA_NOT_FOUND);
                    return response;
                }
                response.setResponse(EVSResponse.SUCCESS);
                response.setData("customer", AccountVo.getAccountVo(account));
            }
        } catch (Exception e) {
            response.setResponse(EVSResponse.SYSTEM_ERROR);
        }
        return response;
    }


}
