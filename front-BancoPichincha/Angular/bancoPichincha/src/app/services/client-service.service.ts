import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientServiceService {

  private apiUrl = 'http://localhost:8080/api'
  constructor(private http: HttpClient) { }

  getClients(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/person`)
  }

}
