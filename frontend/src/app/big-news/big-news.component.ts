import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../shared/models/product-category.model';
import { ProductSubcategory } from '../shared/models/product-subcategory.model';
import { TopNewsImage } from '../shared/models/top-news-image.model';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import {catchError} from 'rxjs/operators';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-big-news',
  templateUrl: './big-news.component.html',
  styleUrls: ['./big-news.component.scss']
})
export class BigNewsComponent implements OnInit {
  private getProductCategoriesListUrl: string = "http://localhost:8080/productCategory/categories";
  private getImageUrl: string = "http://localhost:8080/file/image/download/multiple/topNewsImage";

  private showSubmenuDiv: boolean = false;
  private cursorOnSubmenu: boolean = false;
  private productCategories: ProductCategory[] = [];
  private selectedProductCategory: ProductCategory;
  private productSubcategories: ProductSubcategory[] = [];
  private leftSideSubmenu: ProductSubcategory[] = [];
  private rightSideSubmenu: ProductSubcategory[] = [];

  private topNewsImages: TopNewsImage[] = [];
  private startIndex: number = 0;
  private demo_image: SafeUrl;
  private demo_images: SafeUrl[] = [];

  constructor(private httpClient: HttpClient,
              private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.getProductCategories();
    this.getImages();
    this.Repeat();
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

  getImages(){
    this.httpClient.get<TopNewsImage[]>(this.getImageUrl)
    .pipe(
      map(data => data.map(data => new TopNewsImage().deserialize(data)))
    ).subscribe(responseList => {
      this.topNewsImages = responseList;

      this.createImageFromByteData();
    });
  }

  createImageFromByteData(){
    //let objectURL;
    for (var index in this.topNewsImages) {
      console.log('index : ' + index)
      let filledContent = 'data:image/png;base64,' + this.topNewsImages[index].content;
      this.demo_images.push(this.sanitizer.bypassSecurityTrustUrl(filledContent));
    }
  }




  Repeat() {
    setTimeout(() => {
        this.__FunctionSlide();
        this.Repeat();
      }, 5000);
    }

    __FunctionSlide() {
      const slides = Array.from(document.getElementsByClassName('mall-show-slide'));
      if (slides === []) {
        this.Repeat();
      }
      for (const x of slides) {
        const y = x as HTMLElement;
        y.style.display = 'none';
      }
      if (this.startIndex > slides.length - 1) {
        this.startIndex = 0;
        const slide = slides[this.startIndex] as HTMLElement;
        slide.style.display = 'block';
        this.startIndex++;
      } else {

        const slide = slides[this.startIndex] as HTMLElement;
        slide.style.display = 'block';
        this.startIndex++;
      }
    }
}
