package com.backend.FUN4;

class PlayerNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    PlayerNotFoundException(Long id) {
        super("Could not find player with id: " + id);
    }
}