import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SynthDocument} from "../model/synth-document";

@Injectable({
  providedIn: 'root',
})

export class DocumentService {
  private BASE_URL = 'rest';
  constructor(private _http: HttpClient) {
  }

  getAllDocuments(): Observable<SynthDocument[]> {
    return this._http.get<SynthDocument[]>(`${this.BASE_URL}/documents`);
  }

  addMockDocuments(count: number): Observable<number> {
    return this._http.get<number>(`${this.BASE_URL}/document/generate?count=${count}`);
  }

  deleteDocument(id: number): Observable<number> {
    return this._http.post<number>(`${this.BASE_URL}/document/delete`, id);
  }
}
