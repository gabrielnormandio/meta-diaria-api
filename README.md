# 🚴‍♂️ Meta Diária API — Planejador Financeiro para Motoristas de Aplicativo

API RESTful desenvolvida em Java com Spring Boot para auxiliar motoristas e entregadores de aplicativo (Uber, 99, iFood) a planejarem e acompanharem suas metas financeiras mensais com **recálculo dinâmico diário**.

---

## 📌 Sobre o Projeto

Trabalhar de forma autônoma exige controle financeiro e flexibilidade. O **Meta Diária API** resolve o problema de imprevisibilidade de ganhos diários. O usuário define uma meta mensal e seleciona no calendário os dias em que pretende trabalhar. A partir do registro diário de ganhos brutos e despesas (combustível, alimentação, manutenção), o sistema **recalcula automaticamente a meta necessária para os dias restantes**.

* **Superávit (fez a mais):** O valor excedente reduz a meta diária dos dias futuros.
* **Déficit (fez a menos):** A diferença é redistribuída suavemente entre os dias restantes.
* **Meta Atingida Antecipadamente:** Os dias restantes têm meta reduzida a R$ 0,00 e os novos ganhos passam a contabilizar como **lucro extra**.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security, Spring Validation)
* **Banco de Dados:** PostgreSQL
* **Versionamento de Banco:** Flyway
* **Segurança:** Authentication via Tokens JWT (JSON Web Token)
* **Documentação da API:** OpenAPI 3 / Swagger
* **Testes:** JUnit 5 & Mockito

---

## 📂 Documentação da Engenharia do Projeto

Toda a fase de planejamento, arquitetura e requisitos da aplicação está organizada na pasta [`/docs`](./docs):

* 📄 [**Especificação de Requisitos**](./docs/01-especificacao-requisitos.md): Visão geral, atores, requisitos funcionais (RF01 a RF52) e não-funcionais (RNF01 a RNF10).
* 📄 [**Regras de Negócio e Validações**](./docs/02-regras-de-negocio-e-validacoes.md): Fórmulas matemáticas, tratamento de histórico, regras de domínio (RN01 a RN18) e validações de dados (RV01 a RV06).

---

## 🛠️ Regra de Negócio Principal (Algoritmo de Recálculo)

$$\text{Resultado Líquido do Dia} = \text{Ganho Bruto} - \sum \text{Saídas/Despesas}$$

$$\text{Nova Meta Diária} = \frac{\text{Meta Mensal} - \text{Total Realizado Acumulado}}{\text{Quantidade de Dias Restantes}}$$

---

## 👨‍💻 Autor

Desenvolvido por **Gabriel** — Estudante de Análise e Desenvolvimento de Sistemas (4º Período).

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/gabrielnormandio/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/gabrielnormandio)
