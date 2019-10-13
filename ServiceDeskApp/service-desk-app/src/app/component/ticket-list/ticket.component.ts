import {Component, OnInit} from '@angular/core';
import {Ticket} from "../../domain/ticket";
import {TicketService} from "../../service/ticket.service";
import {NotificationService} from "../../service/notification.service";
import {FormBuilder, FormControl, FormGroup, Validator, Validators} from "@angular/forms";

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css'],
})
export class TicketComponent implements OnInit {

  tickets: Ticket[];
  columns: any[];
  loading: boolean;
  totalRecords = 0;
  displayDialog: boolean =false;
  newTicket: boolean;
  ticket: Ticket = new Ticket();
  statusses : any[];
  userForm : FormGroup;

  constructor(private ticketService: TicketService,private readonly notificationService: NotificationService,
              private readonly formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.columnsDefine();
    this.search();
    this.statusses = [
      {label:'OPEN', value: 'OPEN'},
      {label:'PROCESS', value: 'PROCESS'},
      {label:'CLOSE', value: 'CLOSE'}
    ];
    this.validations();
  }

  validations() {
    this.userForm = this.formBuilder.group(
      {
        'email' : new FormControl('',Validators.compose([Validators.email,Validators.required])),
        'name' : new FormControl('',Validators.required),
        'priority' : new FormControl('',Validators.required)
      }
    );
  }

  columnsDefine () {
    this.columns = [
      {field: 'ticketNumber', header: 'Ticket Number'},
      {field: 'name', header: 'Name'},
      {field: 'email', header: 'E-mail'},
      {field: 'priority', header: 'Priority'},
      {field: 'description', header: 'Description'},
      {field: 'status', header: 'Status'},
      {field: 'createDate', header: 'Create Date'},
      {field: 'updateDate', header: 'Update Date'}
    ];
  }

  search() {
    this.loading = true;
    this.tickets = [];
    this.ticketService.findAll()
      .subscribe(
      data => {
        if (data && data.length > 0) {
          this.tickets.push(...data);
        }
        this.totalRecords = data.length;
        this.loading = this.notificationService.loading;
        this.notificationService.info(
          `Total: ${this.totalRecords} record(s)`);
      },
      error => {
        this.totalRecords = 0;
        this.loading = this.notificationService.loading;
        this.notificationService.error(
          `Retrieve Failed: ${error.toString()}`,"Failed");
      }
    );
  }

  clearAndSearch(event?) {
    this.notificationService.clear();
    this.totalRecords = 0;
    this.columnsDefine();
    this.search();
  }

  showDialogToAdd() {
    this.newTicket = true;
    this.ticket = new Ticket();
    this.displayDialog = true;
    this.validations();
  }

  save() {
    this.ticket.updateDate = null;
    this.ticket.createDate = null;
    if (this.newTicket) {
      this.ticketService.save(this.ticket).subscribe(data => {
          if (data) {
            this.notificationService.success("Saved Completed","Saved")
          }
          this.clearAndSearch();
        },
        error => {
          this.notificationService.error("Saved Failed " + error.toString(),"Failed")
        }
      );
    }
    else {
      this.ticketService.update(this.ticket).subscribe(data => {
          if(data){
            this.notificationService.success("Update Completed","Update")
          }
          this.clearAndSearch();
        },
        error =>{
          this.notificationService.error("Update Failed " + error.toString(),"Failed")
        }
      );
    }

    this.ticket = new Ticket();
    this.displayDialog = false;
  }

  delete() {
    this.ticket.updateDate = null;
    this.ticket.createDate = null;
    this.ticketService.delete(this.ticket).subscribe(data => {
        if(data){
          this.notificationService.success("Delete Completed","Delete")
        }
        this.clearAndSearch();
      },
      error =>{
        this.notificationService.error("Delete Failed" + error.toLocaleString(),"Failed")
      }
    );
    this.ticket = new Ticket();
    this.displayDialog = false;
  }

  onRowSelect(ticket : Ticket) {
    this.newTicket = false;
    this.ticket = this.cloneTicket(ticket);
    this.displayDialog = true;
  }

  cloneTicket(t: Ticket): Ticket {
    let ticket = new Ticket();
    for (let prop in t) {
      ticket[prop] = t[prop];
    }
    return ticket;
  }
}
