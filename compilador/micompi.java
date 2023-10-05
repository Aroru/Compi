import java.io.IOException;

public class micompi {
    nodo p;
    public static void main(String[] args)throws IOException {
        lexico lexico = new lexico();
        if(!lexico.ErroEncontrado){
            System.out.println("Analisis lexico terminado");
        }
    }
}
