package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import com.okta.springbootvue.entity.Show;
import com.okta.springbootvue.entity.Showtime;
import com.okta.springbootvue.entity.Time;
import com.okta.springbootvue.entity.ShowLocation;
import com.okta.springbootvue.repository.ShowRepository;
import com.okta.springbootvue.repository.TimeRepository;
import com.okta.springbootvue.repository.ShowtimeRepository;
import com.okta.springbootvue.repository.ShowLocationRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class ShowtimeController {
    @Autowired
    private final ShowtimeRepository showtimeRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowLocationRepository showLocationRepository;
    @Autowired
    private TimeRepository timeRepository;
    

    ShowtimeController(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }


   @GetMapping("/showtimeD/showtimeid={id}")
    public Collection<Showtime> getShowtimeByShow(@PathVariable("id") Long id) {
        return showtimeRepository.findShowtimeByShow(id);
    }
  
    @GetMapping("/showtime/showtimeid={id}")
    public Collection<Showtime> findDatetime(@PathVariable("id") Long id) {
        return showtimeRepository.findShowtime(id);
    }
    
  
   
  
    @GetMapping("/showDatetime/showid={date}")
    public Collection<Showtime> getShowtimeByShow(@PathVariable("date") String date) throws ParseException {
         DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date showdate = d.parse(date);
    return showtimeRepository.findDatetime(showdate);
        }

    
    @GetMapping("/showtimeCheck/{show_id}/{time_id}/{location_id}/{showdatetime}")
    public Collection<Showtime> getSubjectsByStudent(@PathVariable("show_id") Long show_id,
    @PathVariable("time_id") Long time_id,
    @PathVariable("location_id") Long location_id,
    @PathVariable("showdatetime") String showdatetime) 
    throws ParseException {
    DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    Date showdate = d.parse(showdatetime);
        return showtimeRepository.findShowtimeByShowtime(show_id,time_id,location_id,showdate);
    }

  

    @PostMapping("/showtime/{show_id}/{time_id}/{location_id}/{showdate}")
    public Showtime newShowtime(Showtime newShowtime,
    @PathVariable long show_id,
    @PathVariable long time_id,
    @PathVariable long location_id,
    @PathVariable String showdate
    ) throws ParseException {
    
    Show show = showRepository.findById(show_id);
    Time time = timeRepository.findById(time_id);
    ShowLocation location = showLocationRepository.findById(location_id);
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date datetime = df.parse(showdate);
    
    
    newShowtime.setShow(show);
    newShowtime.setTime(time);
    newShowtime.setLocation(location);
    newShowtime.setShowDate(datetime);
    
    

    return showtimeRepository.save(newShowtime); 
    
    }
}
