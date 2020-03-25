import { Bovino } from '../model/bovino';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BovinoService {

  private endpoint = 'bovinos/';

  constructor(private httpClient: HttpClient) { }

  criar(Bovino: Bovino): Observable<Bovino> {
      return this.httpClient.post<Bovino>(`${environment.server}${this.endpoint}`, Bovino);
  }

  listar(): Observable<Bovino[]> {
    return this.httpClient.get<Bovino[]>(`${environment.server}${this.endpoint}`);
  }

  buscarNome(nome: string): Observable<Bovino[]> {
    return this.httpClient.get<Bovino[]>(`${environment.server}${this.endpoint}nome/${nome}`);
  }

  buscarBrinco(brinco: string): Observable<Bovino[]> {
    return this.httpClient.get<Bovino[]>(`${environment.server}${this.endpoint}brinco/${brinco}`);
  }

  atualizar(codigo: number, Bovino: Bovino): Observable<Bovino> {
    return this.httpClient.put<Bovino>(`${environment.server}${this.endpoint}${codigo}`, Bovino);
  }

  remover(codigo: number): Observable<Bovino> {
    return this.httpClient.delete<Bovino>(`${environment.server}${this.endpoint}${codigo}`);
  }

}
