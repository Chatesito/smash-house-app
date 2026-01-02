package com.house.smash.smash_house.service;

import com.house.smash.smash_house.model.Tournament;
import com.house.smash.smash_house.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public List<Tournament> getUpcomingTournaments() {
        return tournamentRepository.findUpcomingTournaments(LocalDateTime.now());
    }

    public List<Tournament> getPastTournaments() {
        return tournamentRepository.findPastTournaments(LocalDateTime.now());
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
    }

    @Transactional
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Transactional
    public Tournament updateTournament(Long id, Tournament tournament) {
        Tournament existingTournament = getTournamentById(id);
        existingTournament.setName(tournament.getName());
        existingTournament.setDescription(tournament.getDescription());
        existingTournament.setEventDate(tournament.getEventDate());
        existingTournament.setLocation(tournament.getLocation());
        return tournamentRepository.save(existingTournament);
    }

    @Transactional
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
