import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu/sidebar-menu.component';
import { HeaderComponent } from './components/header/header/header.component';
import { TableComponent } from './components/table/table/table.component';
import { DashboardComponent } from './pages/dashboard/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home/home.component';
import { ClientComponent } from './components/client/client/client.component';
import { AccountComponent } from './components/account/account/account.component';
import { MoveComponent } from './components/move/move/move.component';
import { ReportComponent } from './components/report/report/report.component';
import { HttpClientModule } from '@angular/common/http';
import { AddClientComponent } from './components/addClient/add-client/add-client.component';


@NgModule({
  declarations: [
    AppComponent,
    SidebarMenuComponent,
    HeaderComponent,
    TableComponent,
    DashboardComponent,
    HomeComponent,
    ClientComponent,
    AccountComponent,
    MoveComponent,
    ReportComponent,
    AddClientComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
