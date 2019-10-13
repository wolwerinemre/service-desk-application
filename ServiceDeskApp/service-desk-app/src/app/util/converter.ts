import {isArray} from "util";
import {toDateTimeStr} from "./date.str";

export function dateConverter(list:any[]): any[] {
  list.forEach((value) => {
    if (value) {
      Object.keys(value).forEach(valueKey => {
        if (typeof value[valueKey] === 'number' && valueKey.toUpperCase().includes('date'.toUpperCase())) {
            value[valueKey] = toDateTimeStr(value[valueKey]);
        } else if(isArray(value[valueKey])) {
          dateConverter(value[valueKey]);
        }
      })
    }
  });
  return list;
}
