import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../shared/models/product-category.model';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import {catchError} from 'rxjs/operators';

@Component({
  selector: 'app-big-news',
  templateUrl: './big-news.component.html',
  styleUrls: ['./big-news.component.sass']
})
export class BigNewsComponent implements OnInit {

  private showSubmenuDiv: boolean = false;
  private cursorOnSubmenu: boolean = false;
  private productCategories: ProductCategory[] = [];

  private getProductCategoriesListUrl: string = "http://localhost:8080/productCategory/categories";

  private delay2 = (ms: number) => new Promise(res => setTimeout(res, ms));

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.getProductCategories();
  }

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }

  setSubmenuDisplayTrue(): void {
    this.showSubmenuDiv = true;
  }

  setSubmenuFalseAfterDelay(): void{
    this.delay(500).then(() => {
      console.log("*");
      if(!this.cursorOnSubmenu){
        this.showSubmenuDiv = false;
      }
    });
  }

  cursorNotOnSubmenu(): void {
    if(this.showSubmenuDiv)
    this.showSubmenuDiv = false;
  }

  getProductCategories(){
    this.getProductCategoriesHttp().subscribe(productCategories =>
      {
        this.productCategories = productCategories
        console.log(this.productCategories);
      });
  }

  getProductCategoriesHttp(): Observable<ProductCategory[]> {
    if(this.productCategories.length == 0){
      return this.httpClient.get<ProductCategory[]>(this.getProductCategoriesListUrl)
      .pipe(
        map(data => data.map(data => new ProductCategory().deserialize(data)))
      )
    }
  }

}
