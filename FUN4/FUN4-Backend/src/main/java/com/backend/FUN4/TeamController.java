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
class TeamController {

  private final TeamRepository repository;
  private final TeamModelAssembler assembler;

  TeamController(TeamRepository repository, TeamModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root

  @GetMapping("/teams")
  CollectionModel<EntityModel<Team>> all() {
  
    List<EntityModel<Team>> employees = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());
  
    return new CollectionModel<>(employees,
      linkTo(methodOn(TeamController.class).all()).withSelfRel());
  }

  @PostMapping("/teams")
  ResponseEntity<?> newTeam(@RequestBody Team newTeam) throws URISyntaxException {

    EntityModel<Team> entityModel = assembler.toModel(repository.save(newTeam));
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  // Single item
  @GetMapping("/teams/{id}")
  EntityModel<Team> one(@PathVariable Long id) {
  
    Team team = repository.findById(id)
      .orElseThrow(() -> new TeamNotFoundException(id));
  
    return assembler.toModel(team);
  }

  @PutMapping("/teams/{id}")
  ResponseEntity<?> replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) throws URISyntaxException {

    Team updatedTeam = repository.findById(id)
      .map(team -> {
        team.setName(newTeam.getName());
        return repository.save(team);
      })
      .orElseGet(() -> {
        newTeam.setTeam_id(id);
        return repository.save(newTeam);
      });
  
    EntityModel<Team> entityModel = assembler.toModel(updatedTeam);
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  @DeleteMapping("/teams/{id}")
  ResponseEntity<?> deleteTeam(@PathVariable Long id) {

    repository.deleteById(id);
  
    return ResponseEntity.noContent().build();
  }
}