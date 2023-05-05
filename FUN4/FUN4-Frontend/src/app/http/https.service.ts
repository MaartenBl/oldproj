import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const headers = new HttpHeaders()
  .set('Content-Type', 'application/json')
  .set('Access-Control-Allow-Origin', '*');

@Injectable({
  providedIn: 'root'
})
export class HttpsService {
    constructor(private http: HttpClient) { }

    public post(url: string, content: object): Observable<any> {
        return this.http.post(url, content, { headers });
    }
    public get(url: string): Observable<any> {
        return this.http.get(url, { headers });
    }
    public delete(url: string) {
        this.http.delete(url);
    }
    public put(url: string, content: object): Observable<any> {
        return this.http.put(url, content);
    }
}
