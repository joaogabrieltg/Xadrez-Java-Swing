package ProjetoXadrezLPOO;

public class ReiXequeException extends Exception {
        public ReiXequeException(String cor){
            super("O rei "+cor+" esta em cheque");
        }
    }
