import { Component, OnInit } from '@angular/core';
import { Flat } from '../../class/flat';
import { ActivatedRoute, Router } from '@angular/router';
import { FlatService } from '../../service/flat.service';

@Component({
  selector: 'app-flat-details',
  templateUrl: './flat-details.component.html',
  styleUrl: './flat-details.component.css'
})
export class FlatDetailsComponent implements OnInit{

  id: number = 0;
  flat: Flat = new Flat();
  flats : Flat[] = [];

  constructor(private route:ActivatedRoute,private flatService:FlatService, private router:Router){}


  private getFlats(){
    this.flatService.getFlatsList().subscribe(data=> {
      this.flats=data;
      console.log(data)
    })
  }

  ngOnInit(): void {
      this.id=this.route.snapshot.params['id'];
      this.flat=new Flat();
      this.flatService.getFlatById(this.id).subscribe(data=>{
        this.flat = data;
        console.log(data)
      });
  }

  updateFlat(id:number){
    this.router.navigate(['update-flat',id]);
  }
  
  deleteFlat(id:number){
    this.flatService.deleteFlat(id).subscribe(data=>{
      console.log(data)
      this.router.navigate(['/flats']);
    })
}
}
