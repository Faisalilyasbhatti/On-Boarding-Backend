package com.evantagesoft.service.user.role;

import com.evantagesoft.entities.user.permission.Permission;
import com.evantagesoft.entities.user.role.Role;
import com.evantagesoft.entities.user.role.RoleType;
import com.evantagesoft.repository.user.role.permission.PermissionRepository;
import com.evantagesoft.repository.user.role.type.RoleTypeRepository;
import com.evantagesoft.util.encrypt.BCryptPasswordGenerator;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.UserVo;
import com.evantagesoft.vo.user.permission.PermissionVo;
import com.evantagesoft.vo.user.role.RoleTypeVo;
import com.evantagesoft.vo.user.role.RoleVo;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evantagesoft.repository.user.UserRepository;
import com.evantagesoft.repository.user.role.RoleRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
@Service
public class RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private RoleTypeRepository roleTypeRepository;

	public Response findAll(){
		Response response = new Response();

		try{
			Iterable<Role> roles = this.roleRepository.findAll();
			List<RoleVo> roleVos = new ArrayList<>();
			roles.forEach(role -> {
				roleVos.add(RoleVo.getRoleVo(role));
			});
			response.setData("roles", roleVos);
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception ex){
			ex.printStackTrace();
			logger.error(""+ex);
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}

	public Response findById(Long id){
		Response response = new Response();

		try{
			if(id == null){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				return response;
			}
			Role role = this.roleRepository.findOne(id);
			if (role == null){
				response.setResponse(EVSResponse.DATA_NOT_FOUND);
				return response;
			}
			response.setData("role", RoleVo.getRoleVo(role));
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception ex){
			ex.printStackTrace();
			logger.error(""+ex);
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}

	public Response saveRole(HashMap<String,RoleVo> input){
		Response response = new Response();
		try {
			if(input.isEmpty()){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
			}
			if(!input.containsKey("roleVo")){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
			} else {
				//HashMap<String, Object> roleVo = (HashMap<String, Object>) input.get("role");
				RoleVo roleVo = (RoleVo) input.get("roleVo");
				//if(StringUtils.isEmpty(roleVo.get("name")) || StringUtils.isEmpty(roleVo.get("code")) || !roleVo.containsKey("permissionIds") || !roleVo.containsKey("roleTypeId")) {
				if(roleVo == null){
					response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				} else {

					Role role = null;
					Role roleTemp = roleRepository.findByCode(roleVo.getCode());
					if(roleTemp != null) {
						if(roleVo.getId() == null && roleVo.getId() != roleTemp.getId()) {
							response.setResponse(EVSResponse.EMAIL_ALREADY_EXIST);
							return response;
						}
					}
					role = RoleVo.getRole(roleVo);
					if(role.getId() == null)
						role.setCreatedDate(new Date());
					else {
						role.setCreatedDate(roleTemp.getCreatedDate());
						role.setUpdatedDate(new Date());
					}

					role = this.roleRepository.save(role);
					if (role == null) {
						response.setResponse(EVSResponse.SYSTEM_ERROR);
					} else {
						response.setResponse(EVSResponse.SUCCESS);
						response.setData("role", RoleVo.getRoleVo(role));
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response findRoleAlreadyExistByCode(String code){
		Response response = new Response();
		try{
			if(Strings.isNullOrEmpty(code)){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				return response;
			}
			Role role = this.roleRepository.findByCode(code);
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("exists", role != null);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response updateRole(Long id, HashMap<String,RoleVo> input){
		Response response = new Response();
		try {
			if(input.isEmpty()){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
			}
			if(!input.containsKey("roleVo")){
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
			} else {
				Role _role = this.roleRepository.findOne(id);
				if(_role == null){
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				RoleVo roleVo = (RoleVo) input.get("roleVo");
				if(roleVo == null){
					response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				} else {
					Role role = RoleVo.getRole(roleVo);
					role.setUpdatedDate(new Date());
					role = this.roleRepository.save(role);
					if (role == null) {
						response.setResponse(EVSResponse.SYSTEM_ERROR);
					} else {
						response.setResponse(EVSResponse.SUCCESS);
						response.setData("role", RoleVo.getRoleVo(role));
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response deleteRole(Long id) {
		Response response = new Response();
		try {
			if(id == null) {
				response.setResponse(EVSResponse.INVALID_REQUEST_PARAMETER);
				return response;
			}
			Role _role = this.roleRepository.findOne(id);
			if(_role == null){
				response.setResponse(EVSResponse.DATA_NOT_FOUND);
				return response;
			}
			this.roleRepository.delete(_role);
			response.setResponse(EVSResponse.SUCCESS);
		} catch (Exception e){
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}
}
