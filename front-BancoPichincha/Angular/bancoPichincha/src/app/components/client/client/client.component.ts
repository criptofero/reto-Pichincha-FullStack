import { Component } from '@angular/core';
import { Client } from '../../../model/client';
import { ClientServiceService } from 'src/app/services/client-service.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  constructor(private clientService: ClientServiceService){

  }
  //Objeto cliente para prueba con datos quemados
  /*
  client:Client = {
    id: 0,
    numberDocument: '1235487',
    typedocument: 'Pasaporte',
    name: 'John',
    lastName: 'Doe',
    gender: 'Masculino',
    address: 'Cr 45 #25-6',
    numberPhone: '45875698'
  }
  client2:Client = {
    id: 1,
    numberDocument: '1235487',
    typedocument: 'Pasaporte',
    name: 'John',
    lastName: 'Doe',
    gender: 'Masculino',
    address: 'Cr 45 #25-6',
    numberPhone: '45875698'
  }
*/
  //Lista vacia
clients: Client[] = [];
isOpen: any = false;

  ngOnInit(){

    this.clientService.getClients().subscribe(
      response => {
        this.clients = response;
      },
      error => {
        console.log(error);
      }
    );
  }

  //Modal para agregar cliente
  openModaladdClient(){
    this.isOpen = true;
  }
  closeModaladdClient(event:Event){
    this.isOpen = event
  }


}

