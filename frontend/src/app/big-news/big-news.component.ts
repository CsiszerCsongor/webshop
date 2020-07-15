import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-big-news',
  templateUrl: './big-news.component.html',
  styleUrls: ['./big-news.component.sass']
})
export class BigNewsComponent implements OnInit {

  showSubmenuDiv: boolean = false;
  cursorOnSubmenu: boolean = false;

  delay2 = (ms: number) => new Promise(res => setTimeout(res, ms));

  constructor() { }

  ngOnInit() {
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

}
