package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    @Query(value="SELECT * FROM guest WHERE email = :email ",nativeQuery = true)
    public Guest getGuestByEmail(@Param("email") String email);

}