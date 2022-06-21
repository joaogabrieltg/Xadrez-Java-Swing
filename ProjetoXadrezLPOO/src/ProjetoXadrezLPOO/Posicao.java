package ProjetoXadrezLPOO;

public abstract class Posicao implements IPosicao{
    //essa classe define os elementos do tabuleiro

    protected String print;
    public String cor;
    public String tipo;
    public boolean mexeu;

    public Posicao(String tipoRecebido){
        this.tipo = tipoRecebido;
    }

    @Override
    public boolean isMexeu(){
        return mexeu;
    }

    @Override
    public String print(){
        return print;
    }

    @Override
    public String cor(){
        return cor;
    }

    @Override
    public String tipo(){
        return tipo;
    }

    @Override
    public abstract boolean movimento(int[] moverDe, int[] moverPara, String corPeca);
}