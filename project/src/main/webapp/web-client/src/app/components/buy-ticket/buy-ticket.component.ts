import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Session} from '../../model/session';
import {TicketService} from '../../services/ticket/ticket.service';
import {Router} from '@angular/router';
import {Ticket} from '../../model/ticket';

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css']
})
export class BuyTicketComponent implements OnInit {
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  firstName: string;
  lastName: string;
  email: string;
  sessions: Session[];
  ticketCost: number;
  selectedOptions: Session[] = [];
  badData = false;

  constructor(private ticketService: TicketService, private router: Router) { }

  ngOnInit(): void {
    this.ticketService.getSessionsWithAvailableSeats().subscribe(sessions => this.sessions = sessions);
  }

  buyAnotherTicket() {
    if (this.validData()) {
      const cart: Ticket[] = JSON.parse(localStorage.getItem('cart'));
      const newTicket = this.createTicket();
      cart.push(newTicket);
      localStorage.setItem('cart', JSON.stringify(cart));
      location.reload();
      this.badData = false;
    } else {
      this.badData = true;
    }
  }

  proceedToCheckout() {
    if (this.validData()) {
      const cart: Ticket[] = JSON.parse(localStorage.getItem('cart'));
      const newTicket = this.createTicket();
      cart.push(newTicket);
      localStorage.setItem('cart', JSON.stringify(cart));
      this.router.navigateByUrl('/checkout');
      this.badData = false;
    } else {
      this.badData = true;
    }
  }

  private createTicket(): Ticket {
    this.sessions = this.selectedOptions;
    return new Ticket(this.firstName, this.lastName, this.email, this.sessions);
  }

  selectionChanged($event: any) {
    let total = 0;
    for (const session of this.selectedOptions) {
      total += session.price;
    }
    this.ticketCost = total;
  }

  private validData() {
    return this.firstName && this.lastName && this.email && this.selectedOptions.length > 0;
  }
}
