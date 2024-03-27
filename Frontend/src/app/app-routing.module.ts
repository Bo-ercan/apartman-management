import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlatListComponent } from './component/flat-list/flat-list.component';
import { CreateFlatComponent } from './component/create-flat/create-flat.component';
import { UpdateFlatComponent } from "./component/update-flat/update-flat.component";
import { FlatDetailsComponent } from './component/flat-details/flat-details.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { AuthGuard } from './service/is-authenticated.service'; // AuthGuard'ı ekleyin


const routes: Routes = [
  {path:'flats-pagination',component:FlatListComponent},
  {path:'create-flat', component: CreateFlatComponent, canActivate: [AuthGuard] },
  {path:'update-flat/:id', component: UpdateFlatComponent, canActivate: [AuthGuard] },
  {path:'flat-details/:id',component:FlatDetailsComponent, canActivate: [AuthGuard]},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'',redirectTo:'flats-pagination',pathMatch:'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
