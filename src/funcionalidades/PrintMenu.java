package funcionalidades;

import dominio.Pet;
import dominio.PetEndereco;
import dominio.PetSexo;
import dominio.PetTipo;
import validacoes.Validacoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PrintMenu {
    public int mostrarMenuPrincipal(Scanner input) {
            System.out.println("\n1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sair");
            int escolha = numEntreUmESeis(input);
            return escolha;
    }

    public static int numEntreUmESeis(Scanner input) {
        while (true) {
            System.out.println("Digite um número de 1 a 6: ");

            if (input.hasNextInt()) {
               int num = input.nextInt();
               input.nextLine();
               if (num >= 1 && num <= 6) {
                   return num;
               } else {
                   System.out.println("Opção inválida! Digite um número de 1 a 6: ");
               }
            } else {
                System.out.println("Opção inválida! Digite apenas números");
                input.next();
            }
        }
    }

    public static ArrayList<Pet> printListaPets() {

        Path pathPet = Paths.get("petsCadastrados");
        File[] arquivos = pathPet.toFile().listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo encontrado.");
            return null;
        }

        ArrayList<Pet> pets = new ArrayList<>();

        for (File arquivo : arquivos) {

        }


        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                Pet pet = new Pet();
                PetEndereco petEndereco = new PetEndereco();

                String nome = br.readLine().split(" - ")[1]; // 1 - lulu da silva
                String tipo = br.readLine().split(" - ")[1]; // 2 - Gato
                String sexo = br.readLine().split(" - ")[1]; // 3 - Fêmea
                String endereco = br.readLine().split(" - ")[1]; // 4 - av argentina, 5, sertão
                String idade = br.readLine().split(" - ")[1]; // 5 - 5.0 anos
                String peso = br.readLine().split(" - ")[1]; //6 - 6.0 kg
                String raca = br.readLine().split(" - ")[1] ; // 7 - Alux

                String idadeLimpa = idade.split(" ")[0];
                String pesoLimpo = peso.split(" ")[0];

                String enderecoPartes[] = endereco.split(",");
                pet.setPetNome(nome);
                if (tipo.equalsIgnoreCase("Gato")) {
                    pet.setPetTipo(PetTipo.GATO);
                } else if (tipo.equalsIgnoreCase("Cachorro")) {
                    pet.setPetTipo(PetTipo.CACHORRO);
                }
                if (sexo.equalsIgnoreCase("Fêmea")) {
                    pet.setPetsexo(PetSexo.FEMEA);
                } else if (sexo.equalsIgnoreCase("Macho")) {
                    pet.setPetsexo(PetSexo.MACHO);
                }
                petEndereco.setRua(enderecoPartes[0].trim());
                petEndereco.setNumCasa(enderecoPartes[1].trim());
                petEndereco.setCidade(enderecoPartes[2].trim());
                pet.setPetEndereco(petEndereco);
                pet.setPetIdade(idadeLimpa);
                pet.setPetPeso(pesoLimpo);
                pet.setPetRaca(raca);

                pet.setArquivoOrigem(arquivo.toPath());

                pets.add(pet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Validacoes validacoes = new Validacoes();
        ArrayList<Pet> petsFiltrados = new ArrayList<>();

        String nomeBusca = null;
        int sexoBusca = 0;
        String idadeBusca = null;
        String pesoBusca = null;
        String racaBusca = null;
        String enderecoBusca = null;

        for (Pet pet : pets) {

                petsFiltrados.add(pet);
            }
        if (petsFiltrados.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
        } else {
            System.out.println("--- Listando Todos Os Pets ---");
            int count = 1;

            for (Pet p : petsFiltrados) {
                String enderecoFormatado = p.getPetEndereco().getRua() + ", " +
                        p.getPetEndereco().getNumCasa() + ", " +
                        p.getPetEndereco().getCidade();
                System.out.printf("%d. %s - %s - %s - %s - %s anos - %s kg - %s\n",
                        count++,
                        p.getPetNome(),
                        p.getPetTipo(),
                        p.getPetsexo(),
                        enderecoFormatado,
                        p.getPetIdade(),
                        p.getPetPeso(),
                        p.getPetRaca());
            }
        }
        return pets;
    }
}
