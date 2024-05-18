package com.luv2code.springboot.demo.mycoolapp.mysql.Repository;


import com.luv2code.springboot.demo.mycoolapp.mysql.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Settings, Integer> {

}