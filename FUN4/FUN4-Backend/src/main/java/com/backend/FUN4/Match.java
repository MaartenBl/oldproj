package com.backend.FUN4;

import lombok.Data;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "Match")
class Match {
    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;
    @Column(name = "location")
    private String location;
    @Column(name ="date")
    private String date;
    @Column(name ="time")
    private String time;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name ="homeTeam",referencedColumnName ="team_id")
    @JsonIgnoreProperties("team_id")
    private Team homeTeam;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "awayTeam",referencedColumnName ="team_id")
    @JsonIgnoreProperties("team_id")
    private Team awayTeam;
    @Column(name ="score")
    private ArrayList<Integer> score;

    Match() {}

    Match(String location, String date, String time, String homeTeam, String awayTeam){
        this.location = location;
        this.date = date;
        this.time = time;
        this.homeTeam = new Team(homeTeam);
        this.awayTeam = new Team(awayTeam);
        this.score = new ArrayList<Integer>();
        this.score.add(0);
        this.score.add(0);
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }

    /* 
    public void awayLoss(){
       int x = this.getAwayTeam().getTotalLosses();
       this.getAwayTeam().setTotalLosses(x+1);
    }

    public void awayWin(){
        int x = this.getAwayTeam().getTotalWins();
        this.getAwayTeam().setTotalLosses(x+1);
    }

    public void homeLoss(){
        int x = this.getHomeTeam().getTotalLosses();
        this.getHomeTeam().setTotalLosses(x+1);
    }

    public void homeWin(){
        int x = this.getHomeTeam().getTotalWins();
        this.getHomeTeam().setTotalWins(x+1);
    }

    public void drawGame(){
        int x = this.getHomeTeam().getTotalDraws();
        this.getHomeTeam().setTotalDraws(x+1);
        int y = this.getAwayTeam().getTotalDraws();
        this.getAwayTeam().setTotalDraws(y+1);
    }

    public void homeScores(int playernumber){
        for (Player p : this.getHomeTeam().getPlayers()) {
            if (p.getPlayerNumber() == playernumber){
                p.setGoalsScored(p.getGoalsScored()+1);
            }
        }
        this.getAwayTeam().setGoalsAgainst(this.getAwayTeam().getGoalsAgainst()+1);
        this.score[0] += 1;
    }
    public void awayScores(int playernumber){
        for (Player p : this.getAwayTeam().getPlayers()) {
            if (p.getPlayerNumber() == playernumber){
                p.setGoalsScored(p.getGoalsScored()+1);
            }
        }
        this.getHomeTeam().setGoalsAgainst(this.getHomeTeam().getGoalsAgainst()+1);
        this.score[1] += 1;
    }
    */

}