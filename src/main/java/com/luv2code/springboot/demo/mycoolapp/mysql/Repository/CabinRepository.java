package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Integer> {

}