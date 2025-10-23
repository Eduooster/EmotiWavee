package org.example.emotiwave.infra.repository;

import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseMusicaRepository extends JpaRepository<AnaliseMusica, Long> {
}
