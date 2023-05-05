package com.backend.FUN4;

import org.springframework.data.jpa.repository.JpaRepository;

interface PlayerRepository extends JpaRepository<Player, Long> {
    
}