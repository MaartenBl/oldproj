import { Injectable } from '@angular/core';
import { HttpsService } from '../../http/https.service';
import { endpoints } from '../../../environments/endpoints';
import { Observable } from 'rxjs';
import { EnvironmentData } from '../../models/environmentData/environmentData.model';


@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  constructor(private httpService: HttpsService) { }

  public getEnvironmentData(longitude : number, latitude : number) : Observable<EnvironmentData>{
    return this.httpService.get(endpoints.environment + longitude + "/" + latitude);
  }
}