import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BigNewsComponent } from './big-news/big-news.component';

const routes: Routes = [
  { path: "", component: BigNewsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
