import {DateStr} from "./date.str.brand";
import * as moment from "moment";
import {dateFormat, dateTimeFormat} from "./constants";

type DateType = Date | number | moment.Moment | string;

export function checkValidDateStr(str: string): str is DateStr {
  return str.match(/^\d{2}-\d{2}-\d{4} \d{2}:\d{2}:\d{2}$/) !== null || str.match(/^\d{2}-\d{2}-\d{4}$/) !== null;
}

export function toDateStr(date: DateType): DateStr {
  return toStr(date, dateFormat);
}

export function toDateTimeStr(date: DateType): DateStr {
  return toStr(date, dateTimeFormat);
}

function toStr(date: DateType, dateFormat: string) {
  if (typeof date === 'string') {
    if (checkValidDateStr(date)) {
      return date;
    } else {
      throw new Error(`Invalid date string: ${date}`);
    }
  } else {
    const dateString = moment(date).format(dateFormat);
    if (checkValidDateStr(dateString)) {
      return dateString;
    }
    else {
      return null;
    }
  }
}
