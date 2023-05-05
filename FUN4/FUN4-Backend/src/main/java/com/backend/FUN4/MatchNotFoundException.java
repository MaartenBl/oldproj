package com.backend.FUN4;

class MatchNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    MatchNotFoundException(Long id) {
        super("Could not find match with id: " + id);
    }
}