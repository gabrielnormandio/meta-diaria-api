\# 🗄️ **Modelagem do Banco de Dados (DER)**



Estrutura de dados relacional (PostgreSQL) projetada para a API \*\*Meta Diária\*\*.



\---



\## 📐 Diagrama Relacional (Visão Geral)



\* \*\*tb\_usuarios\*\* (1) ───< (N) \*\*tb\_planejamentos\_mensais\*\*

\* \*\*tb\_planejamentos\_mensais\*\* (1) ───< (N) \*\*tb\_dias\_planejados\*\*

\* \*\*tb\_dias\_planejados\*\* (1) ───< (N) \*\*tb\_saidas\_financeiras\*\*



\---



\## 📋 Detalhamento das Tabelas



\### 1. Tabela: `tb\_usuarios`

Guarda as credenciais de acesso e preferências do usuário.



| Coluna | Tipo SQL | Restrições | Descrição |

| :--- | :--- | :--- | :--- |

| `id` | `BIGSERIAL` | `PRIMARY KEY` | Identificador único do usuário |

| `nome` | `VARCHAR(100)` | `NOT NULL` | Nome do usuário |

| `email` | `VARCHAR(150)` | `NOT NULL, UNIQUE` | E-mail para login |

| `senha` | `VARCHAR(255)` | `NOT NULL` | Hash da senha (BCrypt) |

| `horario\_notificacao` | `TIME` | `NULLABLE` | Horário da notificação diária |

| `criado\_em` | `TIMESTAMP` | `NOT NULL` | Data/hora de cadastro |



\---



\### 2. Tabela: `tb\_planejamentos\_mensais`

Representa a meta financeira global para determinado mês.



| Coluna | Tipo SQL | Restrições | Descrição |

| :--- | :--- | :--- | :--- |

| `id` | `BIGSERIAL` | `PRIMARY KEY` | Identificador único |

| `usuario\_id` | `BIGINT` | `FOREIGN KEY (tb\_usuarios.id)` | Dono do planejamento |

| `mes\_ano` | `DATE` | `NOT NULL` | Primeiro dia do mês (ex: 2026-09-01) |

| `meta\_mensal` | `NUMERIC(10,2)` | `NOT NULL, CHECK (meta\_mensal > 0)` | Meta financeira do mês |

| `criado\_em` | `TIMESTAMP` | `NOT NULL` | Data de criação |



> \*\*Unicidade (Constraint):\*\* `UNIQUE (usuario\_id, mes\_ano)` — Garante apenas 1 planejamento por mês para cada usuário (RN01).



\---



\### 3. Tabela: `tb\_dias\_planejados`

Registra cada dia do mês, indicando os dias de trabalho, metas recalculadas e ganho bruto.



| Coluna | Tipo SQL | Restrições | Descrição |

| :--- | :--- | :--- | :--- |

| `id` | `BIGSERIAL` | `PRIMARY KEY` | Identificador único |

| `planejamento\_id` | `BIGINT` | `FOREIGN KEY (tb\_planejamentos\_mensais.id)` | Planejamento pai |

| `data` | `DATE` | `NOT NULL` | Data do dia |

| `eh\_dia\_trabalho` | `BOOLEAN` | `NOT NULL` | `true` para trabalho, `false` para folga |

| `meta\_diaria\_atual` | `NUMERIC(10,2)` | `NOT NULL` | Meta recalculada para este dia |

| `ganho\_bruto` | `NUMERIC(10,2)` | `DEFAULT 0.00` | Valor bruto informado |

| `status` | `VARCHAR(20)` | `NOT NULL` | `PENDENTE`, `CONCLUIDO`, `FOLGA` |



\---



\### 4. Tabela: `tb\_saidas\_financeiras`

Registra cada despesa individual realizada em determinado dia.



| Coluna | Tipo SQL | Restrições | Descrição |

| :--- | :--- | :--- | :--- |

| `id` | `BIGSERIAL` | `PRIMARY KEY` | Identificador único |

| `dia\_planejado\_id` | `BIGINT` | `FOREIGN KEY (tb\_dias\_planejados.id)` | Dia a que se refere |

| `descricao` | `VARCHAR(100)` | `NOT NULL` | Ex: Gasolina, Almoço, Manutenção |

| `valor` | `NUMERIC(10,2)` | `NOT NULL, CHECK (valor > 0)` | Valor da despesa |

