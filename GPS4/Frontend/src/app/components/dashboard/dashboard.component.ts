import { Component, OnInit } from '@angular/core';
import { P2kService } from 'src/app/services/p2k/p2k.service';
import { P2k } from 'src/app/models/p2k/p2k.model';
import { HttpsService } from '../../../app/http/https.service';
import { endpoints } from '../../../environments/endpoints';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  p2klist: P2k[] = [];
  todayDate: Date = new Date();
  count = 0;

  constructor(private p2kService: P2kService, private httpService: HttpsService) {

  }

  ngOnInit(): void {
    this.p2kService.getP2k().subscribe(data => this.p2klist = data as P2k[]);
  }

  public NumberOfp2kAlertsLastHour() {
    this.p2klist.forEach(obj => { });
  }

  public GetAmbulanceAlerts() {
    return this.httpService.get(endpoints.p2k);
  }

  public GetActiveIncidents() {
    return this.httpService.get(endpoints.activeIncidents);
  }

  public GetNewIncidents() {
    return this.httpService.get(endpoints.newIncidents);
  }

  public GetTwitterTags() {
    return this.httpService.get(endpoints.twitterTags);
  }

  public GetFacebookTags() {
    return this.httpService.get(endpoints.facebookTags);
  }

  public GetInstagramTags() {
    return this.httpService.get(endpoints.instagramTags);
  }

  public GetAvailablePeople() {
    return this.httpService.get(endpoints.availablePeople);
  }
}
