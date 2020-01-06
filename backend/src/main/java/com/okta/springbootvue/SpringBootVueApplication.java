package com.okta.springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

import com.okta.springbootvue.Showtime.entity.Show;
import com.okta.springbootvue.Showtime.entity.ShowLocation;
import com.okta.springbootvue.Showtime.entity.Time;
import com.okta.springbootvue.Showtime.repository.ShowLocationRepository;
import com.okta.springbootvue.Showtime.repository.ShowRepository;
import com.okta.springbootvue.Showtime.repository.TimeRepository;

@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}
	@Bean
	ApplicationRunner init(ShowRepository showRepository, TimeRepository timeRepository, ShowLocationRepository showLocationRepository) {
		return args -> {
			Stream.of("IU Consert", "Dancing Bug", "Somchai voice", "Opala").forEach(name -> {
				Show show = new Show(); 
				show.setTitle(name); 
				showRepository.save(show); 
			});

			Stream.of("10.00-12.00", "13.00-16.00", "9.00-11.00", "17.00-19.00").forEach(name -> {
				Time time = new Time(); 
				time.setTime(name); 
				timeRepository.save(time); 
			});

			Stream.of("Impact arena", "RCA", "MT","PaCha","illu hall").forEach(name -> {
				ShowLocation location = new ShowLocation(); 
				location.setLocation(name); 
				showLocationRepository.save(location); 
			});

			

			showRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
			timeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
			showLocationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity RentalType บน Terminal
			
		};
	}
	
}
