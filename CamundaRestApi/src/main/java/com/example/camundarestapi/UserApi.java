package com.example.camundarestapi;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserApi {


    private  RestTemplate restTemplate;
    private  String url="http://localhost:8080/engine-rest/user";


    public UserApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }



    public void getAllUser () {
        ResponseEntity<String> response=restTemplate.getForEntity ( url,String.class );
        System.out.println (response.getBody ());
    }

    public void getUserCount(){
        ResponseEntity<String> response=restTemplate.getForEntity ( url+"/count",String.class );
        System.out.println (response.getBody ());
    }

    public void createUser(String id,String firstName, String lastName,String email,String pass){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity=new HttpEntity<> ( "{\n" +
                "  \"profile\": {\n" +
                "    \"id\": \""+id+"\",\n" +
                "    \"firstName\": \""+firstName+"\",\n" +
                "    \"lastName\": \""+lastName+"\",\n" +
                "    \"email\": \""+email+"\"\n" +
                "  },\n" +
                "  \"credentials\": {\n" +
                "    \"password\": \""+pass+"\"\n" +
                "  }\n" +
                "}" ,headers);
        ResponseEntity<String> response=restTemplate.postForEntity (   url+"/create",httpEntity,String.class );
        System.out.println (response.getBody ());
    }
    public void deleteUser(String id){
        restTemplate.delete ( url+"/user/"+id);
    }

    public void updateProfile(String oldId,String newId,String firstName, String lastName,String email){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity=new HttpEntity<> ( "{\n" +
                "  \"id\": \""+newId+"\",\n" +
                "  \"firstName\": \""+firstName+"\",\n" +
                "  \"lastName\": \""+lastName+"\",\n" +
                "  \"email\": \""+email+"\"\n" +
                "}" ,headers);
        ResponseEntity<String> response=restTemplate.exchange (   url+"/"+oldId+"/profile", HttpMethod.PUT,httpEntity,String.class );
        System.out.println (response.getBody ());

    }

    public void getProfileUser(String id){
        ResponseEntity<String> response=restTemplate.getForEntity ( url+"/"+id+"/profile",String.class );
        System.out.println (response.getBody ());
    }

    public void updateCredentials(String id,String newPass,String oldPass){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity=new HttpEntity<> ( "{\n" +
                "  \"password\": \""+newPass+"\",\n" +
                "  \"authenticatedUserPassword\": \""+oldPass+"\"\n" +
                "}" ,headers);
        ResponseEntity<String> response=restTemplate.exchange (   url+"/"+id+"/credentials", HttpMethod.PUT,httpEntity,String.class );
        System.out.println (response.getBody ());

    }

    public void unlockUser(String id){
        HttpEntity<String> stringHttpEntity=new HttpEntity<> ( "" );
        ResponseEntity<String > response=restTemplate.postForEntity ( url+"/"+id+"/unlock",stringHttpEntity,String.class );
        System.out.println (response.getBody ());

    }

}
