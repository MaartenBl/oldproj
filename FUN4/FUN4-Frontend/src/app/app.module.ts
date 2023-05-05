import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { GamereportComponent } from './gamereport/gamereport.component';
import { NewsComponent } from './news/news.component';
import { CompetitionrulesComponent } from './competitionrules/competitionrules.component';

const appRoutes: Route[] = [
  { path: 'dashboard', component: DashboardComponent, data: { showRootComponents: false }},
];

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    StatisticsComponent,
    GamereportComponent,
    NewsComponent,
    CompetitionrulesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
