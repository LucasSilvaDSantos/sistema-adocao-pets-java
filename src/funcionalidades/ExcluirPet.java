package funcionalidades;

import dominio.Pet;
import repositorio.RepositorioArquivo;
import validacoes.Validacoes;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class ExcluirPet {
    public void excluirPet(Scanner input, RepositorioArquivo repositorioArquivo, Validacoes validacoes) {
        System.out.println("\n--- DELETAR PET ---");

        ArrayList<Pet> resultados = PrintMenu.printListaPets();

        if (resultados.isEmpty()) {
            return;
        }

        System.out.println("\nDigite o NÚMERO do pet que deseja deletar: ");

        int index = validacoes.lerNValido(input) - 1;

        if (index < 0 || index >= resultados.size()) {
            System.out.println("Opção inválida! Retornando ao menu.");
            return;
        }

        Pet petSelecionado = resultados.get(index);

        System.out.println("\nDELETANDO..: " + petSelecionado.getPetNome());

        System.out.println("Digite Sim para comfirmar e Não para cancelar");
        String confirmar = input.nextLine();

        try {
            if (confirmar.equalsIgnoreCase("SIM")) {
                Files.deleteIfExists(petSelecionado.getArquivoOrigem());
                System.out.println("Pet deletado com sucesso!");
            } else {
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
