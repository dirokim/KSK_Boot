package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nimbusds.oauth2.sdk.Response;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
@Slf4j
public class RestTempletTest {

	
	
	public void flux() throws Exception {
		WebClient webClient = WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")    // 공통 url
				.build();
		int num = 1 ;
//			Mono<ResponseEntity<RestVO>> response =	webClient.get()
//		 		 .uri("posts/1")
//				 .retrieve()
//				 .toEntity(RestVO.class) ;
//				RestVO rsetVO = response.block().getBody();
//		Mono<RestVO> response =	webClient.get()
//		 		 .uri("posts/1")
//				 .retrieve()
//				 .bodyToMono(RestVO.class) ;
//				RestVO rsetVO = response.block();
		
			Flux<RestVO> response =	webClient.get()
		 		 .uri("posts")
				 .retrieve()
				 .bodyToFlux(RestVO.class) ;
				
			response.subscribe((r)->{
				log.info("=============restVO : {}",r);
				RestVO restVO = r ;
				log.info("=============restVO : {}",r);
			});
		
		
		
	} 
	
	public void rest() throws Exception {
		log.info("Rest Template");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(null, null);
		ResponseEntity<List> rsponse = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", List.class);
		List<RestVO> result = rsponse.getBody();
		log.info("==================result : {}",result);
		
		
		
	}
	
	
}
