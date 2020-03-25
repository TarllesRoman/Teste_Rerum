import { Bovino } from '../model/bovino';
import { BovinoService } from './bovino.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-bovino',
  templateUrl: './bovino.component.html',
  styleUrls: ['./bovino.component.css']
})
export class BovinoComponent implements OnInit {

  formulario: FormGroup;
  formRelatorio: FormGroup;
  lista: Bovino[];
  listaFeminina: Bovino[];
  listaMasculina: Bovino[];
  aparecerMsg = false;

  list = true;
  report = false;
  register = false;

  msgPositiva = true;
  msgNegativa = null;
  remover = false;

  constructor(private service: BovinoService, private builder: FormBuilder) { }

  ngOnInit() {
    this.rstFormulario();
    this.listar();
    this.formRelatorio = this.builder.group(
      {
        data1: [null, [Validators.required, Validators.maxLength(10)]],
        data2: [null, [Validators.required, Validators.maxLength(10)]]
      });
  }

  // resetar formulario
  rstFormulario() {
    if (this.formulario != null) {
      this.formulario.reset();
    }
    this.formulario = this.builder.group(
      {
        codigo: [null],
        brinco: [null, [ Validators.required, Validators.maxLength(8)] ],
        nome: [null, [ Validators.required, Validators.maxLength(20)] ],
        situacao: ["Seca"],
        sexo: ["F", Validators.required],
        brincoMae: [null, [Validators.maxLength(8)] ],
        brincoPai: [null, [Validators.maxLength(8)] ],
        raca: ["Girolando", [Validators.maxLength(15)] ],
        nascimento: [ null, [Validators.maxLength(10)] ],
        prenhes: [ null, [Validators.maxLength(10)] ],
        parto: [ null, [Validators.maxLength(10)] ],
        ultimoParto: [ null, [Validators.maxLength(10)] ],
      }
    );
    this.listar();
  }

  // setar formulario
  setFormulario(Bovino: Bovino) {
    if (this.formulario != null) {
      this.formulario.reset();
    }

    this.formulario.patchValue(Bovino);
  }

  salvar() {
    const Bovino = this.formulario.value as Bovino;

    this.service.criar(Bovino).subscribe(
      retorno => {
        console.dir(retorno, { depth: null });
        this.aparecerMsg = true;
        this.rstFormulario();
      },
      error => {
        this.msgNegativa = error.error[0].mensagemUsuario;
      }
    );
  }

  listar() {
    this.service.listar().subscribe(
      retorno => {
        this.lista = retorno;
        this.findListaFeminina();
        this.findListaMasculina();
      },
      error => {
        console.dir(error, { depth: null });
      }
    );
  }

  atualizar() {
    const Bovino = this.formulario.value as Bovino;

    this.service.atualizar(Bovino.codigo, Bovino).subscribe(
      retorno => {
        // console.dir(retorno, { depth: null });
        this.aparecerMsg = true;
        this.setFormulario(retorno);
      },
      error => {
        this.msgNegativa = error.error[0].mensagemUsuario;
      }
    );
  }

  cancelar() {
    this.rstFormulario();
  }

  excluir() {
    const Bovino = this.formulario.value as Bovino;

    this.service.remover(Bovino.codigo).subscribe(
      retorno => {
        this.remover = false;
        this.showList();
      }
    );
  }

  showList() {
    this.listar();

    this.list = true;
    this.report = false;
    this.register = false;
  }

  showRegister() {
    this.list = false;
    this.report = false;
    this.register = true;

    this.aparecerMsg = false;
    this.rstFormulario();
  }

  update(Bovino: Bovino) {
    this.list = false;
    this.report = false;
    this.register = true;

    this.aparecerMsg = false;
    this.setFormulario(Bovino);
  }

  buscarNome(nome: string) {
    this.service.buscarNome(nome).subscribe(
      retorno => {
        console.dir(retorno, { depth: null });
        this.lista = retorno;
      },
      error => {
        console.dir(error, { depth: null });
      }
    );
  }

  buscarBrinco(brinco: string) {
    this.service.buscarBrinco(brinco).subscribe(
      retorno => {
        console.dir(retorno, { depth: null });
        this.lista = retorno;
      },
      error => {
        console.dir(error, { depth: null });
      }
    );
  }

  findListaFeminina() {
    this.listaFeminina = new Array();
        this.lista.map(
          (b) => {
            if (b.sexo == 'F')
              this.listaFeminina.push(b);
          }
        )
  }

  findListaMasculina() {
    this.listaMasculina = new Array();
    this.lista.map(
      (b) => {
        if (b.sexo == 'M')
          this.listaMasculina.push(b);
      }
    )
  }

}
