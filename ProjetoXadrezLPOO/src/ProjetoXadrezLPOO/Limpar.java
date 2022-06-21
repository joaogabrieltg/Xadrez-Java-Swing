package ProjetoXadrezLPOO;

public class Limpar extends Posicao {
    //essa classe define os espacos vazios no tabuleiro

    public Limpar() {
        super("vazio");
        print = "     ";
        cor = null;
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){
        return false;
    }
}
