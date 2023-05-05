import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Route, RouterModule } from '@angular/router';
import { SidebarModule } from 'ng-sidebar';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { IncidentInformationComponent } from './components/incident-information/incident-information.component';
import { IncidentCreateComponent } from './components/incident-create/incident-create.component';
import { IncidentListComponent } from './components/incident-list/incident-list.component';
import { SocialMediaPostsComponent } from './components/social-media-posts/social-media-posts.component';
import { MapsComponent } from './maps/maps.component';
import { WeatherMeasurementInformationComponent } from './components/weather-measurement-information/weather-measurement-information/weather-measurement-information.component';
import { IncidentsMapComponent } from './components/incidents-map/incidents-map.component';

const appRoutes: Route[] = [
  { path: 'login', component: LoginComponent, data: { showRootComponents: false } },
  { path: 'dashboard', component: DashboardComponent, data: { showRootComponents: false } },
  { path: 'incident/information', component: IncidentInformationComponent, data: { showRootComponents: false } },
  { path: 'incident/information/:id', component: IncidentInformationComponent, data: { showRootComponents: false } },
  { path: 'incident/create', component: IncidentCreateComponent, data: { showRootComponents: false } },
  { path: '', component: IncidentListComponent, data: { showRootComponents: false } },
  { path: 'socialmedia/posts', component: SocialMediaPostsComponent, data: { showRootComponents: false } },
  { path: 'incidentsmap', component: IncidentsMapComponent, data: { showRootComponents: false } },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    IncidentInformationComponent,
    IncidentCreateComponent,
    IncidentListComponent,
    SocialMediaPostsComponent,
    MapsComponent,
    WeatherMeasurementInformationComponent,
    IncidentsMapComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes
    ),
    SidebarModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
