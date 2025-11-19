package repositorio;

import dominio.Pet;
import dominio.PetEndereco;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RepositorioArquivo {
    static Path pathForm = Paths.get("src\\data\\formulario.txt");

    public void salvarPet(Pet pet) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHora = agora.format(formatador);
        String nome = pet.getPetNome();
        String nomeFormatado = nome.toUpperCase().replace(" ", "");
        String nomeDoArquivo = dataHora + "-" + nomeFormatado + ".TXT";


            Path diretorio = Paths.get("petsCadastrados");
            Path caminhoDoArquivo = diretorio.resolve(nomeDoArquivo);

            if (Files.exists(diretorio)) {
                System.out.println("Pet Cadastrado com sucesso");
            } else {
                System.out.println("Falha Pet não foi Cadastrado!!!");
            }

        try {
            Files.createDirectories(diretorio);

            try (BufferedWriter writer = Files.newBufferedWriter(caminhoDoArquivo)) {

                writer.write("1 - " + pet.getPetNome());
                writer.newLine();
                writer.write("2 - " + pet.getPetTipo().toString());
                writer.newLine();
                writer.write("3 - " + pet.getPetsexo().toString());
                writer.newLine();

                PetEndereco fim = pet.getPetEndereco();
                String enderecoFormatado = fim.getRua() + ", " + fim.getNumCasa() + ", " + fim.getCidade();
                writer.write("4 - " + enderecoFormatado);
                writer.newLine();
                writer.write("5 - " + pet.getPetIdade() + " anos");
                writer.newLine();
                writer.write("6 - " + pet.getPetPeso() + " kg");
                writer.newLine();
                writer.write("7 - " + pet.getPetRaca());
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar o pet: " + e.getMessage());
            }
    }

    public void createFile() {
        File file = new File(String.valueOf(pathForm.toAbsolutePath()));
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)\n3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n5 - Qual a idade aproximada do pet?\n6 - Qual o peso aproximado do pet?\n7 - Qual a raça do pet?");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() {

            try (BufferedReader br = new BufferedReader(new FileReader(pathForm.toFile()))){
            String line;
            while ((line = br.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readSpecifyLineFile(int line) {
        File file = new File(String.valueOf(pathForm.toAbsolutePath()));
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)){
            String linha;
            int cont = 1;
            while ((linha = br.readLine()) != null) {
                if (cont == line) {
                    System.out.println(linha);
                    return linha;
                }
                cont++;
            }
            return linha;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
