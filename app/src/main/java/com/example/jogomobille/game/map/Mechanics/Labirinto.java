package com.example.jogomobille.game.map.Mechanics;

import com.example.jogomobille.utils.Coordenada;
import com.example.jogomobille.utils.Pilha;

import java.util.ArrayList;

public class Labirinto {

    int largura, altura;
    Coordenada entrada, saida;
    byte[][] labirinto;

    public Labirinto(int fase, int dificuldade, String tipo) throws Exception {
        if(fase < 0)
            fase *= -1;

        largura = fase * 2 + 1;
        altura  = fase * 2 + 1;

        labirinto = new byte[altura][largura];

        for(int i=0; i < altura; i++)
            for(int j=0; j < largura; j++)
                labirinto[i][j] = 1;

        switch (tipo)
        {
            case "fixo" :
                entrada = new Coordenada(largura / 2, 0);
                saida = new Coordenada(largura / 2, altura - 1);
                break;
            case "randomico":
                entrada = new Coordenada((int) (Math.random() * (largura / 2)) * 2 + 1, 0);
                saida = new Coordenada((int) (Math.random() * (largura / 2)) * 2 + 1, altura - 1);
                break;
            default:
                entrada = new Coordenada(1, 0);
                saida = new Coordenada(largura - 2, altura - 1);
                break;
        }

        Pilha<Coordenada> caminho = new Pilha<>(largura * altura);
        Coordenada atual = new Coordenada(entrada);

        caminho.guardeUmItem(atual);
        labirinto[entrada.getY()][entrada.getX()] = 3; //Abrir a entrada
        atual = new Coordenada(entrada.getX(),entrada.getY()+1);
        caminho.guardeUmItem(atual);
        labirinto[atual.getY()][atual.getX()] = 0;

        byte nUnico = 0;
        do
        {
            ArrayList<Coordenada> fila = new ArrayList<>(3);

            if(cavavel(atual.getX() - 2, atual.getY()))
                fila.add(new Coordenada(atual.getX() - 2, atual.getY()));

            if(cavavel(atual.getX() + 2, atual.getY()))
                fila.add(new Coordenada(atual.getX() + 2, atual.getY()));

            if(cavavel(atual.getX(), atual.getY() - 2))
                fila.add(new Coordenada(atual.getX(), atual.getY() - 2));

            if(cavavel(atual.getX(), atual.getY() + 2))
                fila.add(new Coordenada(atual.getX(), atual.getY() + 2));

            if(fila.size() > 1)
                nUnico = 0;
            else
                nUnico++;

            if(nUnico > dificuldade)
            {
                byte rand = (byte)(4 * Math.random());
                switch (rand)
                {
                    case 0: if(cavavel(atual.getX()+1,atual.getY()))
                    { cavar(new Coordenada(atual.getX()+1,atual.getY())); break;}
                    case 1: if(cavavel(atual.getX()-1,atual.getY()))
                    { cavar(new Coordenada(atual.getX()-1,atual.getY())); break;}
                    case 2: if(cavavel(atual.getX(),atual.getY()+1))
                    { cavar(new Coordenada(atual.getX(),atual.getY()+1)); break;}
                    case 3: if(cavavel(atual.getX(),atual.getY()-1))
                    { cavar(new Coordenada(atual.getX(),atual.getY()-1)); break;}
                }
                nUnico = 0;
            }

            if (fila.isEmpty())//Regressao
            {
                caminho.removaUmItem();
                caminho.removaUmItem();
                if(caminho.isVazia())
                    break;

                atual = caminho.recupereUmItem();
                continue;
            }
            int escolha = (int) (Math.random() * fila.size());

            Coordenada entre = new Coordenada((atual.getX() + fila.get(escolha).getX())/2,
                    (atual.getY() + fila.get(escolha).getY())/2);
            cavar(entre);
            cavar(fila.get(escolha));

            caminho.guardeUmItem(entre);
            caminho.guardeUmItem(fila.get(escolha));

            atual = fila.get(escolha);

        }while (true);
        labirinto[saida.getY()][saida.getX()] = 4;
    }

    public boolean cavavel(int x, int y) {

        try { if(x <= 0 || x >= largura-1) return false; }
        catch (IndexOutOfBoundsException exception) { return false; }
        try { if(y <= 0 || y >= altura-1)  return false; }
        catch (IndexOutOfBoundsException exception) { return false; }
        return labirinto[y][x] == 1;
    }

    public void cavar(Coordenada cord) {
        labirinto[cord.getY()][cord.getX()] = 0;
    }

    public byte[][] getLabirinto() {
        return labirinto;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.altura).append("\n").append(this.largura).append("\n");
        for(int i=0; i < altura; i++)
        {
            for(int j=0; j < largura; j++)
            {
                switch (this.labirinto[i][j])
                {
                    case 0: res.append(' '); break;
                    case 1: res.append('#'); break;
                    case 3: res.append('E'); break;
                    case 4: res.append('S'); break;
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}