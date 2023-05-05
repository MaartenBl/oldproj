import { Injectable } from '@angular/core';
import { HttpsService } from '../../http/https.service';
import { endpoints } from '../../../environments/endpoints';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private httpService: HttpsService) { }

  public getMatches() {
    return this.httpService.get(endpoints.matches);
  }
}
