import { Component, OnInit } from '@angular/core';
import {SynthDocument} from "../../model/synth-document";
import {DocumentService} from "../../service/document.service";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.scss']
})
export class DocumentListComponent implements OnInit {

  count = 0;
  documents: SynthDocument[] = [];

  constructor(private documentService: DocumentService) { }

  ngOnInit() {
    this.documentService.getAllDocuments().subscribe(
      result => this.documents = result
    );
  }

  addMockDocuments() {
    this.documentService.addMockDocuments(this.count)
      .pipe(
        finalize(this.reloadDocuments)
      )
      .subscribe(
      count => console.log(`Added ${count} mock documents.`)
    );
  }

  private reloadDocuments() {
    this.documentService.getAllDocuments().subscribe(
      result => this.documents = result
    );
  }
}
