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
class PlayerController {

  private final PlayerRepository repository;
  private final PlayerModelAssembler assembler;

  PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root

  @GetMapping("/players")
  CollectionModel<EntityModel<Player>> all() {
  
    List<EntityModel<Player>> employees = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());
  
    return new CollectionModel<>(employees,
      linkTo(methodOn(PlayerController.class).all()).withSelfRel());
  }

  @PostMapping("/players")
  ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) throws URISyntaxException {

    EntityModel<Player> entityModel = assembler.toModel(repository.save(newPlayer));
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  // Single item
  @GetMapping("/players/{id}")
  EntityModel<Player> one(@PathVariable Long id) {
  
    Player player = repository.findById(id)
      .orElseThrow(() -> new PlayerNotFoundException(id));
  
    return assembler.toModel(player);
  }

  @PutMapping("/players/{id}")
  ResponseEntity<?> replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) throws URISyntaxException {

    Player updatedPlayer = repository.findById(id)
      .map(player -> {
        player.setName(newPlayer.getName());
        return repository.save(player);
      })
      .orElseGet(() -> {
        newPlayer.setPlayer_id(id);
        return repository.save(newPlayer);
      });
  
    EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);
  
    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  @DeleteMapping("/players/{id}")
  ResponseEntity<?> deletePlayer(@PathVariable Long id) {

    repository.deleteById(id);
  
    return ResponseEntity.noContent().build();
  }
}