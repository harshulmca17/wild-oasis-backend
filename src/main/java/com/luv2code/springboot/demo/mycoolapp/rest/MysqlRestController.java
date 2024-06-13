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
import org.json.simple.JSONArray;
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
    @GetMapping("/guests")
    public Result getGuests() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        System.out.println("getGuests");
        try {
            List<Guest> guests = guestRepository.findAll();

            result.setResult(guests);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/guest")
    public Result getGuestByEmail(@RequestBody JSONObject request) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        System.out.println("getGuests");
        String emailId = String.valueOf(request.get("email"));

        System.out.println(emailId);
        try {
            Guest guests = guestRepository.getGuestByEmail(emailId);

            result.setResult(guests);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @PostMapping("/pushGuests")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Result pushGuests(@RequestBody Guest requestDto) throws IOException, ParseException {


        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        result.setResult(null);
        System.out.println(requestDto);

        try {
            Guest savedBooking = guestRepository.save(requestDto);
            result.setResult(savedBooking);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @PostMapping("/updateGuests")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Result updateGuests(@RequestBody Guest requestDto) throws IOException, ParseException {


        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        result.setResult(null);
        System.out.println(requestDto);

        try {
            Integer savedBooking = guestRepository.updateGuestDetails(requestDto.getId(),requestDto.getNationalID(), requestDto.getNationality(),requestDto.getCountryFlag());
            result.setResult(savedBooking == 1? "Guest Update Successfully" :"Error occurred while updating");
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/helloworld")
    public Result helloworld() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        System.out.println("getBookings");
        try {

            result.setMessage("heeloworld");
            result.setResult("heeloworld");
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/bookingsAfterDate")
    public Result   getBookingsAfterDate(@RequestBody JSONObject request) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        System.out.println(requestBooking);
        System.out.println(request);
//        JSONObject mergedJSON = me
        try {
            JSONObject resultFinal = new JSONObject();


            Integer daysGap = (Integer) request.get("daysGap");
            String type = (String) request.get("type");
            List<Bookings> bookings = null;

            if(type == "stays"){
                bookings = bookingRepository.findStaysByAfterDays(daysGap);
            }else{
                bookings = bookingRepository.findBookingsByAfterDays(daysGap);
            }


            List<Object> resultList = new ArrayList<>();
            for (Bookings booking:bookings){
                Optional<Guest> guest = guestRepository.findById(booking.getGuestId());
                Optional<Cabin> cabin = cabinRepository.findById(booking.getCabinId());



                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(booking);// obj is your object
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(json);

//                jsonObj.put("created_at",booking.getCreated_at().toString());
//                jsonObj.put("startDate",booking.getStartDate().toString());
//                jsonObj.put("endDate",booking.getEndDate().toString());

                jsonObj.put("cabins",cabin);
                jsonObj.put("guests",guest);


                resultList.add(jsonObj);
            }

            resultFinal.put(type,resultList);
            result.setResult(resultFinal);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.setStatus(500);

        }
        return result;
    }@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getStaysTodayActivity")
    public Result   getStaysTodayActivity() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
        try {
            JSONObject resultFinal = new JSONObject();


            List<Bookings> bookings = bookingRepository.getStaysTodayActivity();


            List<Object> resultList = new ArrayList<>();
            for (Bookings booking:bookings){
                Optional<Guest> guest = guestRepository.findById(booking.getGuestId());
                Optional<Cabin> cabin = cabinRepository.findById(booking.getCabinId());



                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(booking);// obj is your object
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(json);

//                jsonObj.put("created_at",booking.getCreated_at().toString());
//                jsonObj.put("startDate",booking.getStartDate().toString());
//                jsonObj.put("endDate",booking.getEndDate().toString());

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
    @PostMapping("/getBookingsByCabinId")
    public Result getBookingsByCabinId(@RequestBody JSONObject request) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        System.out.println(requestBooking);
        System.out.println(request);
//        JSONObject mergedJSON = me
        try {
            JSONObject resultFinal = new JSONObject();


            Integer cabinId;
            cabinId = request.get("cabinId") != null ? (Integer) request.get("cabinId") : null;

            Integer pageSize = (Integer) request.get("pageSize");
            Integer page = (Integer) request.get("page");

            Integer offset = (page - 1)*pageSize;
            Integer limitResult = pageSize;

            System.out.println(offset);
            System.out.println(limitResult);
            List<Bookings> bookings = null;

            bookings = bookingRepository.getBookingsByCabinId(cabinId,offset,limitResult);
//            bookingRepository.getBookingsByCabinIdWithLogging(cabinId,offset,limitResult);
            Integer count = bookingRepository.getCountOfBookingsByCabinId(cabinId);

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
    @PostMapping("/getBookingsByGuestId")
    public Result getBookingsByGuestId(@RequestBody JSONObject request) throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        System.out.println(requestBooking);
        System.out.println(request);
//        JSONObject mergedJSON = me
        try {
            JSONObject resultFinal = new JSONObject();


            Integer guestId;
            guestId = request.get("guestId") != null ? (Integer) request.get("guestId") : null;

            Integer pageSize = (Integer) request.get("pageSize");
            Integer page = (Integer) request.get("page");

            Integer offset = (page - 1)*pageSize;
            Integer limitResult = pageSize;

            System.out.println(offset);
            System.out.println(limitResult);
            List<Bookings> bookings = null;

            bookings = bookingRepository.getBookingsByGuestId(guestId,offset,limitResult);
//            bookingRepository.getBookingsByCabinIdWithLogging(cabinId,offset,limitResult);
            Integer count = bookingRepository.getCountOfBookingsByGuestId(guestId);

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
    @GetMapping("/getCountries")
    public Result getCabinById() throws IOException, ParseException {
        Result result = new Result();
        result.setError(null);
        result.setStatus(HttpStatus.OK.value());
//        JSONObject mergedJSON = me
        String response = "[{\"name\":\"Afghanistan\",\"flag\":\"https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_the_Taliban.svg\",\"independent\":false},{\"name\":\"Åland Islands\",\"flag\":\"https://flagcdn.com/ax.svg\",\"independent\":false},{\"name\":\"Albania\",\"flag\":\"https://flagcdn.com/al.svg\",\"independent\":false},{\"name\":\"Algeria\",\"flag\":\"https://flagcdn.com/dz.svg\",\"independent\":false},{\"name\":\"American Samoa\",\"flag\":\"https://flagcdn.com/as.svg\",\"independent\":false},{\"name\":\"Andorra\",\"flag\":\"https://flagcdn.com/ad.svg\",\"independent\":false},{\"name\":\"Angola\",\"flag\":\"https://flagcdn.com/ao.svg\",\"independent\":false},{\"name\":\"Anguilla\",\"flag\":\"https://flagcdn.com/ai.svg\",\"independent\":false},{\"name\":\"Antarctica\",\"flag\":\"https://flagcdn.com/aq.svg\",\"independent\":false},{\"name\":\"Antigua and Barbuda\",\"flag\":\"https://flagcdn.com/ag.svg\",\"independent\":false},{\"name\":\"Argentina\",\"flag\":\"https://flagcdn.com/ar.svg\",\"independent\":false},{\"name\":\"Armenia\",\"flag\":\"https://flagcdn.com/am.svg\",\"independent\":false},{\"name\":\"Aruba\",\"flag\":\"https://flagcdn.com/aw.svg\",\"independent\":false},{\"name\":\"Australia\",\"flag\":\"https://flagcdn.com/au.svg\",\"independent\":false},{\"name\":\"Austria\",\"flag\":\"https://flagcdn.com/at.svg\",\"independent\":false},{\"name\":\"Azerbaijan\",\"flag\":\"https://flagcdn.com/az.svg\",\"independent\":false},{\"name\":\"Bahamas\",\"flag\":\"https://flagcdn.com/bs.svg\",\"independent\":false},{\"name\":\"Bahrain\",\"flag\":\"https://flagcdn.com/bh.svg\",\"independent\":false},{\"name\":\"Bangladesh\",\"flag\":\"https://flagcdn.com/bd.svg\",\"independent\":false},{\"name\":\"Barbados\",\"flag\":\"https://flagcdn.com/bb.svg\",\"independent\":false},{\"name\":\"Belarus\",\"flag\":\"https://flagcdn.com/by.svg\",\"independent\":false},{\"name\":\"Belgium\",\"flag\":\"https://flagcdn.com/be.svg\",\"independent\":false},{\"name\":\"Belize\",\"flag\":\"https://flagcdn.com/bz.svg\",\"independent\":false},{\"name\":\"Benin\",\"flag\":\"https://flagcdn.com/bj.svg\",\"independent\":false},{\"name\":\"Bermuda\",\"flag\":\"https://flagcdn.com/bm.svg\",\"independent\":false},{\"name\":\"Bhutan\",\"flag\":\"https://flagcdn.com/bt.svg\",\"independent\":false},{\"name\":\"Bolivia (Plurinational State of)\",\"flag\":\"https://flagcdn.com/bo.svg\",\"independent\":false},{\"name\":\"Bonaire, Sint Eustatius and Saba\",\"flag\":\"https://flagcdn.com/bq.svg\",\"independent\":false},{\"name\":\"Bosnia and Herzegovina\",\"flag\":\"https://flagcdn.com/ba.svg\",\"independent\":false},{\"name\":\"Botswana\",\"flag\":\"https://flagcdn.com/bw.svg\",\"independent\":false},{\"name\":\"Bouvet Island\",\"flag\":\"https://flagcdn.com/bv.svg\",\"independent\":false},{\"name\":\"Brazil\",\"flag\":\"https://flagcdn.com/br.svg\",\"independent\":false},{\"name\":\"British Indian Ocean Territory\",\"flag\":\"https://flagcdn.com/io.svg\",\"independent\":false},{\"name\":\"United States Minor Outlying Islands\",\"flag\":\"https://flagcdn.com/um.svg\",\"independent\":false},{\"name\":\"Virgin Islands (British)\",\"flag\":\"https://flagcdn.com/vg.svg\",\"independent\":false},{\"name\":\"Virgin Islands (U.S.)\",\"flag\":\"https://flagcdn.com/vi.svg\",\"independent\":false},{\"name\":\"Brunei Darussalam\",\"flag\":\"https://flagcdn.com/bn.svg\",\"independent\":false},{\"name\":\"Bulgaria\",\"flag\":\"https://flagcdn.com/bg.svg\",\"independent\":false},{\"name\":\"Burkina Faso\",\"flag\":\"https://flagcdn.com/bf.svg\",\"independent\":false},{\"name\":\"Burundi\",\"flag\":\"https://flagcdn.com/bi.svg\",\"independent\":false},{\"name\":\"Cambodia\",\"flag\":\"https://flagcdn.com/kh.svg\",\"independent\":false},{\"name\":\"Cameroon\",\"flag\":\"https://flagcdn.com/cm.svg\",\"independent\":false},{\"name\":\"Canada\",\"flag\":\"https://flagcdn.com/ca.svg\",\"independent\":false},{\"name\":\"Cabo Verde\",\"flag\":\"https://flagcdn.com/cv.svg\",\"independent\":false},{\"name\":\"Cayman Islands\",\"flag\":\"https://flagcdn.com/ky.svg\",\"independent\":false},{\"name\":\"Central African Republic\",\"flag\":\"https://flagcdn.com/cf.svg\",\"independent\":false},{\"name\":\"Chad\",\"flag\":\"https://flagcdn.com/td.svg\",\"independent\":false},{\"name\":\"Chile\",\"flag\":\"https://flagcdn.com/cl.svg\",\"independent\":false},{\"name\":\"China\",\"flag\":\"https://flagcdn.com/cn.svg\",\"independent\":false},{\"name\":\"Christmas Island\",\"flag\":\"https://flagcdn.com/cx.svg\",\"independent\":false},{\"name\":\"Cocos (Keeling) Islands\",\"flag\":\"https://flagcdn.com/cc.svg\",\"independent\":false},{\"name\":\"Colombia\",\"flag\":\"https://flagcdn.com/co.svg\",\"independent\":false},{\"name\":\"Comoros\",\"flag\":\"https://flagcdn.com/km.svg\",\"independent\":false},{\"name\":\"Congo\",\"flag\":\"https://flagcdn.com/cg.svg\",\"independent\":false},{\"name\":\"Congo (Democratic Republic of the)\",\"flag\":\"https://flagcdn.com/cd.svg\",\"independent\":false},{\"name\":\"Cook Islands\",\"flag\":\"https://flagcdn.com/ck.svg\",\"independent\":false},{\"name\":\"Costa Rica\",\"flag\":\"https://flagcdn.com/cr.svg\",\"independent\":false},{\"name\":\"Croatia\",\"flag\":\"https://flagcdn.com/hr.svg\",\"independent\":false},{\"name\":\"Cuba\",\"flag\":\"https://flagcdn.com/cu.svg\",\"independent\":false},{\"name\":\"Curaçao\",\"flag\":\"https://flagcdn.com/cw.svg\",\"independent\":false},{\"name\":\"Cyprus\",\"flag\":\"https://flagcdn.com/cy.svg\",\"independent\":false},{\"name\":\"Czech Republic\",\"flag\":\"https://flagcdn.com/cz.svg\",\"independent\":false},{\"name\":\"Denmark\",\"flag\":\"https://flagcdn.com/dk.svg\",\"independent\":false},{\"name\":\"Djibouti\",\"flag\":\"https://flagcdn.com/dj.svg\",\"independent\":false},{\"name\":\"Dominica\",\"flag\":\"https://flagcdn.com/dm.svg\",\"independent\":false},{\"name\":\"Dominican Republic\",\"flag\":\"https://flagcdn.com/do.svg\",\"independent\":false},{\"name\":\"Ecuador\",\"flag\":\"https://flagcdn.com/ec.svg\",\"independent\":false},{\"name\":\"Egypt\",\"flag\":\"https://flagcdn.com/eg.svg\",\"independent\":false},{\"name\":\"El Salvador\",\"flag\":\"https://flagcdn.com/sv.svg\",\"independent\":false},{\"name\":\"Equatorial Guinea\",\"flag\":\"https://flagcdn.com/gq.svg\",\"independent\":false},{\"name\":\"Eritrea\",\"flag\":\"https://flagcdn.com/er.svg\",\"independent\":false},{\"name\":\"Estonia\",\"flag\":\"https://flagcdn.com/ee.svg\",\"independent\":false},{\"name\":\"Ethiopia\",\"flag\":\"https://flagcdn.com/et.svg\",\"independent\":false},{\"name\":\"Falkland Islands (Malvinas)\",\"flag\":\"https://flagcdn.com/fk.svg\",\"independent\":false},{\"name\":\"Faroe Islands\",\"flag\":\"https://flagcdn.com/fo.svg\",\"independent\":false},{\"name\":\"Fiji\",\"flag\":\"https://flagcdn.com/fj.svg\",\"independent\":false},{\"name\":\"Finland\",\"flag\":\"https://flagcdn.com/fi.svg\",\"independent\":false},{\"name\":\"France\",\"flag\":\"https://flagcdn.com/fr.svg\",\"independent\":false},{\"name\":\"French Guiana\",\"flag\":\"https://flagcdn.com/gf.svg\",\"independent\":false},{\"name\":\"French Polynesia\",\"flag\":\"https://flagcdn.com/pf.svg\",\"independent\":false},{\"name\":\"French Southern Territories\",\"flag\":\"https://flagcdn.com/tf.svg\",\"independent\":false},{\"name\":\"Gabon\",\"flag\":\"https://flagcdn.com/ga.svg\",\"independent\":false},{\"name\":\"Gambia\",\"flag\":\"https://flagcdn.com/gm.svg\",\"independent\":false},{\"name\":\"Georgia\",\"flag\":\"https://flagcdn.com/ge.svg\",\"independent\":false},{\"name\":\"Germany\",\"flag\":\"https://flagcdn.com/de.svg\",\"independent\":false},{\"name\":\"Ghana\",\"flag\":\"https://flagcdn.com/gh.svg\",\"independent\":false},{\"name\":\"Gibraltar\",\"flag\":\"https://flagcdn.com/gi.svg\",\"independent\":false},{\"name\":\"Greece\",\"flag\":\"https://flagcdn.com/gr.svg\",\"independent\":false},{\"name\":\"Greenland\",\"flag\":\"https://flagcdn.com/gl.svg\",\"independent\":false},{\"name\":\"Grenada\",\"flag\":\"https://flagcdn.com/gd.svg\",\"independent\":false},{\"name\":\"Guadeloupe\",\"flag\":\"https://flagcdn.com/gp.svg\",\"independent\":false},{\"name\":\"Guam\",\"flag\":\"https://flagcdn.com/gu.svg\",\"independent\":false},{\"name\":\"Guatemala\",\"flag\":\"https://flagcdn.com/gt.svg\",\"independent\":false},{\"name\":\"Guernsey\",\"flag\":\"https://flagcdn.com/gg.svg\",\"independent\":false},{\"name\":\"Guinea\",\"flag\":\"https://flagcdn.com/gn.svg\",\"independent\":false},{\"name\":\"Guinea-Bissau\",\"flag\":\"https://flagcdn.com/gw.svg\",\"independent\":false},{\"name\":\"Guyana\",\"flag\":\"https://flagcdn.com/gy.svg\",\"independent\":false},{\"name\":\"Haiti\",\"flag\":\"https://flagcdn.com/ht.svg\",\"independent\":false},{\"name\":\"Heard Island and McDonald Islands\",\"flag\":\"https://flagcdn.com/hm.svg\",\"independent\":false},{\"name\":\"Vatican City\",\"flag\":\"https://flagcdn.com/va.svg\",\"independent\":false},{\"name\":\"Honduras\",\"flag\":\"https://flagcdn.com/hn.svg\",\"independent\":false},{\"name\":\"Hungary\",\"flag\":\"https://flagcdn.com/hu.svg\",\"independent\":false},{\"name\":\"Hong Kong\",\"flag\":\"https://flagcdn.com/hk.svg\",\"independent\":false},{\"name\":\"Iceland\",\"flag\":\"https://flagcdn.com/is.svg\",\"independent\":false},{\"name\":\"India\",\"flag\":\"https://flagcdn.com/in.svg\",\"independent\":false},{\"name\":\"Indonesia\",\"flag\":\"https://flagcdn.com/id.svg\",\"independent\":false},{\"name\":\"Ivory Coast\",\"flag\":\"https://flagcdn.com/ci.svg\",\"independent\":false},{\"name\":\"Iran (Islamic Republic of)\",\"flag\":\"https://flagcdn.com/ir.svg\",\"independent\":false},{\"name\":\"Iraq\",\"flag\":\"https://flagcdn.com/iq.svg\",\"independent\":false},{\"name\":\"Ireland\",\"flag\":\"https://flagcdn.com/ie.svg\",\"independent\":false},{\"name\":\"Isle of Man\",\"flag\":\"https://flagcdn.com/im.svg\",\"independent\":false},{\"name\":\"Israel\",\"flag\":\"https://flagcdn.com/il.svg\",\"independent\":false},{\"name\":\"Italy\",\"flag\":\"https://flagcdn.com/it.svg\",\"independent\":false},{\"name\":\"Jamaica\",\"flag\":\"https://flagcdn.com/jm.svg\",\"independent\":false},{\"name\":\"Japan\",\"flag\":\"https://flagcdn.com/jp.svg\",\"independent\":false},{\"name\":\"Jersey\",\"flag\":\"https://flagcdn.com/je.svg\",\"independent\":false},{\"name\":\"Jordan\",\"flag\":\"https://flagcdn.com/jo.svg\",\"independent\":false},{\"name\":\"Kazakhstan\",\"flag\":\"https://flagcdn.com/kz.svg\",\"independent\":false},{\"name\":\"Kenya\",\"flag\":\"https://flagcdn.com/ke.svg\",\"independent\":false},{\"name\":\"Kiribati\",\"flag\":\"https://flagcdn.com/ki.svg\",\"independent\":false},{\"name\":\"Kuwait\",\"flag\":\"https://flagcdn.com/kw.svg\",\"independent\":false},{\"name\":\"Kyrgyzstan\",\"flag\":\"https://flagcdn.com/kg.svg\",\"independent\":false},{\"name\":\"Lao People's Democratic Republic\",\"flag\":\"https://flagcdn.com/la.svg\",\"independent\":false},{\"name\":\"Latvia\",\"flag\":\"https://flagcdn.com/lv.svg\",\"independent\":false},{\"name\":\"Lebanon\",\"flag\":\"https://flagcdn.com/lb.svg\",\"independent\":false},{\"name\":\"Lesotho\",\"flag\":\"https://flagcdn.com/ls.svg\",\"independent\":false},{\"name\":\"Liberia\",\"flag\":\"https://flagcdn.com/lr.svg\",\"independent\":false},{\"name\":\"Libya\",\"flag\":\"https://flagcdn.com/ly.svg\",\"independent\":false},{\"name\":\"Liechtenstein\",\"flag\":\"https://flagcdn.com/li.svg\",\"independent\":false},{\"name\":\"Lithuania\",\"flag\":\"https://flagcdn.com/lt.svg\",\"independent\":false},{\"name\":\"Luxembourg\",\"flag\":\"https://flagcdn.com/lu.svg\",\"independent\":false},{\"name\":\"Macao\",\"flag\":\"https://flagcdn.com/mo.svg\",\"independent\":false},{\"name\":\"North Macedonia\",\"flag\":\"https://flagcdn.com/mk.svg\",\"independent\":false},{\"name\":\"Madagascar\",\"flag\":\"https://flagcdn.com/mg.svg\",\"independent\":false},{\"name\":\"Malawi\",\"flag\":\"https://flagcdn.com/mw.svg\",\"independent\":false},{\"name\":\"Malaysia\",\"flag\":\"https://flagcdn.com/my.svg\",\"independent\":false},{\"name\":\"Maldives\",\"flag\":\"https://flagcdn.com/mv.svg\",\"independent\":false},{\"name\":\"Mali\",\"flag\":\"https://flagcdn.com/ml.svg\",\"independent\":false},{\"name\":\"Malta\",\"flag\":\"https://flagcdn.com/mt.svg\",\"independent\":false},{\"name\":\"Marshall Islands\",\"flag\":\"https://flagcdn.com/mh.svg\",\"independent\":false},{\"name\":\"Martinique\",\"flag\":\"https://flagcdn.com/mq.svg\",\"independent\":false},{\"name\":\"Mauritania\",\"flag\":\"https://flagcdn.com/mr.svg\",\"independent\":false},{\"name\":\"Mauritius\",\"flag\":\"https://flagcdn.com/mu.svg\",\"independent\":false},{\"name\":\"Mayotte\",\"flag\":\"https://flagcdn.com/yt.svg\",\"independent\":false},{\"name\":\"Mexico\",\"flag\":\"https://flagcdn.com/mx.svg\",\"independent\":false},{\"name\":\"Micronesia (Federated States of)\",\"flag\":\"https://flagcdn.com/fm.svg\",\"independent\":false},{\"name\":\"Moldova (Republic of)\",\"flag\":\"https://flagcdn.com/md.svg\",\"independent\":false},{\"name\":\"Monaco\",\"flag\":\"https://flagcdn.com/mc.svg\",\"independent\":false},{\"name\":\"Mongolia\",\"flag\":\"https://flagcdn.com/mn.svg\",\"independent\":false},{\"name\":\"Montenegro\",\"flag\":\"https://flagcdn.com/me.svg\",\"independent\":false},{\"name\":\"Montserrat\",\"flag\":\"https://flagcdn.com/ms.svg\",\"independent\":false},{\"name\":\"Morocco\",\"flag\":\"https://flagcdn.com/ma.svg\",\"independent\":false},{\"name\":\"Mozambique\",\"flag\":\"https://flagcdn.com/mz.svg\",\"independent\":false},{\"name\":\"Myanmar\",\"flag\":\"https://flagcdn.com/mm.svg\",\"independent\":false},{\"name\":\"Namibia\",\"flag\":\"https://flagcdn.com/na.svg\",\"independent\":false},{\"name\":\"Nauru\",\"flag\":\"https://flagcdn.com/nr.svg\",\"independent\":false},{\"name\":\"Nepal\",\"flag\":\"https://flagcdn.com/np.svg\",\"independent\":false},{\"name\":\"Netherlands\",\"flag\":\"https://flagcdn.com/nl.svg\",\"independent\":false},{\"name\":\"New Caledonia\",\"flag\":\"https://flagcdn.com/nc.svg\",\"independent\":false},{\"name\":\"New Zealand\",\"flag\":\"https://flagcdn.com/nz.svg\",\"independent\":false},{\"name\":\"Nicaragua\",\"flag\":\"https://flagcdn.com/ni.svg\",\"independent\":false},{\"name\":\"Niger\",\"flag\":\"https://flagcdn.com/ne.svg\",\"independent\":false},{\"name\":\"Nigeria\",\"flag\":\"https://flagcdn.com/ng.svg\",\"independent\":false},{\"name\":\"Niue\",\"flag\":\"https://flagcdn.com/nu.svg\",\"independent\":false},{\"name\":\"Norfolk Island\",\"flag\":\"https://flagcdn.com/nf.svg\",\"independent\":false},{\"name\":\"Korea (Democratic People's Republic of)\",\"flag\":\"https://flagcdn.com/kp.svg\",\"independent\":false},{\"name\":\"Northern Mariana Islands\",\"flag\":\"https://flagcdn.com/mp.svg\",\"independent\":false},{\"name\":\"Norway\",\"flag\":\"https://flagcdn.com/no.svg\",\"independent\":false},{\"name\":\"Oman\",\"flag\":\"https://flagcdn.com/om.svg\",\"independent\":false},{\"name\":\"Pakistan\",\"flag\":\"https://flagcdn.com/pk.svg\",\"independent\":false},{\"name\":\"Palau\",\"flag\":\"https://flagcdn.com/pw.svg\",\"independent\":false},{\"name\":\"Palestine, State of\",\"flag\":\"https://flagcdn.com/ps.svg\",\"independent\":false},{\"name\":\"Panama\",\"flag\":\"https://flagcdn.com/pa.svg\",\"independent\":false},{\"name\":\"Papua New Guinea\",\"flag\":\"https://flagcdn.com/pg.svg\",\"independent\":false},{\"name\":\"Paraguay\",\"flag\":\"https://flagcdn.com/py.svg\",\"independent\":false},{\"name\":\"Peru\",\"flag\":\"https://flagcdn.com/pe.svg\",\"independent\":false},{\"name\":\"Philippines\",\"flag\":\"https://flagcdn.com/ph.svg\",\"independent\":false},{\"name\":\"Pitcairn\",\"flag\":\"https://flagcdn.com/pn.svg\",\"independent\":false},{\"name\":\"Poland\",\"flag\":\"https://flagcdn.com/pl.svg\",\"independent\":false},{\"name\":\"Portugal\",\"flag\":\"https://flagcdn.com/pt.svg\",\"independent\":false},{\"name\":\"Puerto Rico\",\"flag\":\"https://flagcdn.com/pr.svg\",\"independent\":false},{\"name\":\"Qatar\",\"flag\":\"https://flagcdn.com/qa.svg\",\"independent\":false},{\"name\":\"Republic of Kosovo\",\"flag\":\"https://flagcdn.com/xk.svg\",\"independent\":false},{\"name\":\"Réunion\",\"flag\":\"https://flagcdn.com/re.svg\",\"independent\":false},{\"name\":\"Romania\",\"flag\":\"https://flagcdn.com/ro.svg\",\"independent\":false},{\"name\":\"Russian Federation\",\"flag\":\"https://flagcdn.com/ru.svg\",\"independent\":false},{\"name\":\"Rwanda\",\"flag\":\"https://flagcdn.com/rw.svg\",\"independent\":false},{\"name\":\"Saint Barthélemy\",\"flag\":\"https://flagcdn.com/bl.svg\",\"independent\":false},{\"name\":\"Saint Helena, Ascension and Tristan da Cunha\",\"flag\":\"https://flagcdn.com/sh.svg\",\"independent\":false},{\"name\":\"Saint Kitts and Nevis\",\"flag\":\"https://flagcdn.com/kn.svg\",\"independent\":false},{\"name\":\"Saint Lucia\",\"flag\":\"https://flagcdn.com/lc.svg\",\"independent\":false},{\"name\":\"Saint Martin (French part)\",\"flag\":\"https://flagcdn.com/mf.svg\",\"independent\":false},{\"name\":\"Saint Pierre and Miquelon\",\"flag\":\"https://flagcdn.com/pm.svg\",\"independent\":false},{\"name\":\"Saint Vincent and the Grenadines\",\"flag\":\"https://flagcdn.com/vc.svg\",\"independent\":false},{\"name\":\"Samoa\",\"flag\":\"https://flagcdn.com/ws.svg\",\"independent\":false},{\"name\":\"San Marino\",\"flag\":\"https://flagcdn.com/sm.svg\",\"independent\":false},{\"name\":\"Sao Tome and Principe\",\"flag\":\"https://flagcdn.com/st.svg\",\"independent\":false},{\"name\":\"Saudi Arabia\",\"flag\":\"https://flagcdn.com/sa.svg\",\"independent\":false},{\"name\":\"Senegal\",\"flag\":\"https://flagcdn.com/sn.svg\",\"independent\":false},{\"name\":\"Serbia\",\"flag\":\"https://flagcdn.com/rs.svg\",\"independent\":false},{\"name\":\"Seychelles\",\"flag\":\"https://flagcdn.com/sc.svg\",\"independent\":false},{\"name\":\"Sierra Leone\",\"flag\":\"https://flagcdn.com/sl.svg\",\"independent\":false},{\"name\":\"Singapore\",\"flag\":\"https://flagcdn.com/sg.svg\",\"independent\":false},{\"name\":\"Sint Maarten (Dutch part)\",\"flag\":\"https://flagcdn.com/sx.svg\",\"independent\":false},{\"name\":\"Slovakia\",\"flag\":\"https://flagcdn.com/sk.svg\",\"independent\":false},{\"name\":\"Slovenia\",\"flag\":\"https://flagcdn.com/si.svg\",\"independent\":false},{\"name\":\"Solomon Islands\",\"flag\":\"https://flagcdn.com/sb.svg\",\"independent\":false},{\"name\":\"Somalia\",\"flag\":\"https://flagcdn.com/so.svg\",\"independent\":false},{\"name\":\"South Africa\",\"flag\":\"https://flagcdn.com/za.svg\",\"independent\":false},{\"name\":\"South Georgia and the South Sandwich Islands\",\"flag\":\"https://flagcdn.com/gs.svg\",\"independent\":false},{\"name\":\"Korea (Republic of)\",\"flag\":\"https://flagcdn.com/kr.svg\",\"independent\":false},{\"name\":\"Spain\",\"flag\":\"https://flagcdn.com/es.svg\",\"independent\":false},{\"name\":\"Sri Lanka\",\"flag\":\"https://flagcdn.com/lk.svg\",\"independent\":false},{\"name\":\"Sudan\",\"flag\":\"https://flagcdn.com/sd.svg\",\"independent\":false},{\"name\":\"South Sudan\",\"flag\":\"https://flagcdn.com/ss.svg\",\"independent\":false},{\"name\":\"Suriname\",\"flag\":\"https://flagcdn.com/sr.svg\",\"independent\":false},{\"name\":\"Svalbard and Jan Mayen\",\"flag\":\"https://flagcdn.com/sj.svg\",\"independent\":false},{\"name\":\"Swaziland\",\"flag\":\"https://flagcdn.com/sz.svg\",\"independent\":false},{\"name\":\"Sweden\",\"flag\":\"https://flagcdn.com/se.svg\",\"independent\":false},{\"name\":\"Switzerland\",\"flag\":\"https://flagcdn.com/ch.svg\",\"independent\":false},{\"name\":\"Syrian Arab Republic\",\"flag\":\"https://flagcdn.com/sy.svg\",\"independent\":false},{\"name\":\"Taiwan\",\"flag\":\"https://flagcdn.com/tw.svg\",\"independent\":false},{\"name\":\"Tajikistan\",\"flag\":\"https://flagcdn.com/tj.svg\",\"independent\":false},{\"name\":\"Tanzania, United Republic of\",\"flag\":\"https://flagcdn.com/tz.svg\",\"independent\":false},{\"name\":\"Thailand\",\"flag\":\"https://flagcdn.com/th.svg\",\"independent\":false},{\"name\":\"Timor-Leste\",\"flag\":\"https://flagcdn.com/tl.svg\",\"independent\":false},{\"name\":\"Togo\",\"flag\":\"https://flagcdn.com/tg.svg\",\"independent\":false},{\"name\":\"Tokelau\",\"flag\":\"https://flagcdn.com/tk.svg\",\"independent\":false},{\"name\":\"Tonga\",\"flag\":\"https://flagcdn.com/to.svg\",\"independent\":false},{\"name\":\"Trinidad and Tobago\",\"flag\":\"https://flagcdn.com/tt.svg\",\"independent\":false},{\"name\":\"Tunisia\",\"flag\":\"https://flagcdn.com/tn.svg\",\"independent\":false},{\"name\":\"Turkey\",\"flag\":\"https://flagcdn.com/tr.svg\",\"independent\":false},{\"name\":\"Turkmenistan\",\"flag\":\"https://flagcdn.com/tm.svg\",\"independent\":false},{\"name\":\"Turks and Caicos Islands\",\"flag\":\"https://flagcdn.com/tc.svg\",\"independent\":false},{\"name\":\"Tuvalu\",\"flag\":\"https://flagcdn.com/tv.svg\",\"independent\":false},{\"name\":\"Uganda\",\"flag\":\"https://flagcdn.com/ug.svg\",\"independent\":false},{\"name\":\"Ukraine\",\"flag\":\"https://flagcdn.com/ua.svg\",\"independent\":false},{\"name\":\"United Arab Emirates\",\"flag\":\"https://flagcdn.com/ae.svg\",\"independent\":false},{\"name\":\"United Kingdom of Great Britain and Northern Ireland\",\"flag\":\"https://flagcdn.com/gb.svg\",\"independent\":false},{\"name\":\"United States of America\",\"flag\":\"https://flagcdn.com/us.svg\",\"independent\":false},{\"name\":\"Uruguay\",\"flag\":\"https://flagcdn.com/uy.svg\",\"independent\":false},{\"name\":\"Uzbekistan\",\"flag\":\"https://flagcdn.com/uz.svg\",\"independent\":false},{\"name\":\"Vanuatu\",\"flag\":\"https://flagcdn.com/vu.svg\",\"independent\":false},{\"name\":\"Venezuela (Bolivarian Republic of)\",\"flag\":\"https://flagcdn.com/ve.svg\",\"independent\":false},{\"name\":\"Vietnam\",\"flag\":\"https://flagcdn.com/vn.svg\",\"independent\":false},{\"name\":\"Wallis and Futuna\",\"flag\":\"https://flagcdn.com/wf.svg\",\"independent\":false},{\"name\":\"Western Sahara\",\"flag\":\"https://flagcdn.com/eh.svg\",\"independent\":false},{\"name\":\"Yemen\",\"flag\":\"https://flagcdn.com/ye.svg\",\"independent\":false},{\"name\":\"Zambia\",\"flag\":\"https://flagcdn.com/zm.svg\",\"independent\":false},{\"name\":\"Zimbabwe\",\"flag\":\"https://flagcdn.com/zw.svg\",\"independent\":false}]";

    try {
        JSONParser parser = new JSONParser();
        JSONArray jsonObj = (JSONArray) parser.parse(response);
            result.setResult(jsonObj);
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
