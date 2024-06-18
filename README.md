<div align="center">
  <h1 align="center">Redes de computadores II com Java</h1>
</div>

<div align="justify">
Nessa repositório estão simulações de alguns algoritmos de roteamento usando representações visuais feitas em Javafx. Aqui estão os algoritmos de inundação, caminho mais curto, 
</div>

## Algoritmo de inundação
<div align="justify">
Nesse algoritmo, todos os roteadores, ao receberem pacotes, devem enviá-lo a todos os seus vizinhos. Aqui também foram propostas outras 3 alternativas a esse algoritmo. Na alternativa 2, os pacotes são enviados a todos os vizinhos exceto aquele que enviou o pacote a esse roteador. Na alternativa 3, além da limitação proposta na alternativa 2, o pacote possui um ttl (time to live) em que, se chegar a 0 os roteadores param de enviar esse pacote, diminuindo bastante a quantidade de pacotes enviados. Por fim, na quarta opção, foi adicionada uma variável que controla se o pacote chegou ao destino ou não, caso tenha chegado, os roteadores param de enviar pacotes.
</div>

## Algoritmo do caminho mais curto
<div align="justify">
Aqui está implementado o algoritmo de melhor caminho de Dijasktra. Dado um roteador remetente e um roteador destinatário dentro de um grafo com arestas que possuem peso, o algoritmo encontra o caminho de menor peso entre esses dois roteadores.
</div>

## Algoritmo por vetor de distância
<div align="justify">
Nesse algoritmo, cada roteador possui uma tabela de roteamento. Nessa tabela terá, para todos os roteadores do backbone, a distância para chegar a cada roteador e o vizinho para quem o pacote será enviado caso chegue um pacote. A priori, cada roteador possui informação apenas da distância a cada vizinho, então recebe de seus vizinhos suas próprias tabelas de roteamento. As distâncias para os outros roteadores é inicialmente infinito, mas ao receber as tebelas de roteamento de seus vizinhos o roteador atualiza sua tabela com os menores valores. Por fim, isso se repete até que todas as tabelas estejam completas.
</div>

## Ferramentas
- Linguagem: Java
- Interface: Javafx
- Style: SceneBuilder

## Para testar os projetos
<div align="justify">
Será necessária a instalação do Java e do Javafx. Procure o caminho da pasta "lib" do javafx e o copie. 

### Rode o programa
```shell
javac --module-path \"YourPath/lib\" --add-modules javafx.controls,javafx.fxml Principal.java
java --module-path \"YourPath/lib\" --add-modules javafx.controls,javafx.fxml Principal
```
</div>
