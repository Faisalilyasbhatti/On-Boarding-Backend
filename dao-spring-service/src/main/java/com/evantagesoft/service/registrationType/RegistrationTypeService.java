package com.evantagesoft.service.registrationType;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evantagesoft.entities.registrationType.RegistrationType;
import com.evantagesoft.repository.registrationType.RegistrationTypeRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.registrationType.RegistrationTypeVo;
import com.evantagesoft.vo.response.Response;

@Service
public class RegistrationTypeService {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationTypeService.class);
	
	@Autowired
	private RegistrationTypeRepository registrationTypeRepository;
	
	public Response findAll() {
		Response response = new Response();
		try {
			List<RegistrationType> types = registrationTypeRepository.findAllByStatusIsTrue();
			List<RegistrationTypeVo> typesVos = new ArrayList<RegistrationTypeVo>();
			for(RegistrationType registrationType : types) {
				typesVos.add(RegistrationTypeVo.getRegistrationTypeVo(registrationType));
			}
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("types", typesVos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}
}
