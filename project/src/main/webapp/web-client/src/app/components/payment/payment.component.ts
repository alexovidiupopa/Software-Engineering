import {Component, OnInit} from '@angular/core';
import {Ticket} from '../../model/ticket';
import {TicketService} from '../../services/ticket/ticket.service';
import {PaymentData} from '../../model/payment-data';
import {Router} from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  cardNumber: string;
  name: string;
  expiringDate: string;
  ccv: string;
  totalPrice: number;
  badData = false;
  tickets: Ticket[];

  constructor(private ticketService: TicketService, private router: Router) {
  }

  ngOnInit(): void {
    this.tickets = JSON.parse(localStorage.getItem('cart'));
    let total = 0;
    for (const ticket of this.tickets) {
      for (const session of ticket.sessions) {
        total += session.price;
      }
    }
    this.totalPrice = total;
    localStorage.setItem('cart', JSON.stringify(new Array<Ticket>()));
  }


  pay() {
    if (this.validData()) {
      this.ticketService.buyTickets(this.tickets, new PaymentData(this.cardNumber, this.expiringDate, this.name, this.ccv))
        .subscribe(response => {
          if (response === true) {
            this.router.navigateByUrl('/pay/end');
          } else {
            this.badData = true;
          }
        });
      this.badData = false;
    } else {
      this.badData = true;
    }
  }

  private validData() {
    return this.validateCardNumber() && this.validateDate() && this.validateCCV() && this.validateName();
  }

  private validateCardNumber() {
    const re = new RegExp('\\b4[0-9]{3}[ -]*[0-9]{4}[ -]*[0-9]{4}[ -]*[0-9](?:[0-9]{3})?\\b|4321\\.4321\\.4321\\.4321|4321\\/4321\\/4321\\/4321|4321\\\\4321\\\\4321\\\\4321');
    const val = this.cardNumber.length > 0 && re.test(this.cardNumber);
    console.log('number', val);
    return val;
  }

  private validateDate() {
    // const re = new RegExp('^((0[1-9])|(2[0-9]))[\\/\\.\\-]*((0[1-9])|(2[0-9]))$');
    //   const val = this.expiringDate.length > 0 && re.test(this.expiringDate);
    return this.expiringDate.length > 0;
  }

  private validateCCV() {
    const re = new RegExp('[0-9]{3,4}');
    const val = this.ccv.length > 0 && re.test(this.ccv);
    console.log('ccv', val);
    return val;
  }

  private validateName() {
    const val = this.name.length > 0;
    console.log('name', val);
    return val;
  }
}
