import { Component, OnInit } from '@angular/core';
import { TwitterService } from '../../services/twitter/twitter.service';
import { Twitter } from 'src/app/models/twitter/twitter.model';
import { TwitterResponse } from 'src/app/models/twitterResponse/twitter-response.model';

import { HttpsService } from 'src/app/http/https.service';
import { endpoints } from '../../../environments/endpoints';

@Component({
  selector: 'app-social-media-posts',
  templateUrl: './social-media-posts.component.html',
  styleUrls: ['./social-media-posts.component.css']
})
export class SocialMediaPostsComponent implements OnInit {

  tagInput: string;
  twitterList: TwitterResponse;

  constructor(private TwitterService: TwitterService, private httpsService: HttpsService) { }

  ngOnInit(): void {
  }

  getByTag() {
    this.TwitterService.getByTag(this.tagInput).subscribe(data => {this.twitterList = data as TwitterResponse, console.log(this.twitterList)});
  }
}
