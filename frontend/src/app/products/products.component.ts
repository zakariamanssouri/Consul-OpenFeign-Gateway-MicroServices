import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  displayedColumns: string[] = ['id','name', 'price', 'quantity'];
  products:any;
  constructor(private http:HttpClient ) { }

  ngOnInit(): void {
    this.http.get('http://localhost:9999/inventory-service/products?projection=fullProduct').subscribe({
      next: (data) => {
        console.log("data"+data);
        this.products = data;
      },
      error: (err) => {
        console.log("error"+err);
      }
    })
  }

}
