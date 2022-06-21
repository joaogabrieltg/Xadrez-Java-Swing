package ProjetoXadrezLPOO;


public class Bispo extends Peca{
    public Bispo(String corPeca){
        //define o tipo e cor da peca
        super(corPeca, "bispo");
        if(cor.equals("branco")){
            print = "  B  ";
        }
        else{
            print = "  b: ";
        }
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){

        Posicao posicao;
        int caminhoX;
        int caminhoY;

        if(moverPara[0] > moverDe[0]){
            caminhoX = (moverPara[0] - moverDe[0]);
        }
        else if(moverPara[0] < moverDe[0]){
            caminhoX = (moverDe[0] - moverPara[0]);
        }
        else{
            return false;
        }
        if(moverPara[1] > moverDe[1]){
            caminhoY = (moverPara[1] - moverDe[1]);
        }
        else if(moverPara[1] < moverDe[1]){
            caminhoY = (moverDe[1] - moverPara[1]);
        }
        else{
            return false;
        }
        if(caminhoX == caminhoY){
            for(int i = 1; i <= caminhoX; i++){
                for(int j = 1; j <= caminhoY; j++){
                    if(moverPara[1] < moverDe[1] && moverPara[0] < moverDe[0]){
                        posicao = Jogo.posicao[moverDe[1] - i][moverDe[0] - j];
                    }
                    else if(moverPara[1] < moverDe[1] && moverPara[0] > moverDe[0]){
                        posicao = Jogo.posicao[moverDe[1] - i][moverDe[0] + j];
                    }
                    else if(moverPara[1] > moverDe[1] && moverPara[0] < moverDe[0]){
                        posicao = Jogo.posicao[moverDe[1] + i][moverDe[0] - j];
                    }
                    else if(moverPara[1] > moverDe[1] && moverPara[0] > moverDe[0]){
                        posicao = Jogo.posicao[moverDe[1] + i][moverDe[0] + j];
                    }
                    else{
                        return false;
                    }

                    if((!posicao.tipo().equals("vazio")) && (i != caminhoX)){
                        return false;
                    }
                    else if((i == j) && ((posicao.tipo().equals("vazio")) || (!posicao.cor().equals(corPeca)))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}