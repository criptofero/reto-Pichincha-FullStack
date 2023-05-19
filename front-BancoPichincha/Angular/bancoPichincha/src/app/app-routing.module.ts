import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { ClientComponent } from './components/client/client/client.component';
import { AccountComponent } from './components/account/account/account.component';
import { MoveComponent } from './components/move/move/move.component';
import { ReportComponent } from './components/report/report/report.component';

const routes: Routes = [
  {
    path: '',
    component:HomeComponent
  },
  {
    path: 'cliente',
    component:ClientComponent
  },
  {
    path: 'cuenta',
    component:AccountComponent
  },
  {
    path: 'movimiento',
    component:MoveComponent
  },
  {
    path: 'reporte',
    component:ReportComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
