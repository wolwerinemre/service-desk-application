import {inject, TestBed} from '@angular/core/testing';

import {TicketService} from './ticket.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Ticket} from "../domain/ticket";
import {ticketsForTest} from "../util/test.data";
import {HttpClient} from "@angular/common/http";

describe('TicketService', () => {

  let service : TicketService;
  let mockData : Ticket;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TicketService],
      imports: [HttpClientTestingModule]
    })
    service = TestBed.get(TicketService);
    mockData = ticketsForTest[1];
  });

  it('should be created', inject([TicketService,HttpClient],
    (service:TicketService,http:HttpClient) => {
    expect(service).toBeTruthy();
  }));

  it('#findAll should be created', async() => {
    service.findAll().subscribe(value => {
      expect(value).toBeTruthy();
    })
  });

  it('#save should be created', async() => {
    service.save(mockData).subscribe(value => {
      expect(value).toBeTruthy();
    })
  });

  it('#update should be created', async() => {
    service.update(mockData).subscribe(value => {
      expect(value).toBeTruthy();
    })
  });

  it('#delete should be created', async() => {
    service.delete(mockData).subscribe(value => {
      expect(value).toBeTruthy();
    })
  });
});
