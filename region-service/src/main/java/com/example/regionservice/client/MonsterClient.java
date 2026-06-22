//package com.example.regionservice.client;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class MonsterClient {
//
//    private final RestTemplate restTemplate;
//
//    public MonsterClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public void validateMonster(Long monsterId) {
//        restTemplate.getForObject(
//                "http://localhost:8081/monsters/" + monsterId,
//                Object.class
//        );
//    }
//}