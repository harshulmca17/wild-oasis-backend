package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Accounts;
import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Deposites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositesRepository extends JpaRepository<Deposites, Integer> {

}