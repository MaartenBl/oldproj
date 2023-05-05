package com.backend.FUN4;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "Team")
class Team {
    @Id
    @GeneratedValue
    @Column(name ="team_id")
    private Long team_id;
    @Column(name ="name")
    private String name;
    @OneToMany(mappedBy="team")
    private Collection<Player> players;
    @Column(name ="totalwins")
    private Integer totalWins;
    @Column(name ="totallosses")
    private Integer totalLosses;
    @Column(name="totaldraws")
    private Integer totalDraws;
    @Column(name="goalsagainst")
    private Integer goalsAgainst;
    @Column(name="goalsfor")
    private Integer goalsFor;
    @OneToOne(mappedBy = "homeTeam")
    @JsonIgnoreProperties("homeTeam")
    private Match match;

    Team() {}

    Team(String name){
        this.name = name;
        this.players = new ArrayList<Player>();
        this.totalWins = 0;
        this.totalLosses = 0;
        this.totalDraws = 0;
        this.goalsAgainst = 0;
        this.goalsFor = 0;
    }

    public void addPlayer(Player p){
        if (this.players.contains(p)){
            System.out.println("Player already exsists in this team: " + this.name + ".");
        }
        else{
            this.players.add(p);
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setTotalwins(Integer totalwins){
        this.totalWins = totalwins;
    }
    public Integer getTotalwins(){
        return this.totalWins;
    }
    public void setTotallosses(Integer totalLosses){
        this.totalLosses = totalLosses;
    }
    public Integer getTotallosses(){
        return this.totalLosses;
    }
    public void setTotaldraws(Integer totaldraws){
        this.totalDraws = totaldraws;
    }
    public Integer getGoalsAgainst(){
        return this.goalsAgainst;
    }
    public void setGoalsAgainst(Integer goalsAgainst){
        this.goalsAgainst = goalsAgainst;
    }
    public Integer getGoalsFor(){
        return this.goalsFor;
    }
    public void setGoalsFor(){
        for (Player p : players) {
            this.goalsAgainst += p.getGoalsScored();
        }
    }
}