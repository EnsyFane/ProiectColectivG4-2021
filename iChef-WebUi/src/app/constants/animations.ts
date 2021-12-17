import { trigger, state, style, transition, animate } from '@angular/animations';

export const animations = {
    animeTrigger: trigger('slideInOut', [
        state('in', style({
          transform: 'translate3d(0,0,0)'
        })),
        state('out', style({
          transform: 'translate3d(-100%, 0, 0)'
        })),
        transition('in => out', animate('400ms ease-in-out')),
        transition('out => in', animate('400ms ease-in-out'))
      ]),
    animeTrigger2: trigger('slideInOut2', [
        state('in', style({
            width: '80%',
            margin: '0 0 0 20%'
        })),
        state('out', style({
            width: '100%'
        })),
        transition('in => out', animate('400ms ease-in-out')),
        transition('out => in', animate('400ms ease-in-out'))
    ]),
    animeTrigger3: trigger('slideInOut3', [
        state('in', style({
            width: '48%'
        })),
        state('out', style({
            width: '31.3333%'
        })),
        transition('in => out', animate('400ms ease-in-out')),
        transition('out => in', animate('400ms ease-in-out'))
    ])
};