import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Flat } from "../../class/flat";
import { FlatService } from '../../service/flat.service';
import { AuthService } from "../../service/auth.service";
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators'; // map operatörünü import et

@Component({
  selector: 'app-flat-list',
  templateUrl: './flat-list.component.html',
  styleUrl: './flat-list.component.css'
})
export class FlatListComponent  implements OnInit{

  loggedIn: boolean = false;
  status: string ="false";
  
  currentPage: number =0 ;
  totalPages: number=0;
  pageButtons: number[] = [];
  flats: any[] = [];

constructor(private authService: AuthService, private flatService: FlatService,
  private router:Router,private http:HttpClient) { }
  ngOnInit(): void {

    this.checkLoggedInStatus();
    this.http.get('http://localhost:8080/api/v1/flats-pagination/2', { observe: 'response' }).subscribe(response => {
      const totalPagesString = response.headers.get('X-Total-Pages');
      this.totalPages = totalPagesString ? parseInt(totalPagesString) : 0;  // Butonları oluştur
      this.pageButtons = [];
      for (let i = 1; i <= this.totalPages; i++) {
        this.pageButtons.push(i);
      }
    });

    if(this.currentPage==0){
      this.currentPage=1;
    }
    console.log(this.currentPage)
    this.flatService.getFlatsByPage(this.currentPage).subscribe({
      next: (data) => {
        this.flats = data; // data nesnesinden flats alanını al
        // Diğer işlemleri de burada yapabilirsiniz, örneğin sayfalama bilgisi alınabilir
      },
      error: (error) => {
        console.error('Bir hata oluştu:', error);
        // Hata oluştuğunda flats ve diğer alanları uygun şekilde işleyin
      }
    });
  }
  artir() {
    this.currentPage++;
    if (this.currentPage >= this.totalPages){
      this.currentPage=this.totalPages;
    }
    this.yenidenVeriCagir();
 
    this.http.get('http://localhost:8080/api/v1/flats-pagination/1', { observe: 'response' }).subscribe(response => {
      console.log(response.headers.get('X-Total-Pages'));
    });
    
    
  }
  
  ilkSayfayaGit() {
    this.currentPage = 1;
    this.yenidenVeriCagir();
  }
  azalt() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.yenidenVeriCagir();
    }
  }
  
  belirliSayfayaGit(sayfaNumarasi: number) {
    if (sayfaNumarasi > 0) {
      this.currentPage = sayfaNumarasi;
      this.yenidenVeriCagir();
    }
  }
  sonSayfayaGit() {
  this.currentPage = this.totalPages;
  this.yenidenVeriCagir();
}
  
  yenidenVeriCagir() {
    this.flatService.getFlatsByPage(this.currentPage).subscribe({
      next: (data) => {
        this.flats = data;
      },
      error: (error) => {
        console.error('Bir hata oluştu:', error);
        // Hata oluştuğunda flats ve diğer alanları uygun şekilde işleyin
      }
    });
  }
  
  
logout() {
  this.authService.logout();
  this.loggedIn = false;
}

checkLoggedInStatus() {
  const loggedInValue = localStorage.getItem('loggedIn');
  this.loggedIn = loggedInValue === 'true';
}

flatDetails(id:number){
  this.router.navigate(['flat-details',id]);
}

updateFlat(id:number){
  this.router.navigate(['update-flat',id]);
}

deleteFlat(id:number){
  this.flatService.deleteFlat(id).subscribe(data=>{
    console.log(data)
  this.ngOnInit()
  })
}

navigateTo(url : string) {
  this.router.navigate([url]);
}

redirectToRoute(route: string): void {
    this.router.navigateByUrl('/login');
}

}
