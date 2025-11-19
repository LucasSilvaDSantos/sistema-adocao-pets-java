# 🐾 Sistema de Adoção de Pets

![Java](https://img.shields.io/badge/Java-17-orange)
![Status](https://img.shields.io/badge/Status-Concluido-brightgreen)

> Desafio de programação focado em Orientação a Objetos e Manipulação de Arquivos, proposto por [Lucas Carrilho (@devmagro)](https://github.com/karilho).

## 📖 Sobre o Projeto

Este é um sistema de gerenciamento para um abrigo de animais, desenvolvido via **CLI (Interface de Linha de Comando)**. O objetivo principal é permitir o cadastro, busca, edição e exclusão de pets para adoção, salvando todas as informações em arquivos de texto (`.txt`), simulando um banco de dados.

O projeto foi desenvolvido aplicando conceitos de:
- **Orientação a Objetos (OO)**
- **Manipulação de arquivos e arrays**
- **Java IO**
- **Exceções**
- **Boas práticas de código**
- **File Systems**

---

## 🚀 Funcionalidades

O sistema permite que o usuário (dono do abrigo) realize as seguintes operações:

- **📄 Cadastrar Pet:** Responde a um formulário dinâmico e salva os dados do animal.
- **🔍 Buscar Pet:** Permite filtrar os animais cadastrados por múltiplos critérios (Tipo, Nome, Idade, Peso, Raça, Sexo).
- **✏️ Editar Pet:** Altera os dados de um animal já cadastrado (atualizando o arquivo automaticamente).
- **❌ Excluir Pet:** Remove o registro do animal do sistema.
- **📂 Persistência de Dados:** Cada pet é salvo em um arquivo `.txt` único na pasta `petsCadastrados`.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **IDE:** IntelliJ IDEA
- **Controle de Versão:** Git & GitHub

---

## 📂 Estrutura do Projeto

O código foi organizado seguindo boas práticas de separação de responsabilidades:

```text
src/
├── dados/             # Arquivos de recursos (formulario.txt)
├── dominio/           # Classes modelo (Pet, Endereco, Enums)
├── funcionalidades/   # Regras de negócio (Cadastrar, Buscar, Editar, Excluir)
├── main/              # Classe principal que inicia a aplicação
├── repositorio/       # Lógica de leitura e escrita de arquivos
└── validacoes/        # Utilitários para validar entradas do usuário