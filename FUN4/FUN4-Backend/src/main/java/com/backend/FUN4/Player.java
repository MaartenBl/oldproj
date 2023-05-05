package com.backend.FUN4;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Player")
class Player {
    @Id
    @GeneratedValue
    @Column(name="player_id", insertable=false, updatable=false)
    private Long player_id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="playernumber")
    private Integer playerNumber;
    @Column(name="goalsscored")
    private Integer goalsScored;
    @Column(name="assists")
    private Integer assists;
    @Column(name="penaltyminutes")
    private Integer penaltyMinutes;
    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    Player() {}

    Player(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerNumber = 0;
        this.goalsScored = 0;
        this.assists = 0;
        this.penaltyMinutes = 0;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    
    public void setName(String name) {
        String[] parts =name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[parts.length-1];
    }
    public void setPlayerNumber(Integer i){
        this.playerNumber = i;
    }
    public Integer getPlayerNumber(){
        return this.playerNumber;
    }
    public void setGoalsScored(Integer i){
        this.goalsScored += i;
    }
    public Integer getGoalsScored(){
        return this.goalsScored;
    }
    public void setAssists(Integer i){
        this.assists += i;
    }
    public Integer getAssists(){
        return this.assists;
    }
    public void setPenaltyMinutes(Integer i){
        this.penaltyMinutes += i;
    }
    public Integer getPenaltyMinutes(){
        return this.penaltyMinutes;
    }

}

/*
    Add penalties later
*/