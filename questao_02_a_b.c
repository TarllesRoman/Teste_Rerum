#include <stdio.h>
#include <stdlib.h>

/** Retorna o menor elemento do vetor de inteiros;
*   retorna 0 caso o tamanho recebido seja menor ou igual a 0
*/
int encontrar_menor(int *vetor, int tamanho){
    int menor, i;

    if (tamanho <= 0) return 0;

    menor = vetor[0];
    for(i = 1; i < tamanho; i++)
        if(vetor[i] < menor) menor = vetor[i];

    return menor;
}

/** Como não foi informado qual o tamanho do vetor utilizado,
 *  escolhi o método insertion sort para ordenação crescente de
 *  elementos por ser uma boa escolha em vetores pequenos onde
 *  enfrentaria seu pior caso, tamanho² comparações, apenas
 *  caso o vetor esteja ordenado de forma decrescente.
 * */
void insertion_sort(int *vetor, int tamanho){    
  int i, j, aux; 
 
  for(i = 1; i < tamanho; i++){ 
    j = i; 
 
    while((j != 0) && (vetor[j] < vetor[j - 1])) { 
      aux = vetor[j]; 
      vetor[j] = vetor[j - 1]; 
      vetor[j - 1] = aux; 
      j--;     
    } 
  } 
}

int main() {
    int *vetor, tamanho, i;

    printf("Informe o tamanho do vetor desejado: ");
    scanf("%d", &tamanho);

    vetor = (int*) malloc(sizeof(int)*tamanho);

    for(i = 0; i < tamanho; i++){
        printf("\n[%d]: ", i);
        scanf("%d",&vetor[i]);
    }

    printf("\n\nO menor valor fornecido e: %d", encontrar_menor(vetor,tamanho));

    printf("\n\nVetor ordenado de forma ascendente: ");
    insertion_sort(vetor, tamanho);
    for(i = 0; i < tamanho; i++){
        printf("\n[%d]: %d", i, vetor[i]);
    }

    free(vetor);
}