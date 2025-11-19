package funcionalidades;

import dominio.Pet;
import dominio.PetEndereco;
import repositorio.RepositorioArquivo;
import validacoes.Validacoes;

import java.util.Scanner;

public class CadastrarPet {
    public void cadastrarPet(Scanner input, RepositorioArquivo repositorioArquivo, Validacoes validacoes){
        Pet pet = new Pet();
        PetEndereco petEndereco = new PetEndereco();

        RepositorioArquivo.readSpecifyLineFile(1);
        pet.setPetNome(input.nextLine());


        RepositorioArquivo.readSpecifyLineFile(2);
        System.out.println("1 = Cachorro | 2 = Gato");
        pet.setTipoEscolher(validacoes.lerDoisNumValidos(input));

        RepositorioArquivo.readSpecifyLineFile(3);
        System.out.println("1 = Macho | 2 = Fêmea");
        pet.setSexoEscolher(validacoes.lerDoisNumValidos(input));

        RepositorioArquivo.readSpecifyLineFile(4);
        System.out.println("Numero da casa: ");
        petEndereco.setNumCasa(validacoes.lerNumeroDeCasaValido(input));

        System.out.println("Cidade: ");
        petEndereco.setCidade(input.nextLine());

        System.out.println("Rua: ");
        petEndereco.setRua(input.nextLine());

        pet.setPetEndereco(petEndereco);

        RepositorioArquivo.readSpecifyLineFile(5);
        pet.setPetIdade(validacoes.lerIdadeValida(input));

        RepositorioArquivo.readSpecifyLineFile(6);
        pet.setPetPeso(validacoes.lerPesoValido(input));

        RepositorioArquivo.readSpecifyLineFile(7);
        pet.setPetRaca(input.nextLine());

        repositorioArquivo.salvarPet(pet);
    }
}