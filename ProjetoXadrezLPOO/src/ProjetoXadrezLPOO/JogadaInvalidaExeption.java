package ProjetoXadrezLPOO;

public class JogadaInvalidaExeption extends Exception {
    public JogadaInvalidaExeption(){
        super("Jogada invalida. Tente de novo");
    }
}
