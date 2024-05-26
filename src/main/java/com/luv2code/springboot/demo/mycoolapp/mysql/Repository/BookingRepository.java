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

//    SELECT *
//    FROM bookings
//    WHERE end_date > DATE_SUB(NOW(), INTERVAL 30 DAY) LIMIT 100

    @Query(value="SELECT * from bookings as e WHERE end_date > DATE_SUB(NOW(), INTERVAL :dayGap DAY)  and end_date <= DATE(NOW())  ",nativeQuery = true)
    public List<Bookings> findBookingsByAfterDays(@Param("dayGap") Integer dayGap);


    @Query(value="SELECT * from bookings as e WHERE start_date > DATE_SUB(NOW(), INTERVAL :dayGap DAY)  and start_date <= DATE(NOW())  ",nativeQuery = true)
    public List<Bookings> findStaysByAfterDays(@Param("dayGap") Integer dayGap);


    @Query(value="SELECT * from bookings as e WHERE ((DATE(start_date) = DATE(NOW()) AND e.status = 'unconfirmed' ) OR (DATE(end_date) = DATE(NOW()) AND e.status = 'checked-in'))  ",nativeQuery = true)
    public List<Bookings> getStaysTodayActivity();



    @Query(value = "SELECT COUNT(*) FROM bookings AS e WHERE e.status = :status", nativeQuery = true)
    public int countByStatus(@Param("status") String status);

    @Query(value = "SELECT COUNT(*) FROM bookings AS e ", nativeQuery = true)
    public int countByStatus();
}