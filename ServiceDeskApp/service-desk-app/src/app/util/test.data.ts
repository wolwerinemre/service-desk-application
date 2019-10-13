import {Ticket} from "../domain/ticket";
import {ProcessStatus} from "../domain/proccess.status";

export const ticketsForTest : Ticket[] =
  [
    {
      ticketNumber:"6d317919-d405-4272-bf4e-24f7cb35cb71",
      name:"Test App",
      email:"e@e.com",
      description:"Description for test",
      status: ProcessStatus.OPEN,
      priority:1,
      createDate: new Date(),
      updateDate: null
    },
    {
      ticketNumber:"d3c67178-c479-41fb-80f7-b976e9b4c38b",
      name:"Service-Desk-App",
      email:"e@e.com",
      description:"Test",
      status: ProcessStatus.PROCESS,
      priority:1,
      createDate:new Date(),
      updateDate:new Date()
    }
  ]
