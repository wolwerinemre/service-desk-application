import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../domain/ticket";
import {catchError, map} from "rxjs/operators";
import {handleError} from "../util/error";
import {dateConverter} from "../util/converter";
import {decorateUrl} from "../util/url.util";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable()
export class TicketService {

  private serviceUrl: string;

  constructor(private readonly httpClient: HttpClient) {
    this.serviceUrl = decorateUrl(`/api/ticket`);
  }

  public findAll(): Observable<Ticket[]> {
    return this.httpClient.get<Ticket[]>(this.serviceUrl,httpOptions)
      .pipe(
        catchError(handleError),
        map(value => dateConverter(value))
      );
  }

  public save(ticket: Ticket) {
    return this.httpClient.post<Ticket>(this.serviceUrl,ticket,httpOptions)
      .pipe(
      catchError(handleError)
    );
  }

  public update(ticket: Ticket) {
    return this.httpClient.put<Ticket>(this.serviceUrl,ticket,httpOptions)
      .pipe(
      catchError(handleError)
    );
  }

  public delete(ticket: Ticket) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: ticket,
    };
    return this.httpClient.delete<any>(this.serviceUrl ,options)
      .pipe(
        catchError(handleError)
      );
  }
}
