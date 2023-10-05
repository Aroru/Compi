import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class lexico {
    nodo cabeza = null,p;
    int estado = 0,columna, valorMT, numRegion=1; //num de renglon*
    int caracter = 0;
    String lexema = "";
    boolean ErroEncontrado = false;



    String archivo = "C:\\Users\\aronc\\Downloads\\codigo.txt";



    int Matriz[][]= {
        /*       0    1    2    3    4    5    6    7    8    9    10    11    12    13    14    15    16    17    18    19    20    21    22    23    24    25    26    27    28   */
        /*       l    @    _    d    +    -    *    /    ^    <    >     =     !     &     |     (     )     {     }     ,     ;     "     eb    tab   nl    .     eof   oc    rt   */
        /*0*/   {1,   1,   1,   2,   103, 104, 105, 5,   107, 8,   9,    10,   11,   12,   13,   117,  118,  119,  120,  124,  125,  14,   0,    0,    0,    505,  0,    505,  0  },
        /*1*/   {1,   1,   1,   1,   100, 100, 100, 100, 100, 100, 100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100},
        /*2*/   {101, 101, 101, 2,   101, 101, 101, 101, 101, 101, 101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  3,    101,  101,  101},
        /*3*/   {500, 500, 500, 4,   500, 500, 500, 500, 500, 500, 500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500},
        /*4*/   {102, 102, 102, 4,   102, 102, 102, 102, 102, 102, 102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102},
        /*5*/   {106, 106, 106, 106, 106, 106, 6,   106, 106, 106, 106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106},
        /*6*/   {6,   6,   6,   6,   6,   6,   6,   7,   6,   6,   6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    501,  6,    6  },
        /*7*/   {6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    501,  6,    6  },
        /*8*/   {108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108,  110,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108},
        /*9*/   {109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109,  111,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109,  109},
        /*10*/  {123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123,  112,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123,  123},
        /*11*/  {116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116,  113,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116,  116},
        /*12*/  {502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502,  502,  502,  114,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502},
        /*13*/  {503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503,  110,  503,  503,  115,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503},
        /*14*/  {14,  14,  14,  14,  14,  14,  14,  14,  14,  14,  14,   110,  14,   14,   14,   14,   14,   14,   14,   14,   14,   122,  14,   14,   504,   504,  14,  14,   504}
        
    };



    String palReservadas[][]={
        /*            0          1         */
        /*0 */   {"break",  "201"},
        /*1 */   {"if",     "202"},
        /*2 */   {"else",   "203"},
        /*3 */   {"main",   "204"},
        /*4 */   {"while",  "205"},
        /*5 */   {"goto",   "206"},
        /*6 */   {"print",  "207"},
        /*7 */   {"new",    "208"},
        /*8 */   {"float",  "209"},
        /*9 */   {"int",    "210"},
        /*10*/   {"false",  "211"},
        /*11*/   {"true",   "212"},
        /*12*/   {"string", "213"},
        /*13*/   {"getvalue", "214"},
        /*14*/   {"bool", "215"},
    };


    String errores[][]={
        /*            0          1         */
        /*0 */   {"Se espera un digito",              "500"},
        /*1 */   {"Se espera cierre comentario",      "501"},
        /*2 */   {"Se espera '&'",                    "502"},
        /*3 */   {"Se espera '|'",                    "503"},
        /*4 */   {"Se espera cierre de cadena",       "504"},
        /*5 */   {"Simbolo no valido",                "505"},
    };

    RandomAccessFile file = null;

    List<String> erroreslexicos = new ArrayList<>();

    public lexico(){
        try{
            file = new RandomAccessFile(archivo,"r");
            while(caracter != -1){   //leer caracter por caracter mientras no sea fin de archivo
                caracter = file.read();

                if(Character.isLetter( ((char)caracter))) {
                    columna = 0;

                }else if(Character.isDigit((char)caracter)) {
                    columna = 3;
                }else{
                    switch((char)caracter){
                        case '@': columna = 1;
                        break;
                        case '_': columna = 2;
                        break;
                        case '+': columna = 4;
                        break;
                        case '-': columna = 5;
                        break;
                        case '*': columna = 6;
                        break;
                        case '/': columna = 7;
                        break;
                        case '^': columna = 8;
                        break;
                        case '<': columna = 9;
                        break;
                        case '>': columna = 10;
                        break;
                        case '=': columna = 11;
                        break;
                        case '!': columna = 12;
                        break;
                        case '&': columna = 13;
                        break;
                        case '|': columna = 14;
                        break;
                        case '(': columna = 15;
                        break;
                        case ')': columna = 16;
                        break;
                        case '{': columna = 17;
                        break;
                        case '}': columna = 18;
                        break;
                        case ',': columna = 19;
                        break;
                        case ';': columna = 20;
                        break;
                        case '"': columna = 21;
                        break;
                        case ' ' : columna = 22; // espacio en blanco
                        break;
                        case 9 : columna = 23; //tab
                        break;
                        case 10 : {columna = 24; //salto de renglon o enter
                            columna = 24;
                            numRegion = numRegion+1;
                        }
                        break;
                        case 13: columna = 28;
                        break;
                        case '.': columna = 25;
                        break;
                        case 3 : columna = 26; //eof
                        break;
                        default : columna = 27; //otra cosa
                        break;

                    } //switch cases
                } //if

                valorMT = Matriz[estado][columna];

                if(valorMT < 100){ //cambiar estado
                    estado = valorMT;

                    if(estado == 0){
                        lexema= "";
                    }else{
                        lexema = lexema+(char) caracter;
                    }
                }else if(valorMT >=100 && valorMT<500){ //estado final
                    if(valorMT == 100){
                        ValidarSiEsPalabraReservada();
                    }

                    if(valorMT ==100||valorMT ==101||valorMT==102||valorMT ==106||valorMT==123||
                    valorMT==109||valorMT==108||valorMT==116||valorMT>=200){
                        file.seek(file.getFilePointer()-1); //i--
                    }else{
                        lexema = lexema + (char) caracter;
                    }

                    insertarNodo();
                    estado=0;
                    lexema="";


                }else{ //estado error
                    guardarmensajeerror();
                    caracter = file.read(); // Avanza al siguiente carácter
                    estado = 0; // Restablece el estado

                    //break;
                }


            } //fin del while
            imprimirNodos();
            imprimirErroresLexicos();
            sintaxis();
            

        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(file!=null){
                    file.close();
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

private void guardarmensajeerror(){
    if(caracter !=  -1 && valorMT>=500){
        for(String[] errore : errores){
            if(valorMT == Integer.valueOf(errore[1])){
                erroreslexicos.add("El error encontrado es "+ errore[0]+ " error "+ valorMT + " caracter "+ caracter + " en el renglon "+ numRegion);
            }
        }
        ErroEncontrado = true;
    }
}

private void imprimirErroresLexicos() {
    // Después de imprimir los nodos, imprime todos los errores léxicos recolectados.
    if (!erroreslexicos.isEmpty()) {
        for (String error : erroreslexicos) {
            System.out.println(error);
        }
    }
}

private void insertarNodo(){
    nodo nodo = new nodo(lexema,valorMT,numRegion);

    if(cabeza == null){
        cabeza = nodo;
        p=cabeza;
    }else{
        p.sig = nodo;
        p= nodo;
    }
}

private void ValidarSiEsPalabraReservada(){
    for(String[] palReservada : palReservadas){
        if(lexema.equals(palReservada[0])){
            valorMT = Integer.valueOf(palReservada[1]);
        }
    }
}

private void imprimirNodos(){
    p=cabeza;
    while (p!=null){
        System.out.println(p.lexema + " " + p.token + " " + p.region);
        p = p.sig;
    }

}




private void sintaxis() {
    p = cabeza;
    while (p != null) {
        if (p.token == 204) {
            p = p.sig;
            if (p.token == 117) {
                p = p.sig;
                if (p.token == 118) {
                    p = p.sig;
                    if (p.token == 119) {
                        p = p.sig;

                        if (!verificarVariables()) {
                            break;
                        }

                        while(Statements()){

                        }
                        
                        if (p == null) {
                            System.out.println("Se espera '}' al final del código");
                        } else if (p.token == 120 ) {
                            System.out.println("Análisis sintáctico terminado " );
                        } else if (p.sig == null) {
                            System.out.println("Se espera '}' al final del código");
                        }

                    } else {
                        System.out.println("Se espera '{' en la linea "+ p.region);
                    }
                } else {
                    System.out.println("Se espera cierre de paréntesis en la linea "+ p.region);
                }
            } else {
                System.out.println("Se espera inicio de paréntesis en la linea "+ p.region);
            }
        } else {
            System.out.println("Se espera la palabra 'main' en la linea "+ p.region);
        }
        break;
    }
}

private boolean verificarVariables() {
    if (p.token == 208) {
        p = p.sig;

       
        if (p.token == 209 || p.token == 213 || p.token == 210 || p.token == 215) {
            p = p.sig;

           
            if (p.token == 100) {
                p = p.sig;

               
                if (p.token == 125) {
                    p = p.sig;

                   
                    if (p.token == 208) {
                        return verificarVariables();
                    } else {
                       
                        return true;
                    }
                } else {
                  
                    if (p.token == 124) {
                        p = p.sig;

                       
                        return verificarIdentificador();
                    } else {
                        System.out.println("Se espera un punto y coma o una coma en la línea " + p.region);
                        return false;
                    }
                }
            } else {
                System.out.println("Se espera un identificador en la línea " + p.region);
                return false;
            }
        } else {
            System.out.println("Se espera un tipo de variable en la línea " + p.region);
            return false;
        }
    } else {
        System.out.println("Se espera la palabra 'new' en la línea " + p.region);
        return false;
    }
}

private boolean verificarIdentificador() {
    if (p.token == 100) {
        p = p.sig;

        
        if (p.token == 124) {
            p = p.sig;
            return verificarIdentificador();
        } else if (p.token == 125) {
            p = p.sig;
            return true;
        } else {
            System.out.println("Se espera un punto y coma después del identificador en la línea " + p.region);
            return false;
        }
    } else {
        System.out.println("Se espera un identificador después de la coma en la línea " + p.region);
        return false;
    }
}

    private boolean Factor() {
        if (p.token == 100 || p.token == 101 || p.token == 102) {
            p = p.sig;
            return true;
        } else if (p.token == 117) {
            p = p.sig;
            if (ExpresionSimple()) {
                if (p.token == 118) {
                    p = p.sig;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (p.token == 11) {
            p = p.sig;
            return Factor();
        } else {
            return false;
        }
    }

    private boolean Termino() {
        if (Factor()) {
            if (p.token == 105 || p.token == 106 || p.token == 114) {
                p = p.sig;
                if (!Factor()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean ExpresionSimple() {
        if (Termino()) {
            if (p.token == 103 || p.token == 104 || p.token == 115) {
                p = p.sig;
                if (!Termino()) {
                    return false;
                }
            }
            return true;
        } else if (p.token == 103 || p.token == 104) {
            p = p.sig;

            if (Termino()) {
                if (p.token == 103 || p.token == 104 || p.token == 115) {
                    p = p.sig;
                    if (!Termino()) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean ExpresionCondicional() {
        if (ExpresionSimple()) {
            if (p.token == 108 || p.token == 109 || p.token == 110 ||
                p.token == 111 || p.token == 112 || p.token == 113) {
                p = p.sig;
                if (!ExpresionSimple()) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    
    private boolean Statements() {
        if(p == null){
            return false;
        }
        if (p.token == 202) {
            p = p.sig;
            if (p.token == 117) { 
                p = p.sig;
                if (!ExpresionCondicional()) {
                    System.out.println("Se espera una expresión condicional después de '(' en el if en la linea "+ p.region);
                    return false;
                }
                if (p.token == 118) { 
                    p = p.sig;
                    if (p.token == 119) { 
                        p = p.sig;
                        if (Statements()) {
                            while (Statements()) {
                               
                            }
                        }
                        if (p.token == 120) { 
                            p = p.sig;
                            if (p != null){
                                if (p.token == 203) { 
                                    p = p.sig;
                                    if (p.token == 119) { 
                                        p = p.sig;
                                        if (Statements()) {
                                            while (Statements()) {
                                                
                                            }
                                        }
                                        if (p.token == 120) { 
                                            p = p.sig;
                                        } else {
                                            System.out.println("Se espera '}' después del bloque del else en la linea "+ p.region);
                                            return false;
                                        }
                                    }
                                }
                            }
                            return true;
                        } else {
                            System.out.println("Se espera '}' después del bloque del if en la linea "+ p.region);
                            return false;
                        }
                    } else {
                        System.out.println("Se espera '{' después de '(' en el if en la linea "+ p.region);
                        return false;
                    }
                } else {
                    System.out.println("Se espera ')' después de la expresión condicional en el if en la linea "+ p.region);
                    return false;
                }
            } else {
                System.out.println("Se espera '(' después de 'if' en la linea "+ p.region);
                return false;
            }
        } else if (p.token == 205) {
            p = p.sig;
            if (p.token == 117) { 
                p = p.sig;
                if (!ExpresionCondicional()) {
                    System.out.println("Se espera una expresión condicional después de '(' en el while statement en la línea " + p.region);
                    return false;
                }
                if (p.token == 118) { 
                    p = p.sig;
                    if (p.token == 119) { 
                        p = p.sig;
                        if (p.token == 120) { 
                            p = p.sig;
                            return true;
                        }
                        while (Statements()) {
                            
                        }
                        if (p.token == 120) { 
                            p = p.sig;
                            return true;
                        } else {
                            System.out.println("Se espera '}'  en el while en la línea " + p.region);
                            return false;
                        }
                    } else {
                        System.out.println("Se espera '{' después de '(' en el while en la línea " + p.region);
                        return false;
                    }
                } else {
                    System.out.println("Se espera ')' después de la expresión condicional en el while en la línea " + p.region);
                    return false;
                }
            } else {
                System.out.println("Se espera '(' después de 'while' en el while statement en la línea " + p.region);
                return false;
            }
        } else if (p.token == 207) { 
            p = p.sig;
            if (p.token == 117) { 
                p = p.sig;
                if (p.token == 100) {
                    p = p.sig;
                    while (p.token == 124) { 
                        p = p.sig;
                        if (p.token == 100) { 
                            p = p.sig;
                        } else {
                            System.out.println("Se espera un id después de ',' en el print statement");
                            return false;
                        }
                    }
                    if (p.token == 118) {
                        p = p.sig;
                        if (p.token == 125) { 
                            p = p.sig;
                            return true;
                        } else {
                            System.out.println("Se espera ';' después del print statement");
                            return false;
                        }
                    } else {
                        System.out.println("Se espera ')' después de los argumentos del print en la linea " + p.region);
                        return false;
                    }
                } else {
                    System.out.println("Se espera un id después de '(' en la linea " + p.region);
                    return false;
                }
            } else {
                System.out.println("Se espera '(' después de 'print' en la linea " + p.region);
                return false;
            }
        } else if (p.token == 100) { 
            p = p.sig;
            if (p.token == 123) { 
                p = p.sig;
                 
                if(p.token == 214){
                    p = p.sig;
                    if(p.token == 117){
                        p = p.sig;
                        if(p.token == 118){
                            p = p.sig;
                            if (p.token == 125) { 
                                p = p.sig;
                                return true;
                                
                            } else {
                                System.out.println("Se espera ';'  en la linea " + p.region);
                                return false;
                            }
                        }else{
                            System.out.println("Se espera un ')' despues del '(' en la linea " + p.region);
                            return false; 
                        }
                    }else{
                        System.out.println("Se espera un '(' despues del getvalue en la linea " + p.region);
                        return false; 
                    }
                }
                if (ExpresionSimple()) {
                    if (p.token == 125) {
                        p = p.sig;
                        return true;
                        
                    } else {
                        System.out.println("Se espera ';'  en la linea " + p.region);
                        return false;
                    }
                }
                else{
                    System.out.println("Se espera expresion simple o un getvalue en la linea " + p.region);
                    return false; 
                }
            } else{
                System.out.println("Se espera un '=' en la linea " + p.region);
                    return false;
            }
        } else {
            return false;
        }
    }
    
    
}

