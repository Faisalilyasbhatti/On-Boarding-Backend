package com.evantagesoft.service.country;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evantagesoft.entities.country.Country;
import com.evantagesoft.repository.country.CountryRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.country.CountryVo;
import com.evantagesoft.vo.response.Response;

@Service
public class CountryService {

	private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

	@Autowired
	private CountryRepository countryRepository;

	public Response findAll() {
		Response response = new Response();
		try {
			List<Country> countries= (List<Country>) countryRepository.findAll();
			List<CountryVo> countryVos = new ArrayList<CountryVo>();
			for (Country country : countries) {
				countryVos.add(CountryVo.getCountryVo(country));
			}
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("countries", countryVos);
		} catch (Exception e) {
			logger.error("" + e);
			e.printStackTrace();
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

}
