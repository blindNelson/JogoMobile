package com.example.jogomobille.game;

public class Coordenada {
    //Declara as variáveis
    int x, y;

    //Construtor que recebe x e y
    public Coordenada(int x, int y)
    {
        //Define o valor de x e y
        this.x = x;
        this.y = y;
    }

    //Pega o valor de x
    public int getX() {
        return x;
    }

    //Pega o valor de y
    public int getY() {
        return y;
    }

    //Define o valor de x
    public void setX(int x) {
        this.x = x;
    }

    //Define o valor de y
    public void setY(int y) {
        this.y = y;
    }

    //Método obrigatório toString()
    @Override
    public String toString()
    {
        //Retorna as cordenadas x e y
        return "[" + this.x + "; " + this.y + "]";
    }

    //Método obrigatório equals()
    @Override
    public boolean equals(Object o) {
        //Compara as cordenadas
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada coordenada = (Coordenada) o;
        return x == coordenada.x && y == coordenada.y;
    }

    //Método obrigatório hashCode()
    @Override
    public int hashCode() {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + this.x;
        ret = ret*7/*primo*/ + this.y;

        if (ret<0)
            ret=-ret;
        return ret;
    }

    //Construtor de copia
    public Coordenada(Coordenada cord)
    {
        //Define o valor de x e y
        this.x = cord.x;
        this.y = cord.y;
    }

    //Metodo Clone
    public Object clone ()
    {
        Coordenada ret=null;

        try
        {
            ret = new Coordenada(this);
        }
        catch(Exception ignored)
        {}

        return ret;
    }
}

