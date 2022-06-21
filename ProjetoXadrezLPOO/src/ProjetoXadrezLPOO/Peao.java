package ProjetoXadrezLPOO;

public class Peao extends Peca {

    public Peao(String corPeca) {
        //define o tipo e cor da peca
        super(corPeca, "peao");
        if(cor.equals("branco")){
            print = "  P  ";
        }
        else{
            print = "  p: ";
        }
    }

        public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){
        //checa a validez do movimento para o tipo

        int duasCasas;
        int umaCasa;
        int posicaoInicial;
        Posicao posicao = Jogo.posicao[moverPara[1]][moverPara[0]];

        if(corPeca.equals("branco")){
            duasCasas = -2;
            umaCasa = -1;
            posicaoInicial = 6;
        }
        else{
            duasCasas = 2;
            umaCasa = 1;
            posicaoInicial = 1;
        }

        if(moverPara[1] == moverDe[1] + umaCasa){//pros lados
            if((moverPara[0] == moverDe[0] - 1) || (moverPara[0] == moverDe[0] + 1)){//exceto em captura
                return (!posicao.tipo().equals("vazio")) && (!posicao.cor().equals(corPeca));
            }
            else{//se andar pra frente e pra frente for vazio
                return (moverPara[0] == moverDe[0]) && (posicao.tipo().equals("vazio"));
            }
        }
        else{//caso sejam 2 casas, caso todos os testes falhem retornará false
            return (moverPara[1] == posicaoInicial + duasCasas) && (moverPara[0] == moverDe[0]) && (posicao.tipo().equals("vazio"));
        }
    }
}
