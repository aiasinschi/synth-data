import { Component, OnInit } from '@angular/core';
import {SynthDocument} from "../../model/synth-document";
import {DocumentService} from "../../service/document.service";
import {MatDialog} from '@angular/material/dialog';
import {DocumentDialogComponent} from '../document-dialog/document-dialog.component';
import {AppConstants} from '../../model/app-constants';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.scss']
})
export class DocumentListComponent implements OnInit {

  count = 0;
  documents: SynthDocument[] = [];
  displayedColumns: string[] = ['id', 'shortName', 'name', 'code', 'content', 'access', 'pages'];
  selectedDocument: SynthDocument;

  constructor(private documentService: DocumentService, public dialog: MatDialog) { }

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

  deleteDocument(doc: SynthDocument) {
    this.documentService.deleteDocument(doc.id).subscribe(
      res => {
        console.log(res);
        this.reloadDocuments();
      }
    );
  }

  showDocumentDetails(doc: SynthDocument) {
    this.selectedDocument = doc;
    const dialogRef = this.dialog.open(DocumentDialogComponent, {
      width: '600px',
      data: {document: doc}
    });

    dialogRef.afterClosed().subscribe(
      result => {
        if (AppConstants.DIALOG_RESULTS.DELETE === result) {
          this.deleteDocument(this.selectedDocument);
        }
      }
    );
  }
}
