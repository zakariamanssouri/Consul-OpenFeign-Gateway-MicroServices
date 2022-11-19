import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-orders-details',
  templateUrl: './orders-details.component.html',
  styleUrls: ['./orders-details.component.css']
})
export class OrdersDetailsComponent implements OnInit {
  OrderDetails:any;
  orderId!:number;

  constructor(private http:HttpClient,private router:Router,private route:ActivatedRoute) {
    this.orderId=route.snapshot.params['orderId'];
  }

  ngOnInit(): void {
    this.http.get('http://localhost:9999/order-service/fullOrder/'+this.orderId).subscribe({
      next: (data) => {
        this.OrderDetails = data;
      },
      error: (err) => {
        console.log("error"+err);
      }
    })
  }


}
