# 8-Puzzle

[![Java](https://img.shields.io/badge/java-22.0.1-gree)](https://jdk.java.net/)

## Overview

Este projeto aborda a aplicação do algoritmo de busca em largura (**Breadth-first Search**) e do algoritmo de  Busca de Caminho - A* (**A Star Search**) no contexto do jogo **8-Puzzle**. O algoritmo A* utiliza da heurística: Distância de Manhattan. 
Foi desenvolvido com Java 22, no Linux Ubuntu 22.04.

## Instalação

Clone o repositório e compile o código utilizando o comando **javac**, de acordo com o exemplo:

```Bash
git clone https://github.com/joaovitorbatistella/EightPuzzle.git
cd EightPuzzle/EightPuzzle
javac -d . src/com/mycompany/*.java
```

## Heurística

A heurística utilizada no algoritmo A* é a distância de Manhattan. Esta heurística soma a distância de cada peça de sua posição atual até a posição final.
Para calcular a distância, são encontrados primeiro a linha e coluna do valor correspondente no estado atual, e depois a linha e coluna do valor correspondente no estado final. Então, é  aplicado a fórumula abaixo, onde Math.abs retorna o valor absoluto do numeral informado, ou seja, se o valor é negativo, ele será retornado positivo, se for positivo continua positivo. O valor é somado para cada peça do tabuleiro. Isso servirá para o algoitmo saber qual nodo está mais pŕoximo do nodo objetivo. Veja a fórnula: 

```Java
    totalDistance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol)
```

## Diferenças na implementação de **Breadth-first Search** e **A\***
A estrutura de classes é a mesma, porém, é adicionado 3 novos atributos à classe *Node* para suportar a **A\***, são eles:
```Java
	public double f; // custo do caminho desde o início até este nó
	public double g; // heurística, estimativa do custo até o objetivo
	public double h; // função de avaliação f(n) = g(n) + h(n)
```

Para as listas de controle dos estados, a estrutura utilizadadifere. No **Breadth-first Search** são usadas duas filas simples, enquanto para **A\*** é utilizado uma lista simples para os nós 'fechados' e uma PriorityQueue para os nós abertos, está na qual recebe a instância da classe NodeComparator como argumento do seu *__construct*, NodeComparator tem a responsabilidade de ordenar os elementos da fila a partir de uma regra estabelecida, neste caso o mnor valor tem prioridade.

Quanto ao funcionamento do algoritmo, ambos percorrem a lista de nodos abertos e espandem os nodos filhos. O que difere é utilização da heurística no algoritmo **A\***, que irá manusear a lista de nodos abertos de modo que se imponha uma prioridade na análise dos nodos (puzzles). No **A\***, caso um nodo filho tenha um custo estimado menor do que seu pai, ele é adicionado a fila de nodos abertos, e seu pai é removido dela.


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
```text
0 1 2 3 4 5 6 7 8
```

Além disso, é necessário informar qual dos algoritmos será o escolhido para resolver o puzzle:
```text
Choose an algorithm. Type 0 for BFS and 1 for A

1
```

A saída do programa contém o número de nodos criados até a solução do puzzle, o tempo de execução do resolvedor (**Breadth-first Search** ou **A\***), 
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

## Testes

Os testes executados foram comparados à performance do resolvedor **Sliding Generator And Solver**, aqui abreviado para *SGAS*.


###  - BFS

### Teste #1
Gerado no SGAS com shuffle = 1.

Entradas: 
```text
1 2 3 4 5 0 7 8 6
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 3
Time to solve (ms): 0.22812
Solution steps: 1

Path:

1 2 3 
4 5 0 
7 8 6 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>0</SolutionTime>
<NoOfStoredNodes>3</NoOfStoredNodes>
<NoOfNodeExpansion>2</NoOfNodeExpansion>
<SolutionStep>1</SolutionStep>

<Node0>1,2,3,4,5,0,7,8,6</Node0>
<Node1>1,2,3,4,5,6,7,8,0</Node1>
```


### Teste #2
Gerado no SGAS com shuffle = 3.

Entradas: 
```text
1 2 3 0 4 6 7 5 8
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 20
Time to solve (ms): 0.359827
Solution steps: 3

Path:

1 2 3 
0 4 6 
7 5 8 

1 2 3 
4 0 6 
7 5 8 

1 2 3 
4 5 6 
7 0 8 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>0</SolutionTime>
<NoOfStoredNodes>12</NoOfStoredNodes>
<NoOfNodeExpansion>13</NoOfNodeExpansion>
<SolutionStep>3</SolutionStep>

<Node0>1,2,3,0,4,6,7,5,8</Node0>
<Node1>1,2,3,4,0,6,7,5,8</Node1>
<Node2>1,2,3,4,5,6,7,0,8</Node2>
<Node3>1,2,3,4,5,6,7,8,0</Node3>
```


### Teste #3
Gerado no SGAS com shuffle = 9.

Entradas: 
```text
1 2 3 0 5 7 4 8 6
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 534
Time to solve (ms): 1.909455
Solution steps: 9

Path:

1 2 3 
0 5 7 
4 8 6 

1 2 3 
5 0 7 
4 8 6 

1 2 3 
5 7 0 
4 8 6 

1 2 3 
5 7 6 
4 8 0 

1 2 3 
5 7 6 
4 0 8 

1 2 3 
5 0 6 
4 7 8 

1 2 3 
0 5 6 
4 7 8 

1 2 3 
4 5 6 
0 7 8 

1 2 3 
4 5 6 
7 0 8 

1 2 3 
4 5 6 
7 8 0
```

Saída no SGAS:
```xml
<SolutionTime>8</SolutionTime>
<NoOfStoredNodes>236</NoOfStoredNodes>
<NoOfNodeExpansion>388</NoOfNodeExpansion>
<SolutionStep>9</SolutionStep>

<Node0>1,2,3,0,5,7,4,8,6</Node0>
<Node1>1,2,3,5,0,7,4,8,6</Node1>
<Node2>1,2,3,5,7,0,4,8,6</Node2>
<Node3>1,2,3,5,7,6,4,8,0</Node3>
<Node4>1,2,3,5,7,6,4,0,8</Node4>
<Node5>1,2,3,5,0,6,4,7,8</Node5>
<Node6>1,2,3,0,5,6,4,7,8</Node6>
<Node7>1,2,3,4,5,6,0,7,8</Node7>
<Node8>1,2,3,4,5,6,7,0,8</Node8>
<Node9>1,2,3,4,5,6,7,8,0</Node9>
```


### Teste #4
Gerado no SGAS com shuffle = 27.

Entradas: 
```text
8 4 2 0 3 5 7 6 1
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 150484
Time to solve (ms): 33964.743003
Solution steps: 21

Path:

8 4 2 
0 3 5 
7 6 1 

8 4 2 
7 3 5 
0 6 1 

8 4 2 
7 3 5 
6 0 1 

8 4 2 
7 3 5 
6 1 0 

8 4 2 
7 3 0 
6 1 5 

8 4 2 
7 0 3 
6 1 5 

8 4 2 
7 1 3 
6 0 5 

8 4 2 
7 1 3 
0 6 5 

8 4 2 
0 1 3 
7 6 5 

8 4 2 
1 0 3 
7 6 5 

8 0 2 
1 4 3 
7 6 5 

0 8 2 
1 4 3 
7 6 5 

1 8 2 
0 4 3 
7 6 5 

1 8 2 
4 0 3 
7 6 5 

1 0 2 
4 8 3 
7 6 5 

1 2 0 
4 8 3 
7 6 5 

1 2 3 
4 8 0 
7 6 5 

1 2 3 
4 8 5 
7 6 0 

1 2 3 
4 8 5 
7 0 6 

1 2 3 
4 0 5 
7 8 6 

1 2 3 
4 5 0 
7 8 6 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>1333</SolutionTime>
<NoOfStoredNodes>21724</NoOfStoredNodes>
<NoOfNodeExpansion>57393</NoOfNodeExpansion>
<SolutionStep>21</SolutionStep>

<Node0>8,4,2,0,3,5,7,6,1</Node0>
<Node1>8,4,2,7,3,5,0,6,1</Node1>
<Node2>8,4,2,7,3,5,6,0,1</Node2>
<Node3>8,4,2,7,3,5,6,1,0</Node3>
<Node4>8,4,2,7,3,0,6,1,5</Node4>
<Node5>8,4,2,7,0,3,6,1,5</Node5>
<Node6>8,4,2,7,1,3,6,0,5</Node6>
<Node7>8,4,2,7,1,3,0,6,5</Node7>
<Node8>8,4,2,0,1,3,7,6,5</Node8>
<Node9>8,4,2,1,0,3,7,6,5</Node9>
<Node10>8,0,2,1,4,3,7,6,5</Node10>
<Node11>0,8,2,1,4,3,7,6,5</Node11>
<Node12>1,8,2,0,4,3,7,6,5</Node12>
<Node13>1,8,2,4,0,3,7,6,5</Node13>
<Node14>1,0,2,4,8,3,7,6,5</Node14>
<Node15>1,2,0,4,8,3,7,6,5</Node15>
<Node16>1,2,3,4,8,0,7,6,5</Node16>
<Node17>1,2,3,4,8,5,7,6,0</Node17>
<Node18>1,2,3,4,8,5,7,0,6</Node18>
<Node19>1,2,3,4,0,5,7,8,6</Node19>
<Node20>1,2,3,4,5,0,7,8,6</Node20>
<Node21>1,2,3,4,5,6,7,8,0</Node21>
```


###  - A*

### Teste #1
Gerado no SGAS com shuffle = 1.

Entradas: 
```text
1 2 3 4 5 0 7 8 6
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 3
Time to solve (ms): 2.117363
Solution steps: 1
Open list: 2
Closed list: 1

Path:

1 2 3 
4 5 0 
7 8 6 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>5</SolutionTime>
<NoOfStoredNodes>3</NoOfStoredNodes>
<NoOfNodeExpansion>2</NoOfNodeExpansion>
<SolutionStep>1</SolutionStep>
<Node0>1,2,3,4,5,0,7,8,6</Node0>
<Node1>1,2,3,4,5,6,7,8,0</Node1>
```


### Teste #2
Gerado no SGAS com shuffle = 3.

Entradas: 
```text
1 2 3 0 4 6 7 5 8
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 10
Time to solve (ms): 1.734313
Solution steps: 3
Open list: 5
Closed list: 3

Path:

1 2 3 
0 4 6 
7 5 8 

1 2 3 
4 0 6 
7 5 8 

1 2 3 
4 5 6 
7 0 8 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>0</SolutionTime>
<NoOfStoredNodes>6</NoOfStoredNodes>
<NoOfNodeExpansion>4</NoOfNodeExpansion>
<SolutionStep>3</SolutionStep>
<Node0>1,2,3,0,4,6,7,5,8</Node0>
<Node1>1,2,3,4,0,6,7,5,8</Node1>
<Node2>1,2,3,4,5,6,7,0,8</Node2>
<Node3>1,2,3,4,5,6,7,8,0</Node3>
```


### Teste #3
Gerado no SGAS com shuffle = 9.

Entradas: 
```text
1 2 3 0 5 7 4 8 6
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 59
Time to solve (ms): 3.094952
Solution steps: 9
Open list: 18
Closed list: 21

Path:

1 2 3 
0 5 7 
4 8 6 

1 2 3 
5 0 7 
4 8 6 

1 2 3 
5 7 0 
4 8 6 

1 2 3 
5 7 6 
4 8 0 

1 2 3 
5 7 6 
4 0 8 

1 2 3 
5 0 6 
4 7 8 

1 2 3 
0 5 6 
4 7 8 

1 2 3 
4 5 6 
0 7 8 

1 2 3 
4 5 6 
7 0 8 

1 2 3 
4 5 6 
7 8 0 
```

Saída no SGAS:
```xml
<SolutionTime>0</SolutionTime>
<NoOfStoredNodes>12</NoOfStoredNodes>
<NoOfNodeExpansion>13</NoOfNodeExpansion>
<SolutionStep>9</SolutionStep>
<Node0>1,2,3,0,5,7,4,8,6</Node0>
<Node1>1,2,3,5,0,7,4,8,6</Node1>
<Node2>1,2,3,5,7,0,4,8,6</Node2>
<Node3>1,2,3,5,7,6,4,8,0</Node3>
<Node4>1,2,3,5,7,6,4,0,8</Node4>
<Node5>1,2,3,5,0,6,4,7,8</Node5>
<Node6>1,2,3,0,5,6,4,7,8</Node6>
<Node7>1,2,3,4,5,6,0,7,8</Node7>
<Node8>1,2,3,4,5,6,7,0,8</Node8>
<Node9>1,2,3,4,5,6,7,8,0</Node9>
```


### Teste #4
Gerado no SGAS com shuffle = 27.

Entradas: 
```text
8 4 2 0 3 5 7 6 1
```
```text
1 2 3 4 5 6 7 8 0
```

Saída:
```text
Number of states needed to solve (expanded nodes): 1420
Time to solve (ms): 24.33326
Solution steps: 21
Open list: 308
Closed list: 531

Path:

8 4 2 
0 3 5 
7 6 1 

8 4 2 
7 3 5 
0 6 1 

8 4 2 
7 3 5 
6 0 1 

8 4 2 
7 3 5 
6 1 0 

8 4 2 
7 3 0 
6 1 5 

8 4 2 
7 0 3 
6 1 5 

8 4 2 
7 1 3 
6 0 5 

8 4 2 
7 1 3 
0 6 5 

8 4 2 
0 1 3 
7 6 5 

8 4 2 
1 0 3 
7 6 5 

8 0 2 
1 4 3 
7 6 5 

0 8 2 
1 4 3 
7 6 5 

1 8 2 
0 4 3 
7 6 5 

1 8 2 
4 0 3 
7 6 5 

1 0 2 
4 8 3 
7 6 5 

1 2 0 
4 8 3 
7 6 5 

1 2 3 
4 8 0 
7 6 5 

1 2 3 
4 8 5 
7 6 0 

1 2 3 
4 8 5 
7 0 6 

1 2 3 
4 0 5 
7 8 6 

1 2 3 
4 5 0 
7 8 6 

1 2 3 
4 5 6 
7 8 0  
```

Saída no SGAS:
```xml
<SolutionTime>9</SolutionTime>
<NoOfStoredNodes>308</NoOfStoredNodes>
<NoOfNodeExpansion>525</NoOfNodeExpansion>
<SolutionStep>21</SolutionStep>
<Node0>8,4,2,0,3,5,7,6,1</Node0>
<Node1>8,4,2,7,3,5,0,6,1</Node1>
<Node2>8,4,2,7,3,5,6,0,1</Node2>
<Node3>8,4,2,7,3,5,6,1,0</Node3>
<Node4>8,4,2,7,3,0,6,1,5</Node4>
<Node5>8,4,2,7,0,3,6,1,5</Node5>
<Node6>8,4,2,7,1,3,6,0,5</Node6>
<Node7>8,4,2,7,1,3,0,6,5</Node7>
<Node8>8,4,2,0,1,3,7,6,5</Node8>
<Node9>0,4,2,8,1,3,7,6,5</Node9>
<Node10>4,0,2,8,1,3,7,6,5</Node10>
<Node11>4,1,2,8,0,3,7,6,5</Node11>
<Node12>4,1,2,0,8,3,7,6,5</Node12>
<Node13>0,1,2,4,8,3,7,6,5</Node13>
<Node14>1,0,2,4,8,3,7,6,5</Node14>
<Node15>1,2,0,4,8,3,7,6,5</Node15>
<Node16>1,2,3,4,8,0,7,6,5</Node16>
<Node17>1,2,3,4,8,5,7,6,0</Node17>
<Node18>1,2,3,4,8,5,7,0,6</Node18>
<Node19>1,2,3,4,0,5,7,8,6</Node19>
<Node20>1,2,3,4,5,0,7,8,6</Node20>
<Node21>1,2,3,4,5,6,7,8,0</Node21>
```