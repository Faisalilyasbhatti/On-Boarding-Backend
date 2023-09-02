package com.evantagesoft.service.industry;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evantagesoft.entities.industry.Industry;
import com.evantagesoft.repository.industry.IndustryRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.industry.IndustryVo;
import com.evantagesoft.vo.response.Response;

@Service
public class IndustryService {
	private static final Logger logger = LoggerFactory.getLogger(IndustryService.class);
	
	 @Autowired
	 private IndustryRepository industryRepository;
	
	public Response findAll() {
		Response response = new Response();
		try {
			List<Industry> industries = industryRepository.findAllByStatusIsTrue();
			List<IndustryVo> industryVos = new ArrayList<IndustryVo>();
			for(Industry industry : industries) {
				industryVos.add(IndustryVo.getIndustryVo(industry));
			}
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("industries", industryVos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(""+e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

}
