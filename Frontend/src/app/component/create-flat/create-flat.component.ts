import { Component, OnInit } from '@angular/core';
import { Flat } from '../../class/flat';
import { FlatService } from '../../service/flat.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-flat',
  templateUrl: './create-flat.component.html',
  styleUrl: './create-flat.component.css'
})
export class CreateFlatComponent implements OnInit{

  flat: Flat = new Flat();
  floorOptions: string[] = ['Bahçe Katı', 'Giriş', '1', '2', '3', '4', '5', '6'];
  ageOptions: string[] = ['0', '1', '2', '3', '4', '5', '10'];
  heatingOptions: string[] = ['Kombi', 'Doğalgaz', 'Klima', 'Merkezi', 'Payölçer', 'Soba'];

  constructor(private flatService: FlatService,
    private router : Router){}

  ngOnInit(): void {
      
  }

  saveFlat(){
    this.flatService.createFlat(this.flat).subscribe(data=>{
      console.log(data);
      this.goToFlatList();
    },
    error=> console.log(error))
  }

  goToFlatList(){
    this.router.navigate(['/flats-pagination/'])
  }

  onSubmit(){
    console.log(this.flat);
    this.saveFlat();
  }

}
