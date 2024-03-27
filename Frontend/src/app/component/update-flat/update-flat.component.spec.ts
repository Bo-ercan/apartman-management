import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeFlatComponent } from './update-flat.component';

describe('UpgradeFlatComponent', () => {
  let component: UpgradeFlatComponent;
  let fixture: ComponentFixture<UpgradeFlatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpgradeFlatComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpgradeFlatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
