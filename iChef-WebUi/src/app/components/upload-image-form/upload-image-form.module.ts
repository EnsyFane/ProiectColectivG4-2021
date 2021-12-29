import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UploadImageFormComponent } from './upload-image-form.component';

@NgModule({
    declarations: [
        UploadImageFormComponent
    ],
    imports: [
        FormsModule,
        ReactiveFormsModule,
        CommonModule
    ],
    exports: [
        UploadImageFormComponent
    ]
})
export class UploadImageFormModule { }
