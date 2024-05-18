package com.luv2code.springboot.demo.mycoolapp.Api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import static org.springframework.http.HttpMethod.*;

@Component
public class Rawg {
    @Autowired
    RestTemplate webClient;



    public JSONObject fetchGames(String apiPath){

        JSONObject updateMap = new JSONObject();
        JSONParser parse = new JSONParser();
        try{
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type","application/json; charset=utf-8");
            HttpEntity<String> entity = new HttpEntity<String>(header);

            HttpEntity<String> response;
            response = webClient.exchange(apiPath, GET,entity,String.class);
            String data = response.getBody();
            if(data != null){
                updateMap = (JSONObject) parse.parse(data);
            }
//            System.out.print(response);
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }

        return updateMap;

    }
}
