import { Injectable } from '@angular/core';
import { HttpsService } from '../../../app/http/https.service';
import { endpoints } from '../../../environments/endpoints';
import { P2k } from 'src/app/models/p2k/p2k.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class P2kService {

  constructor(private httpService: HttpsService) { }

  private selectedP2kDataSource = new BehaviorSubject<P2k>(new P2k());
  private P2kListOnMapDataSource = new BehaviorSubject<P2k[]>(new Array<P2k>());
  data = this.selectedP2kDataSource.asObservable();
  dataOnMap = this.P2kListOnMapDataSource.asObservable();

  public getP2k() {
    return this.httpService.get(endpoints.p2k);
  }

  updatedselectedP2kDataSource(data : P2k){
    this.selectedP2kDataSource.next(data);
  }
  updatedP2kListOnMapDataSource(data : P2k[]){
    console.log(data);
    this.P2kListOnMapDataSource.next(data);
  }
}
