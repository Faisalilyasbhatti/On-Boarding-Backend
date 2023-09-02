package com.evantagesoft.service.user;
import com.evantagesoft.entities.onBording.Authentication;
import com.evantagesoft.repository.user.AuthenticationRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.auth.AuthenticationVo;
import com.evantagesoft.vo.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public Response sendOtp(Map<String, AuthenticationVo> input){
        Response response = new Response();
        try {
            AuthenticationVo auth = input.get("user");
            if(StringUtils.isEmpty( auth.getUserId())) {
                response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            else if(StringUtils.isEmpty( auth.getAuthType())) {
                response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            else {
                Authentication authUser= null;
               Authentication userTemp= authenticationRepository.findByOtpAndUserId(auth.getOtp(),auth.getUserId());
                if(userTemp != null) {
                  if(auth.getOtp().equals(userTemp.getOtp()) && auth.getUserId()== userTemp.getUserId() ) {
                      auth.setOtpTime(userTemp.getOtpTime());
                      if (dateChecker(userTemp.getOtpTime())){
                          authUser = AuthenticationVo.updateAuth(auth);
                      }else {
                        response.setResponse(EVSResponse.EXPIRED_OTP);
                        return response;
                      }
                    }
                }
                else
                {
                    authUser = AuthenticationVo.getAuth(auth);
                }
                authUser = authenticationRepository.save(authUser);
                if (authUser == null) {
                    response.setResponse(EVSResponse.SYSTEM_ERROR);
                } else {
                    response.setResponse(EVSResponse.SUCCESS);
                    response.setData("user", AuthenticationVo.getAuthVo(authUser));
                }
            }
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }

    public boolean dateChecker(Date yourDate){
         final long DAY = 24 * 60 * 60 * 1000;
        return yourDate.getTime() > System.currentTimeMillis() - DAY;
    }
}
