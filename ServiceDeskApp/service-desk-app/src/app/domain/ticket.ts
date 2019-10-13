import {ProcessStatus} from "./proccess.status";

export class Ticket {
  ticketNumber : string;
  status : ProcessStatus;
  name : string;
  email : string;
  priority : number;
  description : string;
  createDate : Date;
  updateDate : Date;
}
