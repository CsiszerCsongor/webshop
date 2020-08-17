import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../shared/models/product-category.model';
import { ProductSubcategory } from '../shared/models/product-subcategory.model';
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
  private getProductCategoriesListUrl: string = "http://localhost:8080/productCategory/categories";

  private showSubmenuDiv: boolean = false;
  private cursorOnSubmenu: boolean = false;
  private productCategories: ProductCategory[] = [];
  private selectedProductCategory: ProductCategory;
  private productSubcategories: ProductSubcategory[] = [];
  private leftSideSubmenu: ProductSubcategory[] = [];
  private rightSideSubmenu: ProductSubcategory[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.getProductCategories();
  }
  
  setSubmenuDisplayTrue(productId: number): void {
    this.showSubmenuDiv = true;

    this.selectedProductCategory = this.productCategories.find(productCategory => productCategory.categoryId == productId);
    this.productSubcategories = this.selectedProductCategory.subcategoryList;

    var half_length = Math.ceil(this.productSubcategories.length / 2);
    this.leftSideSubmenu = this.productSubcategories.slice(0,half_length);
    this.rightSideSubmenu = this.productSubcategories.slice(half_length+1, this.productSubcategories.length);
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
