import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlatListComponent } from './component/flat-list/flat-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateFlatComponent } from './component/create-flat/create-flat.component';
import { FormsModule } from "@angular/forms";
import { UpdateFlatComponent } from './component/update-flat/update-flat.component';
import { FlatDetailsComponent } from './component/flat-details/flat-details.component';
import { RegisterComponent } from './component/register/register.component';
import { LoginComponent } from './component/login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    FlatListComponent,
    CreateFlatComponent,
    UpdateFlatComponent,
    FlatDetailsComponent,
    RegisterComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
