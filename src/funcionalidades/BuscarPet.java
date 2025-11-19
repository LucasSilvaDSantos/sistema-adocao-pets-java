package funcionalidades;


import dominio.Pet;
import dominio.PetEndereco;
import dominio.PetSexo;
import dominio.PetTipo;
import validacoes.Validacoes;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class BuscarPet {
    public ArrayList<Pet> buscarPet(Scanner input) {
        Path pathPet = Paths.get("petsCadastrados");
        File[] arquivos = pathPet.toFile().listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo encontrado.");
            return new ArrayList<>();
        }

        ArrayList<Pet> pets = new ArrayList<>();

        for (File arquivo : arquivos) {
            System.out.println("Arquivo encontrado: " + arquivo.getName());
        }

        System.out.println("\n--- LENDO CONTEÚDO DOS ARQUIVOS ---\n");

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

            System.out.println("Você deseja procurar por: 1 - Cachorro ou 2 - Gato?");
            int respostaDoUsuario = validacoes.lerDoisNumValidos(input);

            System.out.println("Escolha um critério para filtrar os pets:");
            opcoesCriterios();
            int escolha1 = validacoes.lerNValido(input);

            System.out.println("Escolha um segundo critério para filtrar os pets:");
            opcoesCriterios();
            int escolha2 = validacoes.lerNValido(input);

            PetTipo tipoEscolhido = null;
            if (respostaDoUsuario == 1) {
                tipoEscolhido = PetTipo.CACHORRO;
            } else if (respostaDoUsuario == 2){
                tipoEscolhido = PetTipo.GATO;
            } else {
                System.out.println("Opção inválida! Digite apenas 1 ou 2.");
                return new ArrayList<>();
            }

            String nomeBusca = null;
            int sexoBusca = 0;
            String idadeBusca = null;
            String pesoBusca = null;
            String racaBusca = null;
            String enderecoBusca = null;

            if(escolha1 == 1 || escolha2 == 1) {
                System.out.print("Digite o nome (ou parte) que deseja buscar: ");
                nomeBusca = input.nextLine().toLowerCase().trim();

                if (nomeBusca.isBlank()) {
                    System.out.println("Busca por nome ignorada.");
                    nomeBusca = null;
                }
            }
            PetSexo sexoEscolhido = null;
            if(escolha1 == 2 || escolha2 == 2) {
                System.out.println("Você deseja procurar por: 1 - Macho ou 2 - Fêmea?");
                sexoBusca = validacoes.lerDoisNumValidos(input);

                if (sexoBusca == 1) {
                    sexoEscolhido = PetSexo.MACHO;
                } else if (sexoBusca == 2){
                    sexoEscolhido = PetSexo.FEMEA;
                } else {
                    System.out.println("Opção inválida! Digite apenas 1 ou 2.");
                    return new ArrayList<>();
                }
            }

            if(escolha1 == 3 || escolha2 == 3) {
                System.out.println("Digite a idade do Pet que deseja buscar:");
                idadeBusca = validacoes.lerIdadeValida(input);
            }

            if(escolha1 == 4 || escolha2 == 4) {
                System.out.println("Digite o peso Pet que deseja buscar:");
                pesoBusca = validacoes.lerPesoValido(input);
            }

            if(escolha1 == 5 || escolha2 == 5) {
                System.out.println("Digite a raça (ou parte) do Pet que deseja buscar");
                racaBusca = input.nextLine().toLowerCase().trim();
                if (racaBusca.isBlank()) {
                    System.out.println("Busca por raça ignorada.");
                    racaBusca = null;
                }
            }

            if(escolha1 == 6 || escolha2 == 6) {
                System.out.println("Digite o Endereço (ou parte) do Pet que deseja buscar");
                enderecoBusca = input.nextLine().toLowerCase().trim();

                if (enderecoBusca.isBlank()) {
                    System.out.println("Busca por endereço ignorada.");
                    enderecoBusca = null;
                }
            }

        System.out.println("\n--- Buscando... ---");
        for (Pet pet : pets) {
            boolean petPassou = true;

            if (pet.getPetTipo() != tipoEscolhido) {
                petPassou = false;
            }

            if (petPassou && nomeBusca != null) {
                if (!pet.getPetNome().toLowerCase().contains(nomeBusca)) {
                    petPassou = false;
                }
            }

            if (petPassou && sexoEscolhido != null) {
                if (pet.getPetsexo() != sexoEscolhido) {
                    petPassou = false;
                }
            }

            if (petPassou && idadeBusca != null) {
                if (!pet.getPetIdade().equals(idadeBusca)) {
                    petPassou =false;
                }
            }

            if (petPassou && pesoBusca != null){
                if (!pet.getPetPeso().equals(pesoBusca)){
                    petPassou = false;
                }
            }

            if (petPassou && racaBusca != null) {
                if ((!pet.getPetRaca().toLowerCase().contains(racaBusca))) {
                    petPassou = false;
                }
            }

            if (petPassou && enderecoBusca != null) {
                String enderecoCompleto = pet.getPetEndereco().getRua() + ", " +
                                          pet.getPetEndereco().getNumCasa() + ", " +
                                          pet.getPetEndereco().getCidade();
                if (!enderecoCompleto.toLowerCase().contains(enderecoBusca)) {
                    petPassou = false;
                }
            }
                if (petPassou) {
                    petsFiltrados.add(pet);
                }
        }

        if (petsFiltrados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com esses critérios.");
        } else {
            System.out.println("\n--- Pets Encontrados ---");
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
        return petsFiltrados;
    }

    private static void opcoesCriterios(){
        System.out.println("1 - Nome ou Sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        System.out.println("0 - Nenhuma outra");
        System.out.print("Opção: ");
    }
}
