import { environment } from './environment';

export const endpoints = {
    p2k : environment.backend + "p2k",
    activeIncidents: environment.backend + "",
    newIncidents: environment.backend + "",
    twitterTags: environment.backend + "",
    facebookTags: environment.backend + "",
    instagramTags: environment.backend + "",
    availablePeople: environment.backend + "",
  //  login : environment.backend + "auth/login"
    searchTwitterTag: environment.backend + "twitter/search/",
    environment: environment.backend + "environment/"
}
