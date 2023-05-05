package com.backend.FUN4;

class TeamNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    TeamNotFoundException(Long id) {
        super("Could not find Team with id: " + id);
    }
}