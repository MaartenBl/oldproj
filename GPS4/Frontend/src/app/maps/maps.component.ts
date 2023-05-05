import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { P2kService } from 'src/app/services/p2k/p2k.service';
import { P2k } from 'src/app/models/p2k/p2k.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})

export class MapsComponent implements OnInit {
  map: any;
  marker1: any;
  p2klist: P2k[] = [];

  constructor(private p2kService: P2kService, private router : Router) {}

  ngOnInit(): void {
    this.p2kService.dataOnMap.subscribe(data => { this.p2klist = data as P2k[],this.addMap(), this.addP2KList(this.p2klist);} );
  }

  addCircle(p2kItem: P2k) {
    console.log("Circle created");
    const circle = L.circle([p2kItem.lat, p2kItem.lon], {
      color: 'red',
      fillColor: '#f03',
      fillOpacity: 0.5,
      radius: 1000
    }).addTo(this.map);
    circle.on('click', () => {
      this.p2kService.updatedselectedP2kDataSource(p2kItem);
      this.router.navigate(['/incident/information/' + p2kItem.guid ]);
    });
  }
  addP2KList(list: P2k[]) {
    console.log(list);
    for (let i = 0; i < list.length; i++) {
      if (list[i].lat != null && list[i].lon != null && list[i].title != null) {
        this.addCircle(list[i]);
      }
    }
  }

  addMap() {
    this.map = L.map('map').setView([52.092, 5.104], 7);
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
      maxZoom: 15,
      id: 'mapbox/streets-v11',
      tileSize: 512,
      zoomOffset: -1,
      accessToken: 'pk.eyJ1IjoiZ3BzNG1ha2tlcnMiLCJhIjoiY2s5MThsNWJnMDBwcjNncGlsMGpqcjVyNiJ9.9KytMgbZbyRZSKTTadv6fA'
    }).addTo(this.map);
  }
}
