import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-upload-image-form',
    templateUrl: './upload-image-form.component.html',
    styleUrls: ['./upload-image-form.component.scss']
})
export class UploadImageFormComponent {
    @Output() imageLoaded: EventEmitter<File> = new EventEmitter<File>();

    uploadImageFormGroup = new FormGroup({
        image: new FormControl('')
    });

    dragOver(event: DragEvent): void {
        event.preventDefault();
        event.stopPropagation();
    }

    dragLeave(event: DragEvent): void {
        event.preventDefault();
        event.stopPropagation();
    }

    imageDropped(event: DragEvent): void {
        event.preventDefault();
        event.stopPropagation();
        const items = event.dataTransfer?.items;
        if (items && items.length >= 1) {
            const item = items[0];
            const webkitItem = item.webkitGetAsEntry();
            if (webkitItem?.isFile) {
                const file = event.dataTransfer?.files.item(0);
                if (file && file.type.split('/')[0] === 'image') {
                    this.imageLoaded.emit(file);
                }
            }
        }
    }

    imageUploaded(event: any): void {
        const image: File = event.target.files[0];
        this.imageLoaded.emit(image);
        this.uploadImageFormGroup.reset({ file: null }, { emitEvent: false });
    }
}
