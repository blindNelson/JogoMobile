package com.example.jogomobille.utils;

import java.lang.reflect.*;

public class Pilha <X> implements Cloneable
{
    private Object[]  elemento; // private X[] elemento;
    private final int tamanhoInicial;
    private int       ultimo=-1; //vazio

    public Pilha (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");

        this.elemento       = new Object [tamanho]; //this.elemento=new X [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (float fator)
    {
        // X[] novo = new X [Math.round(this.elemento.length*fator)];
        Object[] novo = new Object [Math.round(this.elemento.length*fator)];

        if (this.ultimo + 1 >= 0)
            System.arraycopy(this.elemento, 0, novo, 0, this.ultimo + 1);

        this.elemento = novo;
    }

    private X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored)
        {}

        return ret;
    }

    public void guardeUmItem (X x) throws Exception // LIFO
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2.0F);

        this.ultimo++;

        if (x instanceof Cloneable)
            this.elemento[this.ultimo]=meuCloneDeX(x); // x.clone();
        else
            this.elemento[this.ultimo]=x;
    }

    public X recupereUmItem () throws Exception // LIFO
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.ultimo] instanceof Cloneable)
            ret = meuCloneDeX((X)this.elemento[this.ultimo]);
        else
            ret = (X)this.elemento[this.ultimo];

        return ret;
    }

    public void removaUmItem () throws Exception // LIFO
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length>this.tamanhoInicial &&
                this.ultimo+1<=Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }

    public boolean isCheia ()
    {
        return this.ultimo+1==this.elemento.length;
    }

    public boolean isVazia ()
    {
        return this.ultimo==-1;
    }

    public String toString ()
    {
        String ret = (this.ultimo+1) + " elemento(s)";

        if (this.ultimo!=-1)
            ret += ", sendo o ultimo "+this.elemento[this.ultimo];

        return ret;
    }

    // equals compara this e obj
    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if(this.ultimo!=pil.ultimo)
            return false;

        for(int i=0 ; i<=this.ultimo;i++)
            if(!this.elemento[i].equals (pil.elemento[i]))
                return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + Integer.valueOf(this.ultimo).hashCode();
        ret = ret*7/*primo*/ + Integer.valueOf(this.tamanhoInicial).hashCode();

        for (int i=0; i<=this.ultimo; i++)
            ret = ret*7/*primo*/ + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    // construtor de copia
    public Pilha (Pilha<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo ausente");

        this.ultimo         = modelo.ultimo;
        this.tamanhoInicial = modelo.tamanhoInicial;

        //this.elemento = new X [modelo.elemento.length];
        this.elemento = new Object[modelo.elemento.length];

        System.arraycopy(modelo.elemento, 0, this.elemento, 0, modelo.elemento.length);
    }

    public Object clone ()
    {
        Pilha<X> ret=null;

        try
        {
            ret = new Pilha(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}
