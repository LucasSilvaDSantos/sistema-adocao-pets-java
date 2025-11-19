package dominio;

public enum PetSexo {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private final String descricao;

    PetSexo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
