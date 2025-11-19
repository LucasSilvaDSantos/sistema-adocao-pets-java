package dominio;

public enum PetTipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String descricao;

    PetTipo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
