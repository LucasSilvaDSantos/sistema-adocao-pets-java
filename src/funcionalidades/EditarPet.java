package funcionalidades;

import dominio.Pet;
import repositorio.RepositorioArquivo;
import validacoes.Validacoes;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class EditarPet {
    public void editarPet(Scanner input, RepositorioArquivo repositorioArquivo, BuscarPet buscarPet, Validacoes validacoes) {
        System.out.println("\n--- EDITAR PET ---");
        System.out.println("-- Pets Encontrados ---");

        ArrayList<Pet> resultados = PrintMenu.printListaPets();

        if (resultados.isEmpty()) {
            return;
        }

        System.out.println("\nDigite o NÚMERO do pet que deseja editar: ");
        int index = validacoes.lerNValido(input) - 1;

        if (index < 0 || index >= resultados.size()) {
            System.out.println("Opção inválida! Retornando ao menu.");
            return;
        }

        Pet petSelecionado = resultados.get(index);

        System.out.println("\nEDITANDO: " + petSelecionado.getPetNome());
        System.out.println("O que você deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Idade");
        System.out.println("3 - Peso");
        System.out.println("4 - Raça");
        System.out.println("5 - Endereço");
        System.out.println("0 - Cancelar");

        int escolha = validacoes.lerNValido(input);

        if (escolha == 0) {
            System.out.println("Cancelando...");
            return;
        }

            switch (escolha) {
                case 1:
                    System.out.println("Qual o novo nome");
                    String novoNome = validacoes.lerNomeValido(input);
                    petSelecionado.setPetNome(novoNome);
                    break;
                case 2:
                    System.out.println("Qual a nova idade");
                    String novaIdade = validacoes.lerIdadeValida(input);
                    petSelecionado.setPetIdade(novaIdade);
                    break;
                case 3:
                    System.out.println("Qual a novo peso");
                    String novoPeso = validacoes.lerPesoValido(input);
                    petSelecionado.setPetPeso(novoPeso);
                    break;
                case 4:
                    System.out.println("Qual a nova raça");
                    String novaRaca = validacoes.lerNomeValido(input);
                    petSelecionado.setPetRaca(novaRaca);
                    break;
                case 5:
                    System.out.println("Opção de endereço ainda não implementada.");
                    return;
            }

            try {
                if (petSelecionado.getArquivoOrigem() != null) {
                    Files.deleteIfExists(petSelecionado.getArquivoOrigem());
                } else {
                    System.out.println("Aviso: Arquivo original não encontrado, criando um novo.");
                }

                repositorioArquivo.salvarPet(petSelecionado);

                System.out.println("\n Pet atualizado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
            }
    }

}
