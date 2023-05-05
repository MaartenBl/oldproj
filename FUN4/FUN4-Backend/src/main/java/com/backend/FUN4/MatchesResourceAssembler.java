package com.backend.FUN4;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class MatchModelAssembler implements RepresentationModelAssembler<Match, EntityModel<Match>> {

  @Override
  public EntityModel<Match> toModel(Match match) {

    return new EntityModel<>(match,
      linkTo(methodOn(MatchController.class).one(match.getId())).withSelfRel(),
      linkTo(methodOn(MatchController.class).all()).withRel("matches"));
  }
}