import {Component, OnInit} from '@angular/core';
import {Ticket} from '../../model/ticket';
import {Router} from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  tickets: Ticket[];
  totalPrice: number;

  constructor(private router: Router) {
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
  }

  proceedToPayment() {
    this.router.navigateByUrl('/pay/safe');
  }

  getPrice(ticket: Ticket): number {
    let total = 0;
    for (const session of ticket.sessions) {
      total += session.price;
    }
    return total;
  }
}
