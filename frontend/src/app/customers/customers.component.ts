import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  displayedColumns: string[] = ['id','name','email','orders'];
  customers:any;
  c:any;
  constructor(private http:HttpClient,private router:Router) { }

  ngOnInit(): void {
    this.http.get('http://localhost:9999/customer-service/customers?projection=fullCustomer').subscribe({
      next: (data) => {
        console.log("data"+data);
        this.customers = data;
      },
      error: (err) => {
        console.log("error"+err);
      }
    })
  }


  getOrders(c: any) {
    this.router.navigateByUrl("/orders/"+c.id);
  }
}
