package dominio;


import java.nio.file.Path;

public class Pet {
    private String petNome;
    private PetTipo petTipo;
    private PetSexo petsexo;
    private PetEndereco petEndereco;
    private String petIdade;
    private String petPeso;
    private String petRaca;
    public static final String NAO_INFORMADO = "Nao Informado";

    public String getPetNome() {
        return petNome;
    }

    public String setPetNome(String petNome) {
        if (petNome == null || petNome.isBlank()) {
            this.petNome = NAO_INFORMADO;
            return this.petNome;
        }

        if (!petNome.contains(" ")) {
            throw new IllegalArgumentException("Argumento Inválido! O pet deve ter nome e sobrenome.");
        }

        if (!petNome.matches("[A-Za-zÀ-ÿ ]+")) {
            throw new IllegalArgumentException("Nome contém caracteres inválidos. Use apenas letras e espaços.");
        }

        this.petNome = petNome.trim();
        return this.petNome;
    }

    public PetTipo getPetTipo() {
        return petTipo;
    }

    public void setTipoEscolher(int escolha) {
        if (escolha == 1){
            setPetTipo((PetTipo.CACHORRO));
        } if (escolha == 2) {
            setPetTipo((PetTipo.GATO));
        }
    }

    public void setPetTipo(PetTipo petTipo) {
        this.petTipo = petTipo;
    }

    public PetSexo getPetsexo() {
        return petsexo;
    }

    public void setSexoEscolher(int escolha) {
        if (escolha == 1){
            setPetsexo((PetSexo.MACHO));
        } if (escolha == 2) {
            setPetsexo((PetSexo.FEMEA));
        }
    }

    public void setPetsexo(PetSexo petsexo) {
        this.petsexo = petsexo;
    }

    public PetEndereco getPetEndereco() {
        return petEndereco;
    }

    public void setPetEndereco(PetEndereco petEndereco) {
        this.petEndereco = petEndereco;
    }

    public String getPetIdade() {
        return petIdade;
    }

    public void setPetIdade(String petIdade) {
        this.petIdade = petIdade;
    }

    public String getPetPeso() {
        return petPeso;
    }

    public void setPetPeso(String petPeso) {
        this.petPeso = petPeso;
    }

    public String getPetRaca() {
        return petRaca;
    }

    public String setPetRaca(String petRaca) {
        if (petRaca == null || petRaca.isBlank()) {
            this.petRaca = NAO_INFORMADO;
            return this.petRaca;
        }

        if (!petRaca.matches("[A-Za-zÀ-ÿ ]+")) {
            throw new IllegalArgumentException("Raça contém caracteres inválidos. Use apenas letras e espaços.");
        }

        this.petRaca = petRaca.trim();
        return petRaca;
    }

    private Path arquivoOrigem;

    public Path getArquivoOrigem() {
        return arquivoOrigem;
    }

    public void setArquivoOrigem(Path arquivoOrigem) {
        this.arquivoOrigem = arquivoOrigem;
    }
}