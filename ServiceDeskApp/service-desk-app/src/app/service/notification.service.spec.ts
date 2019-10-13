import {inject, TestBed} from '@angular/core/testing';
import {NotificationService} from "./notification.service";
import {MessageService} from "primeng/api";

describe('NotificationService', () => {
  let service : NotificationService;
  let test : string ;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NotificationService, MessageService]
    });
    service = TestBed.get(NotificationService);
    test = "test";
    service.clear();
  });

  it('#error should test error',() => {
    service.loading = true;
    service.error(test)
    expect(service.loading).toBeFalsy();
  });

  it('#info should test info',() => {
    service.loading = true;
    service.info(test)
    expect(service.loading).toBeFalsy();
  });

  it('#warning should test warning',() => {
    service.loading = true;
    service.warning(test)
    expect(service.loading).toBeFalsy();
  });

  it('#success should test success',() => {
    service.loading = true;
    service.success(test)
    expect(service.loading).toBeFalsy();
  });

  it('should have a created services', inject([NotificationService, MessageService],
    (service: NotificationService, messageService : MessageService) => {
    expect(service).toBeTruthy();
    expect(messageService).toBeTruthy();
  }));
});
