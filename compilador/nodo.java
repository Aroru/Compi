class nodo{
    String lexema;
    int token;
    int region;
    nodo sig = null;

    nodo(String lexema,int token,int region){
        this.lexema=lexema;
        this.token=token;
        this.region=region;
    };
}