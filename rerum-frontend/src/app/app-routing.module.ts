import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BovinoComponent } from './bovino/bovino.component';


const routes: Routes = [
  {
   path: 'bovinos',
   component: BovinoComponent
  },
  {
    path: '',
    redirectTo: 'bovinos',
    pathMatch: 'full'
   },
   {
    path: '**',
    redirectTo: 'bovinos',
    pathMatch: 'full'
   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
