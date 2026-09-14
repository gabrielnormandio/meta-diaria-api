**1. Regras de negócio**

Agora vamos separar requisito funcional de regra de negócio.



RN01

Cada usuário poderá possuir somente um planejamento por mês.



RN02

Um planejamento deve possuir pelo menos um dia de trabalho.



RN03

A meta mensal deve ser maior que R$ 0,00.



RN04

O resultado líquido será:



Ganho bruto − Total de saídas.



RN05

O ganho poderá ser R$ 0,00.



RN06

As saídas poderão ser superiores ao ganho.



RN07

O resultado líquido poderá ser negativo.



RN08

A meta diária inicial será calculada dividindo a meta mensal pela quantidade de dias planejados.



RN09

A meta diária é uma referência para acompanhamento e poderá sofrer alterações durante o mês.



RN10

O atingimento da meta será determinado pelo resultado acumulado em relação à meta mensal.



RN11

A meta será considerada atingida quando o resultado acumulado for maior ou igual à meta mensal.



RN12

O usuário poderá continuar trabalhando após atingir a meta.



RN13

Valores obtidos após o atingimento da meta deverão continuar sendo registrados.



RN14

O valor que exceder a meta mensal deverá ser considerado extra.



RN15

Resultados anteriores não poderão ser contabilizados duas vezes após uma edição.



RN16

Alterações no planejamento não deverão apagar registros financeiros já realizados.



RN17

Um dia retirado do planejamento não deverá apagar seu histórico financeiro, caso exista.



RN18

Um dia adicionado ao planejamento deverá ser considerado no cálculo dos dias restantes, quando aplicável.



**2. Requisitos de validação**

Também precisamos registrar o que o sistema deve impedir.



RV01

Não permitir planejamento sem mês.



RV02

Não permitir meta igual ou inferior a zero.



RV03

Não permitir planejamento sem nenhum dia selecionado.



RV04

Não permitir dois planejamentos do mesmo usuário para o mesmo mês.



RV05

Não permitir usuário acessar dados pertencentes a outro usuário.



RV06

Valores monetários devem possuir tratamento adequado para casas decimais.

