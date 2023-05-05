import { Component, OnInit, ContentChildren } from '@angular/core';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { EnvironmentData } from 'src/app/models/environmentData/environmentData.model';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { P2kService } from 'src/app/services/p2k/p2k.service';
import { P2k } from 'src/app/models/p2k/p2k.model';

@Component({
  selector: 'app-weather-measurement-information',
  templateUrl: './weather-measurement-information.component.html',
  styleUrls: ['./weather-measurement-information.component.css']
})
export class WeatherMeasurementInformationComponent implements OnInit {

  environmentData: EnvironmentData;
  url: SafeResourceUrl;
  p2kData : P2k;
  constructor(private environmentService: EnvironmentService, public sanitizer: DomSanitizer, private p2kService : P2kService) { }

  ngOnInit(): void {
    this.p2kService.data.subscribe(response => {this.p2kData = response, console.log(response)});
    this.environmentService.getEnvironmentData(this.p2kData.lat, this.p2kData.lon).subscribe(response => { this.environmentData = response, this.url = this.getSafeUrl(response.weather.radarUrl), console.log(response); });
   }

  getSafeUrl(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url)
  }
}
