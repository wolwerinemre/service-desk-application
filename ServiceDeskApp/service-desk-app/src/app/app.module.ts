import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {AppComponent} from './app.component';
import {TicketComponent} from './component/ticket-list/ticket.component';
import {TicketService} from "./service/ticket.service";
import {FormsModule,ReactiveFormsModule} from "@angular/forms";
import {NotificationService} from "./service/notification.service";
import {TableModule} from 'primeng/table';
import {DialogModule} from "primeng/dialog";
import {ToastModule} from 'primeng/toast';
import {BlockUIModule} from "primeng/blockui";
import {ProgressBarModule} from 'primeng/progressbar';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {MessageService} from "primeng/api";
import {InputTextareaModule} from 'primeng/inputtextarea';
import {InputMaskModule} from 'primeng/inputmask';
import {SpinnerModule} from "primeng/spinner";
import {DropdownModule} from 'primeng/dropdown';
import {MessageModule} from "primeng/message";


@NgModule({
  declarations: [
    AppComponent,
    TicketComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    TableModule,
    DialogModule,
    ToastModule,
    BlockUIModule,
    ProgressBarModule,
    InputTextModule,
    ButtonModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    InputTextareaModule,
    InputMaskModule,
    SpinnerModule,
    DropdownModule,
    MessageModule,
    HttpClientModule
  ],
  providers: [
    TicketService,
    NotificationService,
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
