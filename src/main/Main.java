package main;

import dominio.Pet;
import repositorio.RepositorioArquivo;
import funcionalidades.*;
import validacoes.Validacoes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Validacoes validacoes = new Validacoes();
        PrintMenu printMenu = new PrintMenu();
        RepositorioArquivo fr = new RepositorioArquivo();
        fr.readFile();
        Scanner scanner = new Scanner(System.in);
        BuscarPet buscarPet = new BuscarPet();
        CadastrarPet cadastrarPet = new CadastrarPet();
        EditarPet editarPet = new EditarPet();
        ExcluirPet excluirPet = new ExcluirPet();
        Pet pet = new Pet();

        int option = 0;

        while (option != 6) {
            option = printMenu.mostrarMenuPrincipal(scanner);

            switch (option) {
                case 1:
                    cadastrarPet.cadastrarPet(scanner, fr, validacoes);
                    break;
                case 2:
                    editarPet.editarPet(scanner, fr, buscarPet, validacoes);
                    break;
                case 3:
                    excluirPet.excluirPet(scanner, fr, buscarPet, validacoes );
                    break;
                case 4:
                        PrintMenu.printListaPets();
                    break;
                case 5:
                    buscarPet.buscarPet(scanner);
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;
            }
        }
            scanner.close();
    }
}