package com.myRetail.products.repository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.myRetail.products.configuration.YAMLConfig;

@Service
public class RedSkyRepository {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private YAMLConfig yamlConfig;

	// JSON extraction method from HTTP URL, to extract the title description from
	// external API
	public String getTitleFromExternalAPI(String id) {
		try {
			String uri = yamlConfig.getHost() + id + yamlConfig.getExtension();
			String externalAPIResult = restTemplate.getForObject(uri, String.class);
			String retrievedTitle = new JSONObject(externalAPIResult.toString()).getJSONObject("product")
					.getJSONObject("item").getJSONObject("product_description").getString("title");
			return retrievedTitle;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
