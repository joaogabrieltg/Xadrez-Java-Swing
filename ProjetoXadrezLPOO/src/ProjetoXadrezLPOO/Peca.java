package ProjetoXadrezLPOO;

public abstract class Peca extends Posicao {
    //essa classe define as pecas
    public Peca(String corPeca, String tipoRecebido) {
        super(tipoRecebido);
        cor = corPeca;
    }
}