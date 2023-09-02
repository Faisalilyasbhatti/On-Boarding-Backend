package com.evantagesoft.service.user;

import java.util.*;
import java.util.regex.Pattern;

import com.evantagesoft.entities.user.role.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evantagesoft.entities.user.User;
import com.evantagesoft.repository.user.UserRepository;
import com.evantagesoft.util.encrypt.BCryptPasswordGenerator;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.UserVo;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @param input
	 * @return
	 */
	public Response userLogin(Map<String, String> input) {
		Response response = new Response();
		try {
			if (StringUtils.isEmpty(input.get("loginId"))
					|| StringUtils.isEmpty(input.get("password"))) {
				response = new Response(EVSResponse.INVALID_REQUEST_PARAMETER, null);
			} else {
				User user = userRepository.findByEmail(input.get("loginId"));
				if (user != null && user.getStatus() == 1) {
					if (BCryptPasswordGenerator.match(input.get("password"), user.getPassword())) {
						response = new Response(EVSResponse.SUCCESS, null);
						response.setData("user", UserVo.getUserVo(user, 1));
					} else {
						response = new Response(EVSResponse.INVALID_REQUEST_PARAMETER, null);
					}
				} else {
					response = new Response(EVSResponse.USER_NOT_FOUND, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}
	
	public Response findAll() {
		Response response = new Response();
		try {
			List<User> users = (List<User>) userRepository.findAll();
			List<UserVo> userVos = new ArrayList<UserVo>();
			for(User user : users) {
				userVos.add(UserVo.getUserVo(user, 1));
			}
			response.setData("users", userVos);
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response saveUser(Map<String, UserVo> input){
		Response response = new Response();
		try {
			UserVo userVo = input.get("userVo");
			if(StringUtils.isEmpty(userVo.getEmail())) {
				response.setResponse(EVSResponse.EMAIL_IS_EMPTY);
				return response;
			} else if(StringUtils.isEmpty(userVo.getFirstName())) {
				response.setResponse(EVSResponse.FIRST_NAME_EMPTY);
				return response;
			} else if(StringUtils.isEmpty(userVo.getRoleVos())) {
				response.setResponse(EVSResponse.USER_ROLE_VO_EMPTY);
				return response;
			}
			String userEmail = userVo.getEmail();
			boolean isEmailValid = Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", userEmail);
			if(!isEmailValid) {
				response.setResponse(EVSResponse.INVALID_EMAIL);
				return response;
			}
			User user = null;
			User userTemp = userRepository.findByEmail(userVo.getEmail());
			if(userTemp != null) {
				if(userVo.getUserID() == null && userVo.getUserID() != userTemp.getUserID()) {
					response.setResponse(EVSResponse.EMAIL_ALREADY_EXIST);
					return response;
				}
			}
			user = UserVo.getUser(userVo);
			if(user.getUserID() == null)
				user.setCreatedDate();
			else {
				user.setCreatedDate(userTemp.getCreatedDate());
				user.setUpdatedDate();
			}
			if(user.getUserID() == null) {
				String passwordHash = BCryptPasswordGenerator.encryptPassword(user.getPassword());
				if(passwordHash == null) {
					response.setResponse(EVSResponse.SYSTEM_ERROR);
					return response;
				}
				user.setPassword(passwordHash);
			} else {
				user.setPassword(userTemp.getPassword());
			}
			user = userRepository.save(user);
			response.setData("userVo", UserVo.getUserVo(user,2));
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception e) {
			logger.error("",e);
			e.printStackTrace();
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}

	public Response deleteUser(Long id){
		Response response = new Response();
		try {
			if(id == null) {
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				return response;
			}
			User user = this.userRepository.findOne(id);
			if(user == null){
				response.setResponse(EVSResponse.DATA_NOT_FOUND);
				return response;
			}
			this.userRepository.delete(user);
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response resetPassword(Map<String,String> input){
		Response response = new Response();
		try {
			if(StringUtils.isEmpty(input.get("email")) || StringUtils.isEmpty(input.get("newPassword"))){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				return response;
			}
			boolean isEmailValid = Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", input.get("email"));
			if(!isEmailValid) {
				response.setResponse(EVSResponse.INVALID_EMAIL);
				return response;
			}
			User user = this.userRepository.findByEmail(input.get("email"));
			if(user == null){
				response.setResponse(EVSResponse.DATA_NOT_FOUND);
				return response;
			}
			String passwordHash = BCryptPasswordGenerator.encryptPassword(input.get("newPassword"));
			if(passwordHash == null) {
				response.setResponse(EVSResponse.SYSTEM_ERROR);
				return response;
			}
			user.setPassword(passwordHash);
			this.userRepository.save(user);
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

}
