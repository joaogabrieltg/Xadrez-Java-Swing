package ProjetoXadrezLPOO;

public class Rei extends Peca {

    public Rei(String corPeca){
        //define o tipo e cor da peca
        super(corPeca, "rei");
        if(cor.equals("branco")){
            print = "  R  ";
        }
        else{
            print = "  r: ";
            print = "  r: ";
        }
    }

    @Override
    public boolean movimento(int[] moverDe, int[] moverPara, String corPeca){

        Posicao posicao = Jogo.posicao[moverPara[1]][moverPara[0]];//posicao inicializada

        for(int AlcanceX = -1; AlcanceX <= 1; AlcanceX++){
            for(int AlcanceY = -1; AlcanceY <= 1; AlcanceY++){
                if(moverPara[0] == moverDe[0] + AlcanceX && moverPara[1] == moverDe[1] + AlcanceY){//exceto em captura e se a posicao desejada for vazia
                    if(((!posicao.tipo().equals("vazio")) && (!posicao.cor().equals(corPeca))) || (posicao.tipo().equals("vazio"))){
                        this.mexeu=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMexeu(){
        return mexeu;
    }
}