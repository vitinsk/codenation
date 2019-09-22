package com.codenation.demo.integracao;

import java.io.File;
import java.util.Collections;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.codenation.demo.config.ServiceConstants;
import com.codenation.demo.model.Response;
import com.codenation.demo.model.Result;

@Service
public class CodenationRestService extends RestTemplate {

	private static final String GET_TEST_PATH = "/generate-data?token={token}";
	private static final String SUBMIT_SOLUTION_PATH = "/submit-solution?token={token}";
	
    public Response getTest(){
    	String url = ServiceConstants.CODENATION_URL + GET_TEST_PATH;
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<Response> entity = new HttpEntity<Response>(headers);
        ResponseEntity<Response> response = exchange(url, HttpMethod.GET,entity,Response.class,ServiceConstants.API_TOKEN);
        return  response.getBody();
    }
    
    public Result sendTest(File file) {
    	String url = ServiceConstants.CODENATION_URL + SUBMIT_SOLUTION_PATH;
    	HttpHeaders headers = new HttpHeaders();
    	MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	FileSystemResource content = new FileSystemResource(file);
    	body.add("answer", content);
    	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    	
    	ResponseEntity<Result> response = postForEntity(url, requestEntity, Result.class,ServiceConstants.API_TOKEN);
    	
    	return response.getBody();
    }


}
