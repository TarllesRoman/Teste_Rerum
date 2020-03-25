#ifndef PILHA_H_INCLUDED
#define PILHA_H_INCLUDED

typedef struct {
     char* nome;
} tipo_elemento;

typedef struct nodo{
    tipo_elemento info;
    struct nodo *prox;
}tipo_nodo;

typedef struct {
     tipo_nodo* topo;
     tipo_nodo* base;
     int tamanho;
} tipo_pilha;

typedef tipo_pilha *pilha;

pilha cria_pilha();
int push(pilha,tipo_elemento);
tipo_elemento pop(pilha);
int esta_vazia(pilha);
void termina_pilha(pilha);

#endif // PILHA_H_INCLUDED

