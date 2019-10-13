import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TicketComponent} from './ticket.component';
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {NotificationService} from "../../service/notification.service";
import {MessageService} from "primeng/api";
import {FormBuilder} from "@angular/forms";
import {TicketService} from "../../service/ticket.service";
import {HttpClientModule} from "@angular/common/http";
import {of} from "rxjs";
import {Ticket} from "../../domain/ticket";
import {ticketsForTest} from "../../util/test.data";
import any = jasmine.any;

describe('TicketComponent', () => {
  let component: TicketComponent;
  let fixture: ComponentFixture<TicketComponent>;
  let ticketService: TicketService;
  let notificationService: NotificationService;
  let mockData: Ticket;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketComponent ],
      schemas:[
        NO_ERRORS_SCHEMA
      ],
      providers: [
        NotificationService,
        MessageService,
        FormBuilder,
        TicketService
      ],
      imports: [
        HttpClientModule
      ]
    })
    .compileComponents();
  })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketComponent);
    component = fixture.componentInstance;
    ticketService = fixture.debugElement.injector.get(TicketService);
    notificationService = fixture.debugElement.injector.get(NotificationService);
    mockData = ticketsForTest[0];
    spyOn(ticketService,'findAll').and.returnValue(of(ticketsForTest));
    spyOn(ticketService,'save').withArgs(any).and.returnValue(of(mockData));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('#search should search component', async () => {
    component.ngOnInit();
    component.clearAndSearch();
    expect(component.tickets).toBeTruthy();
  });

  it('#save should search component', async () => {
    component.ticket = mockData;
    component.ticket.ticketNumber=null;
    component.save();
    expect(component.tickets[0]).toEqual(mockData);
  });

  it('#delete should search component', async () => {
    component.ticket = mockData;
    component.delete();
    expect(component.ticket).toEqual(new Ticket());
  });

  it('#onRowSelect should select ticket', async () => {
    component.onRowSelect(mockData);
    expect(component.displayDialog).toBeTruthy()
  });

  it('#cloneTicket should clone ticket', async () => {
    let ticket = new Ticket();
    ticket.email=mockData.email;
    ticket.ticketNumber=mockData.ticketNumber;
    expect(component.cloneTicket(ticket).email).toEqual(mockData.email);
  });

  it('#showDialogToAdd should clone ticket', async () => {
    component.showDialogToAdd()
    expect(component.displayDialog).toBeTruthy()
    expect(component.newTicket).toBeTruthy()
  });
});
