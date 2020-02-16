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
  showDetailsDialog = false;
  selectedDocument: SynthDocument;

  constructor(private documentService: DocumentService) { }

  ngOnInit() {
    this.reloadDocuments();
  }

  addMockDocuments() {
    this.documentService.addMockDocuments(this.count).subscribe(
      count => {
        console.log(`Added ${count} mock documents.`);
        this.reloadDocuments();
      }
    );
  }

  private reloadDocuments() {
    this.documentService.getAllDocuments().subscribe(
      result => this.documents = result
    );
  }

  showDocumentDetails(doc: SynthDocument) {
    this.showDetailsDialog = true;
    this.selectedDocument = doc;
  }
}
