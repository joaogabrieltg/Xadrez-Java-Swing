package ProjetoXadrezLPOO;

public class Torre extends Peca {

    public Torre(String corPeca){
        //define o tipo e cor da peca
        super(corPeca, "torre");
        if(cor.equals("branco")){
            print = "  T  ";
        }
        else{
            print = "  t: ";
        }
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){

        Posicao posicao;

        int caminho;
        //sequencias de condicionais que verificam se o movimento é valido
        if(moverPara[0] > moverDe[0]){
            caminho = (moverPara[0] - moverDe[0]);
        }
        else if(moverPara[0] < moverDe[0]){
            caminho = (moverDe[0] - moverPara[0]);
        }
        else if(moverPara[1] > moverDe[1]){
            caminho = (moverPara[1] - moverDe[1]);
        }
        else if(moverPara[1] < moverDe[1]){
            caminho = (moverDe[1] - moverPara[1]);
        }
        else{
            return false;
        }

        for(int i = 1; i <= caminho; i++){
            if(moverPara[1] == moverDe[1] && moverPara[0] > moverDe[0]){//para a direita
                posicao = Jogo.posicao[moverDe[1]][moverDe[0] + i];
            }
            else if(moverPara[1] == moverDe[1] && moverPara[0] < moverDe[0]){//para a esquerda
                posicao = Jogo.posicao[moverDe[1]][moverDe[0] - i];
            }
            else if(moverPara[1] > moverDe[1] && moverPara[0] == moverDe[0]){//para frente
                posicao = Jogo.posicao[moverDe[1] + i][moverDe[0]];
            }
            else if(moverPara[1] < moverDe[1] && moverPara[0] == moverDe[0]){//para trás
                posicao = Jogo.posicao[moverDe[1] - i][moverDe[0]];
            }
            else{
                return false;
            }

            if((!posicao.tipo().equals("vazio")) && (i != caminho)){
                return false;
            }
            else if((i == caminho) && ((posicao.tipo().equals("vazio")) || (!posicao.cor().equals(corPeca)))){
                //exceto em captura e se a posicao desejada for vazia
                this.mexeu = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMexeu(){
        return mexeu;
    }
}