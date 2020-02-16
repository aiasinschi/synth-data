import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SynthDocument} from "../../model/synth-document";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.scss']
})
export class DocumentComponent implements OnInit {

  @Input()
  document: SynthDocument;
  @Output()
  closeDocDetailsEvent = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() {
  }

  closeDocumentDetails() {
    this.closeDocDetailsEvent.emit(false);
  }
}
