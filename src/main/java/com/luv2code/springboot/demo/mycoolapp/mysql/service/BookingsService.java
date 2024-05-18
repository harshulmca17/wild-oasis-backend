package com.luv2code.springboot.demo.mycoolapp.mysql.service;

import com.luv2code.springboot.demo.mycoolapp.mysql.Repository.BookingRepository;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BookingsService {


    public abstract List<Bookings> findByFilter(Bookings filter);
}