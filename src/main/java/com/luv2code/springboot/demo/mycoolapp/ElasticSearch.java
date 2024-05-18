package com.luv2code.springboot.demo.mycoolapp;

import org.json.simple.JSONObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ElasticSearch extends ElasticsearchRepository<Games, String> {

    public List<Games> searchSimilar(ElasticSearchRequestDto query) throws IOException;
    public String fetchAndPushGames() throws Exception;
    public String createOrUpdateDocument(Games movie) throws IOException;

    Games getDocumentById(String productId) throws IOException;

    public String deleteDocumentById(String productId) throws IOException;

    public List<JSONObject> searchAllDocuments() throws IOException ;
}
