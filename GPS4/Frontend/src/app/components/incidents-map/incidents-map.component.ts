import { Component, OnInit } from '@angular/core';
import { P2kService } from 'src/app/services/p2k/p2k.service';
import { P2k } from 'src/app/models/p2k/p2k.model';

@Component({
  selector: 'app-incidents-map',
  templateUrl: './incidents-map.component.html',
  styleUrls: ['./incidents-map.component.css']
})
export class IncidentsMapComponent implements OnInit {
  p2klist: P2k[];
  constructor(private p2kService: P2kService) { }

  ngOnInit(): void {
    this.p2kService.getP2k().subscribe(data => { this.p2kService.updatedP2kListOnMapDataSource(data as P2k[]), this.p2klist = data});
  }
}
