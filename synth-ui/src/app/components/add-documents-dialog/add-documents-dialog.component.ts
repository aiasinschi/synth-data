import { Component, OnInit } from '@angular/core';
import {DocumentService} from '../../service/document.service';
import {MatDialogRef} from '@angular/material/dialog';
import {AppConstants} from '../../model/app-constants';

@Component({
  selector: 'app-add-documents-dialog',
  templateUrl: './add-documents-dialog.component.html',
  styleUrls: ['./add-documents-dialog.component.scss']
})
export class AddDocumentsDialogComponent implements OnInit {
  count: number = 0;

  constructor(private documentService: DocumentService,
              public dialogRef: MatDialogRef<AddDocumentsDialogComponent>) { }

  ngOnInit(): void {
  }

  addMockDocuments() {
    this.documentService.addMockDocuments(this.count).subscribe(
      count => {
        console.log(`Added ${count} mock documents.`);
        this.dialogRef.close(count);
      }
    )
  }

  onCloseClick(): void {
    this.dialogRef.close(AppConstants.DIALOG_RESULTS.CLOSE);
  }
}
