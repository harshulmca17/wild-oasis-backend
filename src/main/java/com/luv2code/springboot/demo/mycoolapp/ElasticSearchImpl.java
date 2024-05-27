//package com.luv2code.springboot.demo.mycoolapp;
//
//
//import com.google.gson.Gson;
//import com.luv2code.springboot.demo.mycoolapp.Api.Rawg;
//import co.elastic.clients.elasticsearch.core.*;
//import org.json.simple.JSONObject;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.RefreshPolicy;
//import org.springframework.stereotype.Component;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//public class ElasticSearchImpl implements ElasticSearch{
//
//
//
//
//
//    @Autowired
//    private Rawg rawg;
//
//    @Autowired
//    private ElasticsearchClient elasticsearchClient;
//
//    private final String indexName = "movies";
//
//
//
//    @Override
//    public String fetchAndPushGames() throws Exception{
//        String message = null;
//        JSONObject response = rawg.fetchGames("https://api.rawg.io/api/games?key=49c9238c704b4955b567f6e695d433f0&page_size=40&page=7500");
//        do{
//
//
//            try {
//
//                Gson gson = new Gson();
//
//                Type movieListType = new TypeToken<List<Games>>() {
//                }.getType();
////                System.out.print(response.get("results"));
//                List<Games> movies = gson.fromJson(response.get("results").toString(), movieListType);
//
//                // Now, 'movies' contains a list of Movie objects
//                for (Games movie : movies) {
//                    System.out.printf(this.createOrUpdateDocument(movie) + "\n");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            System.out.printf((String) response.get("next"));
//            response = rawg.fetchGames((String) response.get("next"));
//        }while(response.get("next") != null);
//        message = "Games Pushed Successfully";
//        return message;
//    }
//
//
//
//
//    @Override
//    public String createOrUpdateDocument(Games movie) throws IOException {
//
//        IndexResponse response = elasticsearchClient.index(i -> i
//                .index(indexName)
//                .id(String.valueOf(movie.getId()))
//                .document(movie)
//        );
//        if(response.result().name().equals("Created")){
//            return new StringBuilder("Document has been successfully created."+movie.getId()).toString();
//        }else if(response.result().name().equals("Updated")){
//            return new StringBuilder("Document has been successfully updated."+movie.getId()).toString();
//        }
//        return new StringBuilder("Error while performing the operation.").toString();
//    }
//
//    @Override
//    public Games getDocumentById(String productId) throws IOException{
//        Games movie = null;
//        GetResponse<Games> response = elasticsearchClient.get(g -> g
//                        .index(indexName)
//                        .id(productId),
//                Games.class
//        );
//        System.out.printf(response.toString());
//        if (response.found()) {
//            movie = response.source();
//            System.out.println("Product name " + movie.getName());
//        } else {
//            System.out.println ("Product not found");
//        }
//
//        return movie;
//    }
//
//    @Override
//    public String deleteDocumentById(String productId) throws IOException {
//
//        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));
//
//        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
//        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
//            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
//        }
//        System.out.println("Product not found");
//        return new StringBuilder("Product with id " + deleteResponse.id()+" does not exist.").toString();
//
//    }
//
//
//    @Override
//    public  List<JSONObject> searchAllDocuments() throws IOException {
//
////        SearchRequest searchRequest =  new SearchRequest(indexName).;
////        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest);
////        SearchHit[] hits = searchResponse.getHits().getHits();
//        List<JSONObject> products = new ArrayList<>();
////
////        for (SearchHit hit : hits) {
////            String documentId = hit.getId();
////
////            JSONObject sourceAsString = (JSONObject) hit.getSourceRef();
////
////            // Process the source document as needed
////            System.out.println("Document ID: " + documentId);
////            System.out.println("Source: " + sourceAsString);
////            products.add(sourceAsString);
////        }
//        return products;
//    }
//
//    @Override
//    public List<Games> searchSimilar(ElasticSearchRequestDto requestDto) throws IOException{
//        List<Games> movies = new ArrayList<>();
//        try  {
//
//            // Define the search request
//            co.elastic.clients.elasticsearch.core.SearchResponse<Games> response = elasticsearchClient.search(co.elastic.clients.elasticsearch.core.SearchRequest.of(s -> s
//                            .index(indexName)
//                            .query(q -> q
//                                    .match(t -> t
//                                            .field("name")
//                                            .query(requestDto.getQuery())
//                                    )
//                            ).size(requestDto.getPageSize())),
//                    Games.class
//            );
//
//            List<Hit<Games>> total = response.hits().hits();
//
//            for ( Hit<Games> hit : total){
//                movies.add(hit.source());
//            }
//            // Process the search response
//            // You can access hits, aggregations, etc.
////            System.out.println("Total hits: " + total.get(0).source());
//            // Process other response data as needed
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return movies;
//    }
//
//    @Override
//    public Page<Games> searchSimilar(Games entity, String[] fields, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Games> S save(S entity, RefreshPolicy refreshPolicy) {
//        return null;
//    }
//
//    @Override
//    public <S extends Games> Iterable<S> saveAll(Iterable<S> entities, RefreshPolicy refreshPolicy) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(String s, RefreshPolicy refreshPolicy) {
//
//    }
//
//    @Override
//    public void delete(Games entity, RefreshPolicy refreshPolicy) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings, RefreshPolicy refreshPolicy) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Games> entities, RefreshPolicy refreshPolicy) {
//
//    }
//
//    @Override
//    public void deleteAll(RefreshPolicy refreshPolicy) {
//
//    }
//
//    @Override
//    public <S extends Games> S save(S entity) {
//
//        return null;
//    }
//
//    @Override
//    public <S extends Games> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Games> findById(String s) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Games> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Games> findAllById(Iterable<String> strings) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(String s) {
//
//    }
//
//    @Override
//    public void delete(Games entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Games> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public Iterable<Games> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Games> findAll(Pageable pageable) {
//        return null;
//    }
//}