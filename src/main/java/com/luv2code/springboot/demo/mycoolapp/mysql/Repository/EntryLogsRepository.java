package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Accounts;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Bookings;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.EntryLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryLogsRepository extends JpaRepository<EntryLogs, Integer> {
    @Query(value="SELECT * from entry_logs as e WHERE reference_id = :bankId AND created_at >= DATE_SUB(NOW(), INTERVAL :dayGap DAY) ",nativeQuery = true)
    public List<EntryLogs> findEntriesByAfterDays(@Param("bankId") Integer bankId, @Param("dayGap") Integer dayGap);

}