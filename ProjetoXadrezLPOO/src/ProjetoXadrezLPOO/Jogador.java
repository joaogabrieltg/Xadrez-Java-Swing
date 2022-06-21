package ProjetoXadrezLPOO;

public class Jogador {

    private final String nome;
    private final String corJogador;

    Jogador(String nome, String corJogador){
        this.nome = nome;
        this.corJogador = corJogador;
    }

    private int letraParaInt(char caractere){//transforma o char recebido em int

        int coordenadaL = -1;

        for(int i = 0; i < Jogo.coordLetra.length; i++){
            if(Jogo.coordLetra[i] == caractere){
                coordenadaL = i;
                if(caractere == 'I' || caractere == 'i'){//i=-2 pra desistir
                    coordenadaL = -2;
                }
            }
        }
        return coordenadaL;
    }

    private int numeroparaInt(char coordNumero){//transforma o char(numero) recebido em int

        int coordenadaN = -1;
        int numeroInt = Character.getNumericValue(coordNumero);

        for(int i = 0; i < Jogo.coordNumero.length; i++) {
            if (i == numeroInt) {
                coordenadaN = numeroInt;
                break;
            }
        }
        if (numeroInt == 9) {//9 = -2 pra desistir
            coordenadaN = -2;
        }
        return coordenadaN;
    }

    public int[][] jogada(String entrada){

        int[][] jogada = new int[2][2];
        int valido = 0;


        do{//pergunta de onde e para onde

            String entradaUsuario= entrada.trim();

            if(entradaUsuario.isEmpty()){
                entradaUsuario = "0";
            }
            if(entradaUsuario.equalsIgnoreCase("0-0-0")){//-2, 8
                entradaUsuario = "i8:i8";
            }
            if(entradaUsuario.equalsIgnoreCase("0-0")){//-2, 7
                entradaUsuario = "i7:i7";
            }

            entradaUsuario = entradaUsuario + " ";
            int escolha = 0;
            //int roque = 0;

            while (escolha < 2){
                escolha++;

                if (escolha == 2){
                    entrada = entradaUsuario.substring(3, 5);
                }
                if (escolha == 1) {
                    entrada = entradaUsuario.substring(0, 2);
                }
                if (entradaUsuario.length() == 6 && !entrada.contains(" ") && entradaUsuario.charAt(2) == ':') {//checando caracteres e tamanhos validos
                    valido++;

                    int letra = letraParaInt(Character.toUpperCase(entrada.charAt(0)));//letra -> numero
                    int numero = numeroparaInt(entrada.charAt(1));//letra numero -> numero
                    if (letra != -1 && letra != -2) {//se não desistiu e é caracter válido
                        if (numero != -1 && numero != -2) {

                            numero = 8 - numero;//inverter pq o tabuleiro esta de cabeca para baixo
                            int[] coord = {letra, numero};

                            if (escolha == 1) {//repete pro 2
                                if (Jogo.posicao[numero][letra].tipo().equals("vazio") || !Jogo.posicao[numero][letra].cor().equals(corJogador)) {//checa se tem peca(por isso e feito antes do 2)
                                    coord[0] = -1;
                                    coord[1] = -1;
                                    //saida -1 -1, invalido
                                    return new int[][]{coord, coord};
                                }
                            }
                            jogada[escolha - 1] = coord;
                        }
                    }
                    if (letra == -2 && numero == -2) {//comando para a desistencia
                        int[] coord = {letra, numero};
                        return new int[][]{coord, coord};
                    }
                    if (letra == -2 && numero == 8) {//roque grande
                        int[] coord = {-3, -3};
                        return new int[][]{coord, coord};
                    }
                    if (letra == -2 && numero == 7) {//roque pequeno
                        int[] coord = {-4, -4};
                        return new int[][]{coord, coord};
                    }
                }
                else {
                    return jogada;
                }
            }
        }while(valido == 0);
        return jogada;
    }
}