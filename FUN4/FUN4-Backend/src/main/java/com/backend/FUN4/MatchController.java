package com.backend.FUN4;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MatchController {

  private final MatchRepository repository;
  private final MatchModelAssembler assembler;

  MatchController(MatchRepository repository, MatchModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root

  @GetMapping("/matches")
  CollectionModel<EntityModel<Match>> all() {
  
    List<EntityModel<Match>> employees = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());
  
    return new CollectionModel<>(employees,
      linkTo(methodOn(MatchController.class).all()).withSelfRel());
  }

  @PostMapping("/matches")
  ResponseEntity<?> newMatch(@RequestBody Match newMatch) throws URISyntaxException {

    EntityModel<Match> entityModel = assembler.toModel(repository.save(newMatch));
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  // Single item
  @GetMapping("/matches/{id}")
  EntityModel<Match> one(@PathVariable Long id) {
  
    Match match = repository.findById(id)
      .orElseThrow(() -> new MatchNotFoundException(id));
  
    return assembler.toModel(match);
  }

  @PutMapping("/matches/{id}")
  ResponseEntity<?> replaceMatch(@RequestBody Match newMatch, @PathVariable Long id) throws URISyntaxException {

    Match updatedMatch = repository.findById(id)
      .map(match -> {
        match.setTime(newMatch.getTime());
        match.setLocation(newMatch.getLocation());
        return repository.save(match);
      })
      .orElseGet(() -> {
        newMatch.setId(id);
        return repository.save(newMatch);
      });
  
    EntityModel<Match> entityModel = assembler.toModel(updatedMatch);
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  @DeleteMapping("/matches/{id}")
  ResponseEntity<?> deleteMatch(@PathVariable Long id) {

    repository.deleteById(id);
  
    return ResponseEntity.noContent().build();
  }
}