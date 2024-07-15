# 8-Puzzle

[![Java](https://img.shields.io/badge/java-22.0.1-gree)](https://jdk.java.net/)

## Overview

Este projeto aborda a aplicação do algoritmo de busca em largura (**Breadth-first Search**) sobre o jogo **8-Puzzle**.

## Instalação

Clone o repositório e compile o código utilizando o comando **javac**, de acordo com o exemplo:

```Bash
git clone https://github.com/joaovitorbatistella/EightPuzzle.git
cd EightPuzzle/EightPuzzle
javac -d . src/com/mycompany/*.java
```

## Execução

Para executar, utilize o comando **java** e o caminho onde se encontra a função **Main**
```Bash
java com.mycompany.EightPuzzle
```
Ao executar, será necessário informar o puzzle de entrada e o puzzle na qual representará o estado final ou desejado.
As entradas devem conter valores inteiros separados por um espaço em branco, exemplo:
```text
1 2 4 3 0 5 7 6 8
```

A saída do programa contém o número de nodos criados até a solução do puzzle, o tempo de execução do resolvedor (**Breadth-first Search**), 
o número de passos necessários para solucionar o puzzle e qual o caminho para a solução. Exemplo:
```text
Running...
Goal found!
Tracing path...

Number of states needed to solve (expanded nodes): 9618
Time to solve (ms): 95.715531
Solution steps: 15

Path:

1 2 4 
3 0 5 
7 6 8 

1 2 4 
3 6 5 
7 0 8 

1 2 4 
3 6 5 
7 8 0 

1 2 4 
3 6 0 
7 8 5 

1 2 0 
3 6 4 
7 8 5 

1 0 2 
3 6 4 
7 8 5 

0 1 2 
3 6 4 
7 8 5 

3 1 2 
0 6 4 
7 8 5 

3 1 2 
6 0 4 
7 8 5 

3 1 2 
6 4 0 
7 8 5 

3 1 2 
6 4 5 
7 8 0 

3 1 2 
6 4 5 
7 0 8 

3 1 2 
6 4 5 
0 7 8 

3 1 2 
0 4 5 
6 7 8 

0 1 2 
3 4 5 
6 7 8
```