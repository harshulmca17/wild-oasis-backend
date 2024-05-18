package com.luv2code.springboot.demo.mycoolapp.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Settings;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.SettingRepository;
import com.luv2code.springboot.demo.mycoolapp.Result.Result;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Cabin;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Guest;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.BookingRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.CabinRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.GuestRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.service.BookingsService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MysqlRestController {

    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private BookingsService bookingsService;





    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/booking/{bookingId}")
    public Result getBookingById(@PathVariable Integer bookingId) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());

//        JSONObject mergedJSON = me
        try {

            Bookings booking = bookingRepository.findById(bookingId).get();
            Optional<Guest> guest = guestRepository.findById(booking.getGuestId());
            Optional<Cabin> cabin = cabinRepository.findById(booking.getCabinId());



            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(booking);// obj is your object
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(json);

            jsonObj.put("created_at",booking.getCreated_at().toString());
            jsonObj.put("startDate",booking.getStartDate().toString());
            jsonObj.put("endDate",booking.getEndDate().toString());

            jsonObj.put("cabins",cabin);
            jsonObj.put("guests",guest);
            result.setResult(jsonObj);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/bookings")
    public Result getBookings() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        System.out.println("getBookings");
        try {
            List<Bookings> bookings = bookingRepository.findAll();
            List<Object> resultList = new ArrayList<>();
            for (Bookings booking:bookings){
                Optional<Guest> guest = guestRepository.findById(booking.getGuestId());
                Optional<Cabin> cabin = cabinRepository.findById(booking.getCabinId());



                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(booking);// obj is your object
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(json);

                jsonObj.put("created_at",booking.getCreated_at().toString());
                jsonObj.put("startDate",booking.getStartDate().toString());
                jsonObj.put("endDate",booking.getEndDate().toString());

                jsonObj.put("cabins",cabin);
                jsonObj.put("guests",guest);


                resultList.add(jsonObj);
            }
            result.setResult(resultList);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/bookings")
    public Result getBookingsByFilter(@RequestBody JSONObject request) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        System.out.println(requestBooking);
        System.out.println(request);
//        JSONObject mergedJSON = me
        try {
            JSONObject resultFinal = new JSONObject();


            String status;
            status = request.get("status") != null ? String.valueOf(request.get("status")) : null;
            String sortfield = String.valueOf(request.get("fieldName"));
            String sortDirection = String.valueOf(request.get("sortDirection"));
            Integer pageSize = (Integer) request.get("pageSize");
            Integer page = (Integer) request.get("page");

            Integer offset = (page - 1)*pageSize;
            Integer limitResult = pageSize;

            System.out.println(offset);
            System.out.println(limitResult);
            List<Bookings> bookings = null;
            if(status == null){
                bookings = bookingRepository.findByFilterAllStatus(sortfield,sortDirection,offset,limitResult);
            }else{
                bookings = bookingRepository.findByFilter(status,sortfield,sortDirection,offset,limitResult);
            }
            Integer count = status  != null ? bookingRepository.countByStatus(status) : bookingRepository.countByStatus();

            List<Object> resultList = new ArrayList<>();
            for (Bookings booking:bookings){
                Optional<Guest> guest = guestRepository.findById(booking.getGuestId());
                Optional<Cabin> cabin = cabinRepository.findById(booking.getCabinId());



                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(booking);// obj is your object
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(json);

                jsonObj.put("created_at",booking.getCreated_at().toString());
                jsonObj.put("startDate",booking.getStartDate().toString());
                jsonObj.put("endDate",booking.getEndDate().toString());

                jsonObj.put("cabins",cabin);
                jsonObj.put("guests",guest);


                resultList.add(jsonObj);
            }
            resultFinal.put("pages",Math.ceil( (double) count / (double)pageSize));
            resultFinal.put("count",count);
            resultFinal.put("bookings",resultList);
            result.setResult(resultFinal);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cabin/{cabinId}")
    public Result getCabinById(@PathVariable Integer cabinId) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            result.setResult(cabinRepository.findById(cabinId).get());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/deleteCabin/{cabinId}")
    public Result deleteCabinById(@PathVariable Integer cabinId) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            cabinRepository.deleteById(cabinId);
            result.setResult("Cabin with id:["+cabinId+"] deleted successfully.");

        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/deleteBooking/{bookingId}")
    public Result deleteBookingById(@PathVariable Integer bookingId) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            Bookings booking = bookingRepository.findById(bookingId).get();
            bookingRepository.deleteById(bookingId);
            result.setMessage("Booking with id:["+bookingId+"] deleted successfully.");
            result.setResult(booking);

        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/updateCabin")
    public Result updateCabinById(@RequestBody Cabin cabin) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            cabinRepository.save(cabin);
            result.setResult("Cabin with id:["+cabin.getId()+"] updated successfully.");

        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/updateBooking")
    public Result updateBookingById(@RequestBody Bookings Booking) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        Bookings newBooking = bookingRepository.findById(Booking.getId()).get();

        System.out.println(Booking.getIsPaid());
        Booking.setEndDate(newBooking.getEndDate());
        Booking.setStartDate(newBooking.getStartDate());
        Booking.setCreated_at(newBooking.getCreated_at());
        try {
            bookingRepository.save(Booking);
            result.setMessage("Booking with id:["+Booking.getId()+"] updated successfully.");
            Bookings updateBooking = bookingRepository.findById(Booking.getId()).get();
            Optional<Guest> guest = guestRepository.findById(Booking.getGuestId());
            Optional<Cabin> cabin = cabinRepository.findById(Booking.getCabinId());
//
//
//
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(updateBooking);// obj is your object
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(json);

            jsonObj.put("created_at",Booking.getCreated_at().toString());
            jsonObj.put("startDate",Booking.getStartDate().toString());
            jsonObj.put("endDate",Booking.getEndDate().toString());

            jsonObj.put("cabins",cabin);
            jsonObj.put("guests",guest);



            result.setResult(jsonObj);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);
            System.out.println(e.getMessage());
        }
        return result;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cabins")
    public Result getCabins() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            result.setResult(cabinRepository.findAll());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/settings")
    public Result getSettings() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            result.setResult(settingRepository.findAll());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/updateSettings")
    public Result updateSettingById(@RequestBody Settings setting) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            settingRepository.save(setting);
            result.setResult("Setting with id:["+setting.getId()+"] updated successfully.");

        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/guest/{guestId}")
    public Result getGuestById(@PathVariable Integer guestId) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        try {
            result.setResult(guestRepository.findById(guestId).get());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }

}
