export class PaymentData {
  cardNumber: string;
  expirationDate: string;
  holderName: string;
  ccv: string;

  constructor(cardNumber: string, expirationDate: string, holderName: string, ccv: string) {
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.holderName = holderName;
    this.ccv = ccv;
  }
}
