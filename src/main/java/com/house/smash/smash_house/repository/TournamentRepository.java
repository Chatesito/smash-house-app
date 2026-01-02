package com.house.smash.smash_house.repository;

import com.house.smash.smash_house.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {

    // Encontrar torneos futuros ordenados por fecha
    @Query("SELECT t FROM Tournament t WHERE t.eventDate >= ?1 ORDER BY t.eventDate ASC")
    List<Tournament> findUpcomingTournaments(LocalDateTime now);

    // Encontrar torneos pasados ordenados por fecha descendente
    @Query("SELECT t FROM Tournament t WHERE t.eventDate < ?1 ORDER BY t.eventDate DESC")
    List<Tournament> findPastTournaments(LocalDateTime now);
}
