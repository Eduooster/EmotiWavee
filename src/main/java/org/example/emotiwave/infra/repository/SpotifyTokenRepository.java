package org.example.emotiwave.infra.repository;

import org.example.emotiwave.domain.entities.SpotifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyTokenRepository extends JpaRepository<SpotifyToken, Long> {
}
