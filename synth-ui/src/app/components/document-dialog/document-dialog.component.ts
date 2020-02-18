import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {SynthDocument} from '../../model/synth-document';
import {AppConstants} from '../../model/app-constants';

@Component({
  selector: 'app-document-dialog',
  templateUrl: './document-dialog.component.html',
  styleUrls: ['./document-dialog.component.scss']
})
export class DocumentDialogComponent {

  constructor(public dialogRef: MatDialogRef<DocumentDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.document = data.document;
  }

  document: SynthDocument;

 onCloseClick(): void {
    this.dialogRef.close(AppConstants.DIALOG_RESULTS.CLOSE);
 }

 onDeleteClick(event): void {
   console.log('Deleting document... mock');
   this.dialogRef.close(AppConstants.DIALOG_RESULTS.DELETE);
 }

}
