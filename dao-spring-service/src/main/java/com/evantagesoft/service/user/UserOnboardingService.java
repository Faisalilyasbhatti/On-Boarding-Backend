package com.evantagesoft.service.user;

import com.evantagesoft.entities.onBording.FormData;
import com.evantagesoft.entities.onBording.UserOnBoarding;
import com.evantagesoft.entities.onBording.Users;
import com.evantagesoft.repository.user.UserOnboardingRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.onBoarding.UserOnBoardingVo;
import com.evantagesoft.vo.onBoarding.UsersVo;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.UserVo;
import com.evantagesoft.vo.user.role.RoleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserOnboardingService {
    private static final Logger logger = LoggerFactory.getLogger(UserOnboardingService.class);
//    public  List<FormData> dynamicForm ;

        @Autowired
       private UserOnboardingRepository onboardingRepository;

    public Response saveUser( Map<String, UsersVo> input){
        Response response = new Response();
        try {
            UsersVo usersVo = input.get("user");
            if(StringUtils.isEmpty(usersVo.getEmailAddress())) {
                response.setResponse(EVSResponse.EMAIL_IS_EMPTY);
                return response;
            }
             else if(StringUtils.isEmpty( usersVo.getContact())) {
            response.setResponse(EVSResponse.CONTACT_IS_EMPTY);
            return response;
            }
             else {
                Users user= null;
                Users userTemp= onboardingRepository.findByEmailAddress(usersVo.getEmailAddress());

                if(userTemp != null) {
                    if(usersVo.getId() == null && usersVo.getId() != userTemp.getId()) {
                        response.setResponse(EVSResponse.EMAIL_ALREADY_EXIST);
                        return response;
                    }
                }
                user = UsersVo.getUser(usersVo);
                user = this.onboardingRepository.save(user);
                if (user == null) {
                    response.setResponse(EVSResponse.SYSTEM_ERROR);
                } else {
                    response.setResponse(EVSResponse.SUCCESS);
                    response.setData("user", UsersVo.getUserVo(user));
                }
            }
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }

    public Response findAll() {
        Response response = new Response();
        try {
            List<Users> users = (List<Users>) onboardingRepository.findAll();
            response.setData("users", users);
            response.setResponse(EVSResponse.SUCCESS);
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }

    public Response findUser(long id) {
        Response response = new Response();
        try {
            Users users=  onboardingRepository.findById(id);
            response.setData("users", users);
            response.setResponse(EVSResponse.SUCCESS);
        }
        catch (Exception e) {
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }
    
//    Map<String, UserOnBoarding> input
//    public Response saveUser( UserOnBoardingVo  request){
//        Response response = new Response();
//        UserOnBoarding user;
//        UserOnBoarding userOnBoarding;
//        String userEmail="";
//        UserOnBoardingVo  userVo = request;
//        try {
//            if(userVo.getEmailAddress() ==null){
//                response.setResponse(EVSResponse.INVALID_EMAIL);
//            }
//            else  {
//                    if(userVo.getEmailAddress() != null) {
//                    Iterable<UserOnBoarding> userTemp=  onboardingRepository.findByEmailAddress(userVo.getEmailAddress());
//                    if(userTemp.iterator().hasNext()) {
//                        for (UserOnBoarding temp : userTemp) {
//                         userEmail=  temp.getEmailAddress();
//                        }
//                      if(userVo.getUserID() == null && userVo.getEmailAddress() != userEmail) {
//                          response.setResponse(EVSResponse.EMAIL_ALREADY_EXIST);
//                          return response;
//                      }
//                  }
//                }
//                userOnBoarding=UserOnBoardingVo.getOnBoarding(userVo);
//                dynamicForm =new ArrayList<FormData>();
//                ReflectionUtils.doWithFields(request.getClass(), field -> {
//                    FormData data=new FormData();
//                    if (!field.getName().equals("formData")
//                    && !field.getName().equals("userID") && !field.getName().equals("updatedDate")
//                    && !field.getName().equals("createdDate") && !field.getName().equals("createdBy")
//                    ){
//                        field.setAccessible(true);
//                        String fieldValue=""+field.get(request);
//                        if(field.getName().equals("dateOfBirth")){
//                            fieldValue= dateTransform(fieldValue);
//                        }
//                        data.setUser(userOnBoarding);
//                        data.setName(field.getName());
//                        data.setValue(fieldValue);
//                        dynamicForm.add(data);
//                    }
//                });
//                userOnBoarding.setFormData(dynamicForm);
//                user = onboardingRepository.save(userOnBoarding);
//                response.setData("user",user);
//                response.setResponse(EVSResponse.SUCCESS);
//            }
//        }
//		catch (Exception e) {
//			logger.error("",e);
//			e.printStackTrace();
//			response.setResponse(EVSResponse.SYSTEM_ERROR);
//		}
//        return response;
//    }

//    public String dateTransform(String previousDate){
//        String formatedDate="";
//        try {
//            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//            Date date = (Date)formatter.parse(previousDate);
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//            formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE)  ;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return formatedDate;
//    }
//
}
