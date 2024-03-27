import { Component, OnInit } from '@angular/core';
import { Flat } from '../../class/flat';
import { FlatService } from '../../service/flat.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-flat',
  templateUrl: './update-flat.component.html',
  styleUrl: './update-flat.component.css'
})
export class UpdateFlatComponent implements OnInit{
  
  id: number = 0;
  flat: Flat = new Flat();

  constructor(private flatService: FlatService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.flatService.getFlatById(this.id)
        .subscribe({
          next: (data) => {
            console.log(data)
            this.flat = data
          },
          error: (error) => console.log(error),
          complete: () => console.log("Data init completed!")
        })
  }

  onSubmit(){
    this.updateFlat();
    
  }

  updateFlat(){
    this.flatService.updateFlat(this.id, this.flat)
      .subscribe({
        next: (data) => console.log(data),
        error: (error) => console.log(error),
        complete: () => this.goToFlatList()
      })
  }

  goToFlatList(){
    this.router.navigate(['/flats-pagination'])};
  
}