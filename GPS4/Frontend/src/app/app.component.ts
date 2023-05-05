import { Component, OnInit, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  title = 'GPS4-Frontend';
  public sidebarDisabled = true;
  public showSidebar = false;
  constructor() { }

  ngOnInit(): void {
  }

  public toggleSidebar() {
    this.showSidebar = !this.showSidebar;

    const menuBtn = document.getElementById('menu-btn');
    if (this.showSidebar) {
      const text = "<div><i class=\"fas fa-times\"></i> Menu</div>";
      menuBtn.innerHTML = text;
    }

    else {
      const text = "<div><i class=\"fas fa-bars\"></i> Menu</div>";
      menuBtn.innerHTML = text;
    } 
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.sidebarDisabled = false;
    });
  }
}
