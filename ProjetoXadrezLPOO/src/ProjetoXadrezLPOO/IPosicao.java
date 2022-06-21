package ProjetoXadrezLPOO;

public interface IPosicao {
    public boolean isMexeu();
    public String print();
    public String cor();
    public String tipo();
    public abstract boolean movimento(int[] moverDe, int[] moverPara, String corPeca);
}
