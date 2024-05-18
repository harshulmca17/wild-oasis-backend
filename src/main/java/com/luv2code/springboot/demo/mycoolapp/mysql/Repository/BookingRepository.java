package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {
    @Query(value="SELECT * from bookings as e WHERE e.status =:status ORDER BY  :sortfield  :sortDirection limit :offset,:limit ",nativeQuery = true)
    public List<Bookings> findByFilter(@Param("status") String status,@Param("sortfield") String sortfield,@Param("sortDirection") String sortDirection,@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Query(value="SELECT * from bookings as e ORDER BY  :sortfield  :sortDirection limit :offset,:limit ",nativeQuery = true)
    public List<Bookings> findByFilterAllStatus(@Param("sortfield") String sortfield,@Param("sortDirection") String sortDirection,@Param("offset") Integer offset,@Param("limit") Integer limit);


    @Query(value = "SELECT COUNT(*) FROM bookings AS e WHERE e.status = :status", nativeQuery = true)
    public int countByStatus(@Param("status") String status);

    @Query(value = "SELECT COUNT(*) FROM bookings AS e ", nativeQuery = true)
    public int countByStatus();
}