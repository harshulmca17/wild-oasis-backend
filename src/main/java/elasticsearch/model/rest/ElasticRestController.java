package elasticsearch.model.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticRestController {
    @PostMapping("/pushData")
    public String sayHello(@RequestBody String requestBody) {


//        return"asd";
        return (String) requestBody;
    }
}



    // expose "/" that return "Hello World"



