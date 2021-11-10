package com.study.algafood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.study.algafood.client.model.RestauranteInput;
import com.study.algafood.client.model.RestauranteModel;
import com.study.algafood.client.model.RestauranteResumoModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {
	
	private static final String RESOURCE_PATH = "/restaurantes";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteResumoModel> listar(){
		URI resourceURI = URI.create(url + RESOURCE_PATH);
		try {
			RestauranteResumoModel[] retaurantes = restTemplate.getForObject(resourceURI, RestauranteResumoModel[].class);
			return Arrays.asList(retaurantes);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}

	}
	
	public RestauranteModel adicionar(RestauranteInput restaurante) {
		  var resourceUri = URI.create(url + RESOURCE_PATH);
		  
		  try {
		    return restTemplate
		        .postForObject(resourceUri, restaurante, RestauranteModel.class);
		  } catch (HttpClientErrorException e) {
		    throw new ClientApiException(e.getMessage(), e);
		  }
		}
}
