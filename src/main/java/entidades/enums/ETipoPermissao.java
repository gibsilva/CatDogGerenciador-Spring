package entidades.enums;

public enum ETipoPermissao {
    Adminstrador(1), Auxiliar(2);

    private final int opcao;

    ETipoPermissao(int opcao) {
        this.opcao = opcao;
    }

    public int getOpcao() {
        return this.opcao;
    }

    public static ETipoPermissao fromInt(int x) {
        switch (x) {
            case 1:
                return Adminstrador;
            case 2:
                return Auxiliar;
        }
        return null;
    }

}