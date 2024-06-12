package com.luv2code.springboot.demo.mycoolapp.rest;

//import com.luv2code.springboot.demo.mycoolapp.Api.Rawg;
//import com.luv2code.springboot.demo.mycoolapp.ElasticSearchImpl;
//import com.luv2code.springboot.demo.mycoolapp.ElasticSearchRequestDto;
//import com.luv2code.springboot.demo.mycoolapp.Games;
import com.luv2code.springboot.demo.mycoolapp.Result.Result;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Cabin;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Guest;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.BookingRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.CabinRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.GuestRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FunRestController {

    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private BookingRepository bookingRepository;


//    private final ElasticSearchImpl elasticSearch;
//    private Games myMovie;

//    public FunRestController(ElasticSearchImpl elasticSearch, Rawg rawg) {
//        this.elasticSearch = elasticSearch;
//        this.rawg = rawg;
//    }
//    private Rawg rawg;



//    @PostMapping("/pushData")
//    public ResponseEntity<String> sayHello(@RequestBody Games requestBody) {
//
//
//        try {
//
//            elasticSearch.createOrUpdateDocument(requestBody);
//            ResponseEntity<String> movieSavedSuccessfully = new ResponseEntity<>("Movie saved successfully", HttpStatus.CREATED);
//            System.out.printf(movieSavedSuccessfully.toString());
//            return movieSavedSuccessfully;
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error saving movie: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/games/{gameId}")
//    public Result sayHello(@PathVariable String gameId) throws IOException {
//        Result result = new Result();
//        result.setError(null);
//        result.setStatus(HttpStatus.OK.value());
//        result.setResult(elasticSearch.getDocumentById(gameId));
//
//        return result;
//    }
//    @PostMapping("/searchGames")
//    public List<Games> searchMovies(@RequestBody ElasticSearchRequestDto requestDto) throws IOException, ParseException {
//
//        System.out.println(requestDto);
//        return elasticSearch.searchSimilar(requestDto);
//    }
    @PostMapping("/pushCabins")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Result pushCabins(@RequestBody Cabin requestDto) throws IOException, ParseException {


        System.out.println(requestDto);
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        result.setResult(null);


        try {
            Cabin savedBooking = cabinRepository.save(requestDto);
            result.setResult(savedBooking);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @PostMapping("/pushBookings")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Result pushBookings(@RequestBody Bookings requestDto) throws IOException, ParseException {


        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        result.setResult(null);
        System.out.println(requestDto);

        try {
            Bookings savedBooking = bookingRepository.save(requestDto);
            result.setResult(savedBooking);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
//    @GetMapping("/fetchAndPushGames")
//    public String fetchAndPushGames() throws Exception {
//
//
//        System.print_ln()
//        return elasticSearch.fetchAndPushGames();
//    }

}
