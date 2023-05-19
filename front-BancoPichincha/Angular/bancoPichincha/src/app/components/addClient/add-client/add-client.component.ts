import { Component ,Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent {

  @Output() closed = new EventEmitter<any>();

  closeModal() {
    this.closed.emit(false);

  }

}
