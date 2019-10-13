import {AbstractControl, ValidatorFn} from "@angular/forms";

export function  multiSelectRequired(): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} => {
    const selections = control.value;
    const isValid = selections && selections.length > 0;
    const msg = 'selection required';

    return isValid ?  null : {'multiSelectRequired': {msg}};
  };
}
/**
 * Validator that requires controls to have a value greater than
 */
export function min(min: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    if(control.value==="") {
      return null;
    }
    const selection = control.value;
    const isValid = selection > min;
    const msg = {'min': min, 'actual': selection};
    return isValid ?  null : {'min': {msg}};
  };
}
/**
 * Validator that requires controls to have a value less than
 */
export function max(max: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    if(control.value==="" || max===0) {
      return null;
    }
    const selection = control.value;
    const isValid = selection < max;
    const msg = {'max': max, 'actual': selection};
    return isValid ? null : {'max': {msg}};
  };
}

export function atLeastOne(...values: string[]): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    if (control.value && control.value !== '') return null;
    for (const value of values) {
      if (value && value !== '') {
        return null;
      }
    }
    const msg = 'At least one must be filled';
    return {'atLeastOne': {msg}};
  };
}

export function interest(value: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    let isValid = true;
    const convertedValue = Number(value);
    if (convertedValue > 999999 || convertedValue < -99999 || countDecimals(convertedValue) > 2) isValid = false;
    return isValid ? null : {'interest': 'value to big, small or has too many decimal places'};
  };
}

function countDecimals(value: number) {
  if(Math.floor(value.valueOf()) === value.valueOf()) return 0;
  return value.toString().split(".")[1].length || 0;
}
