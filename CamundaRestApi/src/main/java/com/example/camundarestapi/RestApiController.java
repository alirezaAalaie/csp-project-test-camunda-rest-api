package com.example.camundarestapi;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestApiController {

    public static RestTemplate restTemplate=new RestTemplate ();
    public static String url="http://localhost:8080/engine-rest";

    public static void main (String[] args) {


    }

    private static void engine () {
        ResponseEntity<String> response=restTemplate.getForEntity ( url+"/engine",String.class );
        System.out.println (response.getBody ());
    }
    private static void message () {
        HttpEntity<String> request=new HttpEntity<> ( "HI" );
        ResponseEntity<String> response=restTemplate.postForEntity (  url+"/message",request,String.class );
        System.out.println (response.getBody ());
    }


    private static void getDeploymentList(){
//        ResponseEntity<String> response=restTemplate.getForEntity ( url+"" )
    }

}
