package com.okta.springbootvue.repository;

import java.util.Collection;
import java.util.Optional;

import com.okta.springbootvue.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Showtime findById(long id);

    @Query( value = "SELECT *  FROM SHOWTIME s where s.show_id = :sub",
            nativeQuery = true)
    Collection<Showtime> findShowtimeByShow(@Param("sub") Long sub);


    @Query( value = "SELECT *  FROM SHOWTIME s where s.showtime_id = :sub",
            nativeQuery = true)
    Collection<Showtime> findShowtime(@Param("sub") Long sub);



    @Query( value = "SELECT * FROM SHOWTIME s where s.show_date  = :sub",
            nativeQuery = true)
    Collection<Showtime> findDatetime(@Param("sub") java.util.Date sub);

    

    
    @Query( value = "SELECT * FROM Showtime z WHERE z.SHOW_ID = :show_id and z.TIME_ID = :time_id and z.SHOWLOCATION_ID = :location_id and SHOW_DATE =:showdate",
    
    nativeQuery = true)
    
Collection<Showtime> findShowtimeByShowtime(@Param("show_id") Long show_id,
                                            @Param("time_id") Long time_id,
                                            @Param("location_id") Long location_id,
                                            @Param("showdate") java.util.Date showdate);

}