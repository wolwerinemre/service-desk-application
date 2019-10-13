import {ErrorHandler, Inject, Injectable} from '@angular/core';
import {NotificationService} from "./notification.service";
import {handleError} from "../util/error";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(@Inject(NotificationService) private readonly notificationService: NotificationService) {}

  handleError(error: any) {
    this.notificationService.error(error);
    throw handleError(error);
  }
}
