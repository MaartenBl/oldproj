import { Component, OnInit } from '@angular/core';
import { WebsocketService } from 'src/app/services/websocket/websocket.service';
import { Subject } from 'rxjs';
import { Message } from 'src/app/models/websocket/message';
import { P2kService } from 'src/app/services/p2k/p2k.service';
import { P2k } from 'src/app/models/p2k/p2k.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-incident-list',
  templateUrl: './incident-list.component.html',
  styleUrls: ['./incident-list.component.css']
})
export class IncidentListComponent implements OnInit {
  public url = "ws://127.0.0.1:5000/ws";
  public messages: Subject<Message>;
  public p2kData: Array<P2k> = [];

  constructor(webSucket: WebsocketService, private p2kService: P2kService, private router: Router) {
    p2kService.getP2k().subscribe((data: Array<P2k>) => {
      this.p2kData = data;
      console.log(this.p2kData)
    })
    webSucket.connect(this.url).subscribe(
      (response: MessageEvent) => {
        var data: Message = Object.assign(new Message, JSON.parse(response.data))
        if (data.type == "P2K Alert") {
          this.p2kData.unshift(Object.assign(new P2k, data.payload))
        }
      }
    );
  }

  onGoToSpeficIncidentPage(p2kItem : P2k)
  {
    this.p2kService.updatedselectedP2kDataSource(p2kItem);
    this.router.navigate(['/incident/information/' + p2kItem.guid ]);
  }

  ngOnInit(): void {
  }

}
