package ProjetoXadrezLPOO;

public class Cavalo extends Peca{

    public Cavalo(String corPeca) {
        //define o tipo e cor da peca
        super(corPeca, "cavalo");
        if(cor.equals("branco")){
            print = "  C  ";
        }
        else{
            print = "  c: ";
        }
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca) {
        //inicializa a posicao relativa de acordo com os valores passados nos parametros

        int posicaoRelativaX = moverPara[0]-moverDe[0];
        int posicaoRelativaY = moverPara[1]-moverDe[1];
        boolean valido = false;
        Posicao posicao = Jogo.posicao[moverPara[1]][moverPara[0]];

        if (posicaoRelativaX !=0 && posicaoRelativaY !=0) {//2 para frente ou trás e 1 para esquerda ou direita

            int movimento = posicaoRelativaX + posicaoRelativaY;

            switch (movimento){
                case 1 : valido = true;
                    break;
                case -1 : valido = true;
                    break;
                case 3 : valido = true;
                    break;
                case -3 : valido = true;
                    break;
                default : valido = false;
            }
        }
        if(valido){//exceto em captura e se a posicao desejada for vazia
            return posicao.tipo().equals("vazio") || (!posicao.cor().equals(corPeca));
        }
        return false;
    }
}