import { Injectable } from '@angular/core';
import { HttpsService } from '../../../app/http/https.service';
import { endpoints } from '../../../environments/endpoints';
import { Twitter } from '../../models/twitter/twitter.model';

@Injectable({
  providedIn: 'root'
})

export class TwitterService {

  constructor(private httpService: HttpsService) { }

  public getByTag(tag: string) {   
    return this.httpService.get(endpoints.searchTwitterTag + tag);
  }
}
