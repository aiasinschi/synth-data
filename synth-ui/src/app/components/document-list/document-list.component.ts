import { Component, OnInit } from '@angular/core';
import {SynthDocument} from "../../model/synth-document";
import {DocumentService} from "../../service/document.service";
import {MatDialog} from '@angular/material/dialog';
import {DocumentDialogComponent} from '../document-dialog/document-dialog.component';
import {AppConstants} from '../../model/app-constants';
import {AddDocumentsDialogComponent} from '../add-documents-dialog/add-documents-dialog.component';
import {getSortHeaderNotContainedWithinSortError} from '@angular/material/sort/sort-errors';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.scss']
})
export class DocumentListComponent implements OnInit {

  count = 0;
  documents: SynthDocument[] = [];
  displayedColumns: string[] = ['id', 'selected', 'shortName', 'name', 'code', 'content', 'access', 'pages'];
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

  deleteSelectedDocuments() {
    let selectedIds = this.getSelectedDocumentsIds();
    this.documentService.deleteDocuments(selectedIds).subscribe(
      res => {
        console.log(res);
        this.reloadDocuments();
      }

    )
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

  showAddMockDocsDialog() {
    const dialogRef = this.dialog.open(AddDocumentsDialogComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(
      count => this.reloadDocuments()
    )
  }

  private getSelectedDocumentsIds(): number[] {
    let list: number[] = [];
    for (const doc of this.documents) {
      if (doc.selected) {
        list.push(doc.id);
      }
    }
    console.log(list);
    return list;
  }
}
