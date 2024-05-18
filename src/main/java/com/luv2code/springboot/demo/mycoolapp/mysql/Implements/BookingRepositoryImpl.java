package com.luv2code.springboot.demo.mycoolapp.mysql.Implements;


import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.BookingRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepositoryImpl extends BookingsService {


    @Override
    public List<Bookings> findByFilter(Bookings filter) {
        System.out.println(filter);
        List<Bookings> bookingsList = new ArrayList<>();
        return bookingsList;

    }


}