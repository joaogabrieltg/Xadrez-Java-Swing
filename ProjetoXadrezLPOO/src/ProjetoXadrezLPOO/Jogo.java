package ProjetoXadrezLPOO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Jogo extends JFrame{

    static final char[] coordLetra = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};//coodenadas horizontais
    static final int[] coordNumero = {1, 2, 3, 4, 5, 6, 7, 8, 9};//coordenadas verticais
    public static Posicao[][] posicao = new Posicao[8][8];
    private JTextArea localTabuleiro;
    private JPanel painel;
    private JTextField localEntradas;
    private JButton botaoConfirmar;
    private JLabel Xadrez;
    private JButton botaoDesistencia;
    private JLabel nomeDoJogadorB;
    private JLabel nomeDoJogadorP;
    private JLabel jogadorVez;
    private JLabel instrucoes;
    static int rodada;
    static int[][] movimento;
    static String corExc;
    static boolean sairLoop;

    public Jogo(String titulo){
        super(titulo);
        this.localTabuleiro.setText("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(painel);
        this.setSize(700, 600);
        this.setResizable(false);
        this.setVisible(true);

        botaoDesistencia.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(new JFrame(), "Voce desistiu", "Desistencia confirmada!",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) throws ReiXequeException, RoqueInvalidoException, JogadaInvalidaExeption{

        rodada = 1;
        Jogo GUI = new Jogo("Xadrez - Joao Gila e Lucas Maciel");
        GUI.jogadorVez.setText("Esta local indica a vez");
        String jogador1 = JOptionPane.showInputDialog("Digite o nome do jogador 1");
        GUI.nomeDoJogadorB.setText(jogador1);
        String jogador2 = JOptionPane.showInputDialog("Digite o nome do jogador 2");
        if(Objects.equals(jogador2, jogador1)){
            jogador2 += " 2";
        }
        GUI.nomeDoJogadorP.setText(jogador2);

        GUI.instrucoes.setText("formato da jogada: LetraNumero:LetraNumero");

        Jogador jogadorBranco = new Jogador(jogador1, "branco");
        Jogador jogadorPreto = new Jogador(jogador2, "preto");

        posicionar();//organizar as pecas no tabuleiro

        GUI.localTabuleiro.setText(printarTabuleiro());
        GUI.jogadorVez.setText("branco");

        while(true){
            for(rodada = 1; rodada <= 2; rodada++){
                sairLoop = false;
                while(!sairLoop){
                    GUI.botaoConfirmar.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent botaopressionado){
                            String entrada = GUI.localEntradas.getText();
                            if(!sairLoop){
                                if(!entrada.isEmpty()){
                                    if(GUI.jogadorVez.getText().equals("branco")){
                                        movimento = jogadorBranco.jogada(entrada);
                                        corExc = "branco";
                                        sairLoop = true;
                                    }
                                    else if(GUI.jogadorVez.getText().equals("preto")){
                                        movimento = jogadorPreto.jogada(entrada);
                                        corExc = "preto";
                                        sairLoop = true;
                                    }
                                }
                            }
                        }
                    });
                }
                ReiXequeException reiXeque = new ReiXequeException(corExc);
                JogadaInvalidaExeption jogadaInvalida = new JogadaInvalidaExeption();
                RoqueInvalidoException roqueInvalido = new RoqueInvalidoException(corExc);
                try {
                    if(movimento[0][0] == -3){//roque grande
                        if(GUI.jogadorVez.getText().equals("branco") && !xeque("branco") && posicao[7][4].tipo().equals("rei") && posicao[7][4].cor().equals("branco")){//7,0 & 7,4 & 7,7
                            if(posicao[7][0].tipo().equals("torre") && posicao[7][0].cor().equals("branco")){
                                if(roqueGrande("branco")){
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    if(Objects.equals(corExc, "branco")){
                                        GUI.jogadorVez.setText("preto");
                                    }
                                    else if(Objects.equals(corExc, "preto")){
                                        GUI.jogadorVez.setText("branco");
                                    }
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    break;
                                }
                                else{
                                    throw roqueInvalido;
                                }
                            }
                            continue;
                        }
                        else if(GUI.jogadorVez.getText().equals("preto") && !xeque("preto") && posicao[0][4].tipo().equals("rei") && posicao[0][4].cor().equals("preto")){//0,0 & 0,4 & 0,7
                            if(posicao[0][0].tipo().equals("torre") && posicao[0][0].cor().equals("preto")){
                                if(roqueGrande("preto")){
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    if(Objects.equals(corExc, "branco")){
                                        GUI.jogadorVez.setText("preto");
                                    }
                                    else if(Objects.equals(corExc, "preto")){
                                        GUI.jogadorVez.setText("branco");
                                    }
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    break;
                                }
                                else{
                                    throw roqueInvalido;
                                }
                            }
                            continue;
                        }
                    }
                    else if(movimento[0][0] == -4){//roque pequeno
                        if(GUI.jogadorVez.getText().equals("branco") && !xeque("branco") && posicao[7][4].tipo().equals("rei") && posicao[7][4].cor().equals("branco")){//7,0 & 7,4 & 7,7
                            if(posicao[7][7].tipo().equals("torre") && posicao[7][7].cor().equals("branco")){
                                if(roquePequeno("branco")){
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    if(Objects.equals(corExc, "branco")){
                                        GUI.jogadorVez.setText("preto");
                                    }
                                    else if(Objects.equals(corExc, "preto")){
                                        GUI.jogadorVez.setText("branco");
                                    }
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    break;
                                }
                                else{
                                    throw roqueInvalido;
                                }
                            }
                            continue;
                        }
                        else if(GUI.jogadorVez.getText().equals("preto") && !xeque("preto") && posicao[0][4].tipo().equals("rei") && posicao[0][4].cor().equals("preto")){//7,0 & 7,4 & 7,7
                            if(posicao[0][7].tipo().equals("torre") && posicao[0][7].cor().equals("preto")){
                                if(roquePequeno("preto")){
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    if(Objects.equals(corExc, "branco")){
                                        GUI.jogadorVez.setText("preto");
                                    }
                                    else if(Objects.equals(corExc, "preto")){
                                        GUI.jogadorVez.setText("branco");
                                    }
                                    GUI.localEntradas.setText("");
                                    GUI.localTabuleiro.setText(printarTabuleiro());
                                    break;
                                }
                                else{
                                    throw roqueInvalido;
                                }
                            }
                            continue;
                        }
                    }

                    if(movimento[0][0] == -1){
                        throw jogadaInvalida;
                    }
                    else{
                        Posicao posicaoPeca = Jogo.posicao[movimento[0][1]][movimento[0][0]];
                        boolean movimentoValido;
                        if(GUI.jogadorVez.getText().equals("branco")){
                            movimentoValido = posicaoPeca.movimento(movimento[0], movimento[1], "branco");
                        }
                        else if(GUI.jogadorVez.getText().equals("preto")){
                            movimentoValido = posicaoPeca.movimento(movimento[0], movimento[1], "preto");
                        }
                        else{
                            throw jogadaInvalida;
                        }
                        if(!movimentoValido){
                            throw jogadaInvalida;
                        }

                        else{

                            limparPosicao(movimento[0], movimento[1]);//caso o movimento seja valido a posicao inicial e apagada e o peao se move

                            if(xeque("branco")){
                                if(GUI.jogadorVez.getText().equals("branco")){
                                    limparPosicao(movimento[1], movimento[0]);
                                    throw reiXeque;
                                }
                                else if(GUI.jogadorVez.getText().equals("preto")){
                                    JOptionPane.showMessageDialog(new JFrame(), "VOCE DEIXOU O OUTRO REI EM XEQUE!", "XEQUE!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            if(xeque("preto")){
                                if(GUI.jogadorVez.getText().equals("preto")){
                                    limparPosicao(movimento[1], movimento[0]);
                                    throw reiXeque;
                                }
                                else if(GUI.jogadorVez.getText().equals("branco")){
                                    JOptionPane.showMessageDialog(new JFrame(), "VOCE DEIXOU O OUTRO REI EM XEQUE!", "XEQUE!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            GUI.localTabuleiro.setText(printarTabuleiro());
                            promocao();
                            GUI.localTabuleiro.setText(printarTabuleiro());
                            GUI.localEntradas.setText("");
                            if(Objects.equals(corExc, "branco")){
                                GUI.jogadorVez.setText("preto");
                            }
                            else if(Objects.equals(corExc, "preto")){
                                GUI.jogadorVez.setText("branco");
                            }
                            GUI.localEntradas.setText("");
                            GUI.localTabuleiro.setText(printarTabuleiro());
                            break;//reinicia o loop de rodada
                        }
                    }
                } catch (JogadaInvalidaExeption e) {
                    JOptionPane.showMessageDialog(new JFrame(), jogadaInvalida.getMessage(), "JOGADA INVALIDA",
                            JOptionPane.ERROR_MESSAGE);
                    GUI.localEntradas.setText("");
                    GUI.localTabuleiro.setText(printarTabuleiro());

                } catch (ReiXequeException e) {
                    JOptionPane.showMessageDialog(new JFrame(), reiXeque.getMessage(), "XEQUE!",
                            JOptionPane.WARNING_MESSAGE);
                    GUI.localEntradas.setText("");
                    GUI.localTabuleiro.setText(printarTabuleiro());

                } catch (RoqueInvalidoException e) {
                    JOptionPane.showMessageDialog(new JFrame(), roqueInvalido.getMessage(), "ROQUE INVALIDO",
                            JOptionPane.ERROR_MESSAGE);
                    GUI.localEntradas.setText("");
                    GUI.localTabuleiro.setText(printarTabuleiro());
                }
            }
        }
    }

    private static boolean roqueGrande(String cor){
        if(cor.equals("branco")){
            if(!posicao[7][0].isMexeu() && !posicao[7][4].isMexeu()){
                if(posicao[7][3].tipo().equals("vazio") && posicao[7][2].tipo().equals("vazio") && posicao[7][1].tipo().equals("vazio")){
                    boolean xeque = false;
                    Rei testandoXeque = new Rei("branco");
                    posicao[7][3] = testandoXeque;
                    if(xeque("branco")){
                        xeque = true;
                    }
                    posicao[7][2] = testandoXeque;
                    if(xeque("branco")){
                        xeque = true;
                    }
                    posicao[7][1] = testandoXeque;
                    if(xeque("branco")){
                        xeque = true;
                    }
                    posicao[7][1] = new Limpar();
                    posicao[7][2] = new Limpar();
                    posicao[7][3] = new Limpar();
                    if(!xeque){
                        posicao[7][0] = new Limpar();
                        posicao[7][4] = new Limpar();
                        posicao[7][3] = new Torre("branco");
                        posicao[7][2] = new Rei("branco");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        else if(cor.equals("preto")){
            if(!posicao[0][0].isMexeu() && !posicao[0][4].isMexeu()){
                if(posicao[0][3].tipo().equals("vazio") && posicao[0][2].tipo().equals("vazio") && posicao[0][1].tipo().equals("vazio")){
                    boolean xeque = false;
                    Rei testandoXeque = new Rei("preto");
                    posicao[0][3] = testandoXeque;
                    if(xeque("preto")){
                        xeque = true;
                    }
                    posicao[0][2] = testandoXeque;
                    if(xeque("preto")){
                        xeque = true;
                    }
                    posicao[0][1] = testandoXeque;
                    if(xeque("preto")){
                        xeque = true;
                    }
                    posicao[0][1] = new Limpar();
                    posicao[0][2] = new Limpar();
                    posicao[0][3] = new Limpar();
                    if(!xeque){
                        posicao[0][0] = new Limpar();
                        posicao[0][4] = new Limpar();
                        posicao[0][3] = new Torre("preto");
                        posicao[0][2] = new Rei("preto");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private static boolean roquePequeno(String cor){
        if(cor.equals("branco")){
            if(!posicao[7][7].isMexeu() && !posicao[7][4].isMexeu()){
                if(posicao[7][6].tipo().equals("vazio") && posicao[7][5].tipo().equals("vazio")){
                    boolean xeque = false;
                    Rei testandoXeque = new Rei("branco");
                    posicao[7][6] = testandoXeque;
                    if(xeque("branco")){
                        xeque = true;
                    }
                    posicao[7][5] = testandoXeque;
                    if(xeque("branco")){
                        xeque = true;
                    }
                    posicao[7][6] = new Limpar();
                    posicao[7][5] = new Limpar();
                    if(!xeque){
                        posicao[7][7] = new Limpar();
                        posicao[7][4] = new Limpar();
                        posicao[7][5] = new Torre("branco");
                        posicao[7][6] = new Rei("branco");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        else if(cor.equals("preto")){
            if(!posicao[0][7].isMexeu() && !posicao[0][4].isMexeu()){
                if(posicao[0][6].tipo().equals("vazio") && posicao[0][5].tipo().equals("vazio")){
                    boolean xeque = false;
                    Rei testandoXeque = new Rei("preto");
                    posicao[0][6] = testandoXeque;
                    if(xeque("preto")){
                        xeque = true;
                    }
                    posicao[0][5] = testandoXeque;
                    if(xeque("preto")){
                        xeque = true;
                    }
                    posicao[0][6] = new Limpar();
                    posicao[0][5] = new Limpar();
                    if(!xeque){
                        posicao[0][7] = new Limpar();
                        posicao[0][4] = new Limpar();
                        posicao[0][5] = new Torre("preto");
                        posicao[0][6] = new Rei("preto");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private static void promocao(){
        for(int x = 0; x < 8; x++){//checa se o peao chegou do outro lado e faz a promocao
            int valido = 0;
            if(posicao[0][x].tipo().equals("peao") && posicao[0][x].cor().equals("branco")){
                posicao[0][x] = new Limpar();
                do {
                    String escolhaPromocao = JOptionPane.showInputDialog(new JOptionPane(), "Digite que peca o peao se tranformara!", "PROMOCAO!", JOptionPane.PLAIN_MESSAGE);
                    if(escolhaPromocao != null){
                        if(escolhaPromocao.equalsIgnoreCase("cavalo") || escolhaPromocao.equalsIgnoreCase("c")){
                            posicao[0][x] = new Cavalo("branco");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("dama") || escolhaPromocao.equalsIgnoreCase("d")){
                            posicao[0][x] = new Dama("branco");
                            JOptionPane.showMessageDialog(new JFrame(), "Promocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("torre") || escolhaPromocao.equalsIgnoreCase("t")){
                            posicao[0][x] = new Torre("branco");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("bispo") || escolhaPromocao.equalsIgnoreCase("b")){
                            posicao[0][x] = new Bispo("branco");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Peca invalida! Tente novamente!", "PROMOCAO NAO REALIZADA",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Peca invalida! Tente novamente!", "PROMOCAO NAO REALIZADA",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                while(valido == 0);
            }
            if(posicao[7][x].tipo().equals("peao") && posicao[7][x].cor().equals("preto")){
                posicao[7][x] = new Limpar();

                do {
                    String escolhaPromocao = JOptionPane.showInputDialog(new JOptionPane(), "Digite que peca o peao se tranformara!", "PROMOCAO!", JOptionPane.PLAIN_MESSAGE);
                    if(escolhaPromocao != null){
                        if(escolhaPromocao.equalsIgnoreCase("cavalo") || escolhaPromocao.equalsIgnoreCase("c")){
                            posicao[7][x] = new Cavalo("preto");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("dama") || escolhaPromocao.equalsIgnoreCase("d")){
                            posicao[7][x] = new Dama("preto");
                            JOptionPane.showMessageDialog(new JFrame(), "Promocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("torre") || escolhaPromocao.equalsIgnoreCase("t")){
                            posicao[7][x] = new Torre("preto");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else if(escolhaPromocao.equalsIgnoreCase("bispo") || escolhaPromocao.equalsIgnoreCase("b")){
                            posicao[7][x] = new Bispo("preto");
                            JOptionPane.showMessageDialog(new JFrame(), "Subpromocao!", "PROMOCAO REALIZADA",
                                    JOptionPane.INFORMATION_MESSAGE);
                            valido++;
                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Peca invalida! Tente novamente!", "PROMOCAO NAO REALIZADA",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Peca invalida! Tente novamente!", "PROMOCAO NAO REALIZADA",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                while(valido == 0);
            }
        }
    }

    private static boolean xeque(String cor){//aviso de porqueira e gambiarra
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(posicao[i][j].tipo().equals("rei") && posicao[i][j].cor().equals(cor)){//procura pelo rei da cor testada
                    for(int k = 0; k < 8; k++){//+desce, -sobe
                        for(int l = 0; l < 8; l++){//+direita, -esquerda
                            if(!posicao[k][l].tipo().equals("vazio") && posicao[k][l].cor() != null){
                                if(!posicao[k][l].cor().equals(cor)){
                                    Posicao ameaca = posicao[k][l];
                                    for(int x = 0; x < 8; x++){
                                        String tipoAmeaca = ameaca.tipo();

                                        if(k == i + x && l == j + x){
                                            int checar1 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i + y][j + y].tipo().equals("vazio") && (posicao[i + y][j + y].cor().equals(cor) || !posicao[i + y][j + y].cor().equals(cor)) && !posicao[i + y][j + y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar1--;
                                                }
                                                if(checar1 == 0 && (((tipoAmeaca.equals("bispo") || tipoAmeaca.equals("dama"))) || (k == i + 1 && l == j + 1 && (tipoAmeaca.equals("peao") || tipoAmeaca.equals("rei"))))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i + x && l == j - x){
                                            int checar2 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i + y][j - y].tipo().equals("vazio") && (posicao[i + y][j - y].cor().equals(cor) || !posicao[i + y][j - y].cor().equals(cor)) && !posicao[i + y][j - y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar2--;
                                                }
                                                if(checar2 == 0 && (((tipoAmeaca.equals("bispo") || tipoAmeaca.equals("dama"))) || (k == i + 1 && l == j - 1 && (tipoAmeaca.equals("peao") || tipoAmeaca.equals("rei"))))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i - x && l == j + x){
                                            int checar3 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i - y][j + y].tipo().equals("vazio") && (posicao[i - y][j + y].cor().equals(cor) || !posicao[i - y][j + y].cor().equals(cor)) && !posicao[i - y][j + y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar3--;
                                                }
                                                if(checar3 == 0 && (((tipoAmeaca.equals("bispo") || tipoAmeaca.equals("dama"))) || (k == i - 1 && l == j + 1 && (tipoAmeaca.equals("peao") || tipoAmeaca.equals("rei"))))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i - x && l == j - x){
                                            int checar4 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i - y][j - y].tipo().equals("vazio") && (posicao[i - y][j - y].cor().equals(cor) || !posicao[i - y][j - y].cor().equals(cor)) && !posicao[i - y][j - y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar4--;
                                                }
                                                if(checar4 == 0 && (((tipoAmeaca.equals("bispo") || tipoAmeaca.equals("dama"))) || (k == i - 1 && l == j - 1 && (tipoAmeaca.equals("peao") || tipoAmeaca.equals("rei"))))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i + x && l == j){
                                            int checar5 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i + y][j].tipo().equals("vazio") && (posicao[i + y][j].cor().equals(cor) || !posicao[i + y][j].cor().equals(cor)) && !posicao[i + y][j].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar5--;
                                                }
                                                if(checar5 == 0 && (((tipoAmeaca.equals("torre") || tipoAmeaca.equals("dama"))) || (k == i + 1 && tipoAmeaca.equals("rei")))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i - x && l == j){
                                            int checar6 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i - y][j].tipo().equals("vazio") && (posicao[i - y][j].cor().equals(cor) || !posicao[i - y][j].cor().equals(cor)) && !posicao[i - y][j].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar6--;
                                                }
                                                if(checar6 == 0 && (((tipoAmeaca.equals("torre") || tipoAmeaca.equals("dama"))) || (k == i - 1 && tipoAmeaca.equals("rei")))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i && l == j + x){
                                            int checar7 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i][j + y].tipo().equals("vazio") && (posicao[i][j + y].cor().equals(cor) || !posicao[i][j + y].cor().equals(cor)) && !posicao[i][j + y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar7--;
                                                }
                                                if(checar7 == 0 && (((tipoAmeaca.equals("torre") || tipoAmeaca.equals("dama"))) || (l == j + 1 && tipoAmeaca.equals("rei")))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i && l == j - x){
                                            int checar8 = x;
                                            for(int y = 0; y <= x; y++){
                                                if(!posicao[i][j - y].tipo().equals("vazio") && (posicao[i][j - y].cor().equals(cor) || !posicao[i][j - y].cor().equals(cor)) && !posicao[i][j - y].equals(ameaca)){
                                                    continue;
                                                }
                                                else{
                                                    checar8--;
                                                }
                                                if(checar8 == 0 && (((tipoAmeaca.equals("torre") || tipoAmeaca.equals("dama"))) || (l == j - 1 && tipoAmeaca.equals("rei")))){
                                                    return true;
                                                }
                                            }
                                        }
                                        if(k == i + 2 && l == j + 1 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i + 2 && l == j - 1 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i - 2 && l == j + 1 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i - 2 && l == j - 1 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i + 1 && l == j + 2 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i + 1 && l == j - 2 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i - 1 && l == j + 2 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                        if(k == i - 1 && l == j - 2 && tipoAmeaca.equals("cavalo")){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void posicionar(){

        for(int i = 0; i < 8; i++){
            //posicao[1][i] = new Limpar();
            posicao[1][i] = new Peao("preto");//peoes de cima
        }

        //posicao[0][0] = new Limpar();
        posicao[0][0] = new Torre("preto");
        //posicao[0][1] = new Limpar();
        posicao[0][1] = new Cavalo("preto");
        //posicao[0][2] = new Limpar();
        posicao[0][2] = new Bispo("preto");
        //posicao[0][3] = new Limpar();
        posicao[0][3] = new Dama("preto");
        //posicao[0][4] = new Limpar();
        posicao[0][4] = new Rei("preto");
        //posicao[0][5] = new Limpar();
        posicao[0][5] = new Bispo("preto");
        //posicao[0][6] = new Limpar();
        posicao[0][6] = new Cavalo("preto");
        //posicao[0][7] = new Limpar();
        posicao[0][7] = new Torre("preto");

        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                posicao[i][j] = new Limpar();//vazio do meio
            }
        }

        for(int i = 0; i < 8; i++){
            //posicao[6][i] = new Limpar();
            posicao[6][i] = new Peao("branco");//peoes de baixo
        }

        //posicao[7][0] = new Limpar();
        posicao[7][0] = new Torre("branco");
        //posicao[7][1] = new Limpar();
        posicao[7][1] = new Cavalo("branco");
        //posicao[7][2] = new Limpar();
        posicao[7][2] = new Bispo("branco");
        //posicao[7][3] = new Limpar();
        posicao[7][3] = new Dama("branco");
        //posicao[7][4] = new Limpar();
        posicao[7][4] = new Rei("branco");
        //posicao[7][5] = new Limpar();
        posicao[7][5] = new Bispo("branco");
        //posicao[7][6] = new Limpar();
        posicao[7][6] = new Cavalo("branco");
        //posicao[7][7] = new Limpar();
        posicao[7][7] = new Torre("branco");

    }

    private static void limparPosicao(int[] moverDe, int[] moverPara){//limpa posicao inicial apos um movimento
        posicao[moverPara[1]][moverPara[0]] = posicao[moverDe[1]][moverDe[0]];
        posicao[moverDe[1]][moverDe[0]] = new Limpar();
    }

    private static String printarTabuleiro(){//design do tabuleiro

        StringBuilder tabuleiro;

        tabuleiro = new StringBuilder("\n   ");
        for(int i = 0; i < (Jogo.coordLetra.length - 1); i++){//para no h pois o I é apenas para desistencia, letras em cima
            tabuleiro.append("  -").append(Jogo.coordLetra[i]).append("- ");
        }
        tabuleiro.append("\n   ");
        for(int i = 0; i < 8; i++){//divisor das letras de cima pro tabuleiro
            tabuleiro.append("=-----");
        }
        tabuleiro.append("=\n");
        for(int i = 0; i < 8; i++){//numeros a esquerda
            tabuleiro.append("-").append(8 - i).append(" |");
            for(Posicao j : posicao[i]){//printa o que esta na posicao, seja peca ou vazio
                tabuleiro.append(j.print()).append("|");
            }
            tabuleiro.append(" ").append(8 - i).append("-");//numeros a direita
            tabuleiro.append("\n   ");
            for(int j = 0; j < 8; j++){//divisor entre os espacos
                tabuleiro.append("=-----");
            }
            tabuleiro.append("=\n");
        }
        tabuleiro.append("   ");
        for(int i = 0; i < (Jogo.coordLetra.length - 1); i++){//letras em baixo
            tabuleiro.append("  -").append(Jogo.coordLetra[i]).append("- ");
        }
        tabuleiro.append("\n>A NOTACAO DO ROQUE GRANDE E \"0-0-0\" E DO PEQUENO \"0-0\"< \n\n");
        return tabuleiro.toString();
    }
}