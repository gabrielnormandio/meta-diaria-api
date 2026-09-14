**1. Visão geral**



O sistema permitirá que o usuário planeje quanto deseja obter financeiramente em determinado mês, selecione os dias em que pretende trabalhar e acompanhe diariamente sua evolução.



O sistema calculará dinamicamente a meta necessária para os dias restantes, considerando os resultados financeiros já registrados.



O resultado diário será calculado a partir de:



Ganho bruto − Saídas = Resultado líquido do dia



O sistema também permitirá alterações no planejamento durante o mês, refletindo situações reais como trabalhar em um dia inicialmente não planejado ou deixar de trabalhar em um dia planejado.



**2. Atores**

Usuário



Pessoa que utiliza o sistema para:



criar planejamentos;

definir metas;

selecionar dias de trabalho;

registrar ganhos;

registrar despesas;

acompanhar progresso;

alterar planejamentos;

consultar histórico.



Sistema



Responsável por:



calcular metas;

recalcular metas;

controlar os dias planejados;

calcular resultados;

identificar cumprimento da meta;

enviar lembretes;

manter o histórico.



**3. Requisitos funcionais**

Autenticação



RF01 — Cadastro

O sistema deve permitir que um novo usuário crie uma conta.



RF02 — Login

O sistema deve permitir que o usuário autenticado acesse sua conta.



RF03 — Logout

O sistema deve permitir que o usuário encerre sua sessão.



RF04 — Proteção dos dados

O usuário somente poderá acessar seus próprios planejamentos e registros financeiros.



**4. Planejamento mensal**



RF05 — Criar planejamento

O usuário deve poder criar um planejamento para determinado mês.



RF06 — Selecionar mês

O usuário deve poder escolher o mês e ano do planejamento.



RF07 — Definir meta mensal

O usuário deve informar quanto deseja alcançar naquele mês.



RF08 — Selecionar dias

O sistema deve apresentar um calendário para que o usuário selecione os dias em que pretende trabalhar.



RF09 — Calcular meta diária inicial

O sistema deve calcular a meta diária inicial:



Meta mensal ÷ quantidade de dias planejados.



RF10 — Visualizar planejamento

O usuário deve poder visualizar o planejamento mensal através do calendário.



RF11 — Um planejamento por mês

O sistema não deve permitir que o mesmo usuário possua mais de um planejamento para o mesmo mês.



**5. Alteração do planejamento**



RF12 — Adicionar dia

O usuário poderá adicionar ao planejamento um dia que inicialmente não havia selecionado.



RF13 — Remover dia

O usuário poderá retirar do planejamento um dia que havia selecionado.



RF14 — Recalcular após alteração

Sempre que houver alteração nos dias planejados, o sistema deverá recalcular a meta dos dias restantes quando aplicável.



RF15 — Preservar histórico

Alterações futuras no planejamento não devem apagar registros financeiros que já foram realizados.



**6. Registro financeiro**



RF16 — Registrar ganho

O usuário deve poder informar quanto recebeu em determinado dia.



RF17 — Registrar saída

O usuário deve poder informar valores gastos durante o dia.



RF18 — Múltiplas saídas

O usuário poderá registrar mais de uma saída no mesmo dia.



Exemplo:



Combustível     R$ 40

Alimentação     R$ 15

Manutenção      R$ 20



RF19 — Total de saídas

O sistema deve calcular automaticamente o total das saídas daquele dia.



RF20 — Calcular resultado líquido

O sistema deve calcular:



Ganho bruto − total das saídas.



RF21 — Resultado negativo

O sistema deve permitir resultado negativo.



Exemplo:



Ganho:   R$ 100

Saídas:  R$ 150

Resultado: -R$ 50



RF22 — Ganho zero

O sistema deve permitir que o usuário registre ganho de R$ 0,00.



RF23 — Editar resultado

O usuário poderá alterar um resultado já registrado.



RF24 — Recalcular após edição

Quando um resultado for alterado, o sistema deverá recalcular o impacto daquele resultado sobre o planejamento.



Importante: o cálculo deverá considerar o novo resultado e não acumular o efeito do registro anterior.



**7. Meta dinâmica**



Essa é uma das partes centrais do projeto.



RF25 — Comparar resultado com meta

O sistema deverá comparar o resultado líquido do dia com a meta diária vigente.



RF26 — Resultado acima da meta

Quando o resultado superar a meta diária, o excedente deverá reduzir a necessidade dos dias planejados restantes.



RF27 — Resultado abaixo da meta

Quando o resultado ficar abaixo da meta diária, a diferença deverá ser distribuída entre os dias planejados restantes.



RF28 — Recalcular meta dos dias restantes

Após cada resultado registrado, o sistema deverá atualizar a meta necessária para os próximos dias.



RF29 — Considerar somente dias restantes

Dias já realizados não devem receber uma nova meta.



RF30 — Meta mensal como referência principal

O atingimento da meta será determinado pelo total realizado em comparação com a meta mensal.



RF31 — Meta atingida

O sistema deverá identificar quando:



Resultado acumulado ≥ Meta mensal.



RF32 — Continuar registrando após atingir a meta

Mesmo depois de atingir a meta, o usuário poderá continuar registrando seus resultados.



RF33 — Registrar valor extra

Valores realizados acima da meta mensal deverão ser apresentados como valor extra.



**8. Situações especiais de planejamento**



RF34 — Trabalhar em dia não planejado

O usuário poderá decidir trabalhar em um dia que inicialmente não estava planejado.



RF35 — Incluir dia trabalhado

O usuário deverá poder incluir esse dia no planejamento.



RF36 — Não trabalhar em dia planejado

O usuário poderá informar que não irá trabalhar em um dia que havia planejado.



RF37 — Redistribuir meta

Quando um dia planejado for retirado, a necessidade daquele dia deverá ser redistribuída entre os demais dias aplicáveis.



**9. Calendário**



RF38 — Exibir calendário mensal

O sistema deverá apresentar os dias do mês em formato de calendário.



RF39 — Identificar dias planejados

O calendário deverá diferenciar visualmente os dias planejados.



RF40 — Identificar dias realizados

O calendário poderá indicar dias que já possuem registro financeiro.



RF41 — Identificar dias sem registro

O sistema poderá indicar dias planejados que ainda não possuem resultado registrado.



**10. Lembretes**



RF42 — Configurar horário

O usuário poderá definir o horário do lembrete diário.



RF43 — Verificar planejamento

O sistema deverá verificar se o dia atual está planejado para trabalho.



RF44 — Enviar lembrete

Caso o dia esteja planejado, o sistema deverá solicitar ao usuário o registro do resultado.



RF45 — Não enviar em dia não planejado

O sistema não deverá enviar o lembrete quando o dia não fizer parte do planejamento.



**11. Consultas e acompanhamento**



RF46 — Exibir progresso mensal

O usuário deverá conseguir visualizar:



meta mensal;

valor realizado;

valor restante;

percentual alcançado;

quantidade de dias planejados;

quantidade de dias restantes;

meta atual por dia.



RF47 — Exibir resultado diário

O usuário poderá consultar o resultado de cada dia.



RF48 — Exibir ganhos

O sistema deverá apresentar os ganhos registrados.



RF49 — Exibir saídas

O sistema deverá apresentar as saídas registradas.



RF50 — Exibir resultado líquido

O sistema deverá apresentar o resultado líquido de cada dia.



RF51 — Histórico mensal

O usuário poderá consultar planejamentos de meses anteriores.



RF52 — Histórico financeiro

O usuário poderá consultar os registros financeiros anteriores.





**12. Requisitos não funcionais**

Agora entramos em coisas que não são funcionalidades, mas definem como o sistema deve funcionar.



RNF01 — Segurança

Senhas não devem ser armazenadas em texto puro.



RNF02 — Autenticação

Operações privadas deverão exigir autenticação.



RNF03 — Integridade

Os cálculos financeiros devem preservar precisão monetária.



RNF04 — Disponibilidade

O sistema deverá estar disponível para utilização através da internet.



RNF05 — Responsividade

A interface deverá funcionar adequadamente em computador e celular.



Isso é especialmente importante porque você provavelmente vai acessar o sistema pelo celular enquanto estiver trabalhando.



RNF06 — Usabilidade

O registro diário deve exigir o mínimo possível de informações do usuário.



RNF07 — Manutenibilidade

O sistema deverá possuir uma arquitetura organizada para facilitar manutenção e evolução.



RNF08 — API

A aplicação deverá disponibilizar uma API REST para comunicação entre frontend e backend.



RNF09 — Persistência

Os dados deverão permanecer armazenados após o encerramento da aplicação.



RNF10 — Escalabilidade

A arquitetura deverá permitir evolução futura sem necessidade de reescrever completamente o sistema.

