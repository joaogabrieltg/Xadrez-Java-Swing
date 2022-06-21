package ProjetoXadrezLPOO;

public class RoqueInvalidoException extends Exception {
    public RoqueInvalidoException(String cor){
        super("O roque falhou!");
    }
}
