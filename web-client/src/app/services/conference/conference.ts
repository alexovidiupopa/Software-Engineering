import * as moment from 'moment';
export interface Conference {
  conferenceName: string;
  preliminaryPhaseDeadline: moment.Moment;
  firstPhaseDeadline: moment.Moment;
  secondPhaseDeadline: moment.Moment;
  thirdPhaseDeadline: moment.Moment;
}
