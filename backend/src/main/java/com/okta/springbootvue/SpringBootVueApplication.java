package com.okta.springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;



import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(CancelReasonRepository cancelReasonRepository,
	SexRepository sexRepository,TypeNameRepository typeNameRepository  ,  QuestionRepository questionRepository,
		ShowRepository showRepository, TimeRepository timeRepository, ShowLocationRepository showLocationRepository, ZoneRepository zoneRepository, SeatRepository seatRepository) {
		return args -> {

			//cancelReason
			Stream.of("ติดธุระ", "โดนสปอยล์", "ป่วย", "ไม่มีคู่ไปดู").forEach(name -> {
				CancelReason cReason = new CancelReason();
				cReason.setReason(name); 
				cancelReasonRepository.save(cReason); 
			});

			//----------------------------------------------------------------------
			Stream.of("ชาย", "หญิง", "ไม่ระบุ").forEach(name -> {
				Sex sex = new Sex();
				sex.setName(name); 
				sexRepository.save(sex); 
			});

			Stream.of("นาย", "นางสาว", "นาง").forEach(name -> {
				TypeName typename = new TypeName(); 
				typename.setName(name);
				typeNameRepository.save(typename); 
			});

			Stream.of("บ้านเกิดคุณอยู่ที่ไหน", "สัตว์เลี้ยงของคุณชื่ออะไร", "นักร้องที่คุณชื่นชอบ" ,"สถานที่ที่คุณชื่นชอบ").forEach(name -> {
				Question question = new Question(); 
				question.setName(name); 
				questionRepository.save(question); 
			});




			//---------------------------------------------------------
			Stream.of("IU Concert", "Dancing Bug", "Somchai voice", "Opala").forEach(name -> {
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

			// zone 

			//zone C
			Zone zoneC = new Zone();
			zoneC.setName("C");
			zoneC.setPrice(1500);
			zoneRepository.save(zoneC);

			//zone D
			Zone zoneD = new Zone();
			zoneD.setName("D");
			zoneD.setPrice(2500);
			zoneRepository.save(zoneD);

			//zone E
			Zone zoneE = new Zone();
			zoneE.setName("E");
			zoneE.setPrice(3500);
			zoneRepository.save(zoneE);

			// seat 

			//zone C
			Seat C1 = new Seat();
			C1.setSeatNum("C1");
			C1.setChooseSeat(zoneC);
			C1.setStatus("N");
			seatRepository.save(C1);
			
			Seat C2 = new Seat();
			C2.setSeatNum("C2");
			C2.setChooseSeat(zoneC);
			C2.setStatus("N");
			seatRepository.save(C2);
			
			Seat C3 = new Seat();
			C3.setSeatNum("C3");
			C3.setChooseSeat(zoneC);
			C3.setStatus("N");
			seatRepository.save(C3);

			//zone D
			Seat D1 = new Seat();
			D1.setSeatNum("D1");
			D1.setChooseSeat(zoneD);
			D1.setStatus("N");
			seatRepository.save(D1);
			
			Seat D2 = new Seat();
			D2.setSeatNum("D2");
			D2.setChooseSeat(zoneD);
			D2.setStatus("N");
			seatRepository.save(D2);
			
			Seat D3 = new Seat();
			D3.setSeatNum("D3");
			D3.setChooseSeat(zoneD);
			D3.setStatus("N");
			seatRepository.save(D3);
			
			//zone E
			Seat E1 = new Seat();
			E1.setSeatNum("E1");
			E1.setChooseSeat(zoneE);
			E1.setStatus("N");
			seatRepository.save(E1);
			
			Seat E2 = new Seat();
			E2.setSeatNum("E2");
			E2.setChooseSeat(zoneE);
			E2.setStatus("N");
			seatRepository.save(E2);
			
			Seat E3 = new Seat();
			E3.setSeatNum("E3");
			E3.setChooseSeat(zoneE);
			E3.setStatus("N");
			seatRepository.save(E3);

			showRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
			timeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
			showLocationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity RentalType บน Terminal
			zoneRepository.findAll().forEach(System.out::println); 
			seatRepository.findAll().forEach(System.out::println);
			
		};
	}
	
}
