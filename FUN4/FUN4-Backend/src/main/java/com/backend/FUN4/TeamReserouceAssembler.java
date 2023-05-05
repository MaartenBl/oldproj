package com.backend.FUN4;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class TeamModelAssembler implements RepresentationModelAssembler<Team, EntityModel<Team>> {

  @Override
  public EntityModel<Team> toModel(Team team) {

    return new EntityModel<>(team,
      linkTo(methodOn(TeamController.class).one(team.getTeam_id())).withSelfRel(),
      linkTo(methodOn(TeamController.class).all()).withRel("Team"));
  }
}