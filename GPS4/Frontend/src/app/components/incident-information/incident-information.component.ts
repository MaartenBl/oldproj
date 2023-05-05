import { Component, OnInit } from '@angular/core';
import { EnvironmentData } from 'src/app/models/environmentData/environmentData.model';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { P2k } from 'src/app/models/p2k/p2k.model';
import { P2kService } from 'src/app/services/p2k/p2k.service';

@Component({
  selector: 'app-incident-information',
  templateUrl: './incident-information.component.html',
  styleUrls: ['./incident-information.component.css']
})

export class IncidentInformationComponent implements OnInit {
  environmentData: EnvironmentData;
  url: SafeResourceUrl;
  p2kData: P2k;
  p2kList: P2k[] = new Array();
  constructor(public sanitizer: DomSanitizer, private p2kService: P2kService) { }

  ngOnInit(): void {
    this.p2kService.data.subscribe(response => {this.p2kData = response });
    this.p2kService.data.subscribe(response => {this.p2kList.push(response as P2k), this.p2kService.updatedP2kListOnMapDataSource(this.p2kList), this.p2kData = response });
 
  }

  getSafeUrl(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url)
  }
}
