package ProjetoXadrezLPOO;

public class Dama extends Peca {

    public Dama(String corPeca){
        //define o tipo e cor da peca
        super(corPeca, "dama");
        if(cor.equals("branco")){
            print = "  D  ";
        }
        else{
            print = "  d: ";
        }
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){

        Posicao posicao;

        int caminhoX;
        int caminhoY;
        //condicionais e loops que verificam se o movimento da dama é valido
        if(moverPara[0] > moverDe[0]){
            caminhoX = (moverPara[0] - moverDe[0]);
        }
        else if(moverPara[0] < moverDe[0]){
            caminhoX = (moverDe[0] - moverPara[0]);
        }
        else {
            caminhoX = 0;
        }
        if(moverPara[1] > moverDe[1]){
            caminhoY = (moverPara[1] - moverDe[1]);
        }
        else if(moverPara[1] < moverDe[1]){
            caminhoY = (moverDe[1] - moverPara[1]);
        }
        else {
            caminhoY = 0;
        }

        if(caminhoX == caminhoY){
            for(int j = 1; j <= caminhoX; j++){
                for(int i = 1; i <= caminhoY; i++){
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
                    if((!posicao.tipo().equals("vazio")) && (i != caminhoY) && (j != caminhoX)){
                        return false;
                    }
                    else if((i == caminhoY) && (j == caminhoX) && ((posicao.tipo().equals("vazio")) || (!posicao.cor().equals(corPeca)))){//exceto em captura e se a posicao desejada for vazia
                        return true;
                    }
                }
            }
        }
        else if(caminhoY == 0){
            for(int i = 1; i <= caminhoX; i++){
                if(moverPara[1] == moverDe[1] && moverPara[0] > moverDe[0]){//para a direita
                    posicao = Jogo.posicao[moverDe[1]][moverDe[0] + i];
                }
                else if(moverPara[1] == moverDe[1] && moverPara[0] < moverDe[0]){//para a esquerda
                    posicao = Jogo.posicao[moverDe[1]][moverDe[0] - i];
                }
                else{
                    return false;
                }

                if((!posicao.tipo().equals("vazio")) && (i != caminhoX)){
                    return false;
                }
                else if((i == caminhoX) && ((posicao.tipo().equals("vazio")) || (!posicao.cor().equals(corPeca)))){
                    //exceto em captura e se a posicao desejada for vazia
                    return true;
                }
            }
        }
        else if(caminhoX == 0){
            for(int i = 1; i <= caminhoY; i++){
                if(moverPara[1] > moverDe[1] && moverPara[0] == moverDe[0]){//para frente
                    posicao = Jogo.posicao[moverDe[1] + i][moverDe[0]];
                }
                else if(moverPara[1] < moverDe[1] && moverPara[0] == moverDe[0]){//para trás
                    posicao = Jogo.posicao[moverDe[1] - i][moverDe[0]];
                }
                else{
                    return false;
                }
                if((!posicao.tipo().equals("vazio")) && (i != caminhoY)){
                    return false;
                }
                else if((i == caminhoY) && ((posicao.tipo().equals("vazio")) || (!posicao.cor().equals(corPeca)))){
                    //exceto em captura e se a posicao desejada for vazia
                    return true;
                }
            }
        }
        return false;
    }
}