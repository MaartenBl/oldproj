package com.backend.FUN4;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class PlayerModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {

  @Override
  public EntityModel<Player> toModel(Player player) {

    return new EntityModel<>(player,
      linkTo(methodOn(PlayerController.class).one(player.getPlayer_id())).withSelfRel(),
      linkTo(methodOn(PlayerController.class).all()).withRel("Player"));
  }
}