import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { HomeComponent } from './home.component';

import { routing } from './home.routes';

@NgModule({
    imports: [CommonModule, FormsModule, routing],
    declarations: [HomeComponent],
    exports: []
})
export class HomeModule {

}