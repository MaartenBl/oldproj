import { Component, OnInit } from '@angular/core';
import { MatchService } from '../services/matches/matches.service';
import { Matches } from '../models/matches/matches.model';
import { Response } from '../models/response/response.model';
import { HttpsService } from '../http/https.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  listofMatches: Matches[] = [];
  match: Matches;
  response: Response;
  error: any;
  constructor(private matchService: MatchService, private httpService: HttpsService) { }

  ngOnInit(): void {
    this.matchService.getMatches()
      .subscribe(
        data => { this.response = data, this.listofMatches = this.response._embedded.matchList; },
        // console.log(JSON.stringify(this.response._embedded.matchList));
        error => console.log(error)
    );
  }

}
