package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Guest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    @Query(value="SELECT * FROM guest WHERE email = :email ",nativeQuery = true)
    public Guest getGuestByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Guest g SET g.nationalID = :nationalID, g.nationality = :nationality, g.countryFlag = :countryFlag WHERE g.id = :id")
    int updateGuestDetails(@Param("id") Integer id,
                           @Param("nationalID") String nationalID,
                           @Param("nationality") String nationality,
                           @Param("countryFlag") String countryFlag);
}