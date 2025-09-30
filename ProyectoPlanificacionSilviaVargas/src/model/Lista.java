package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Lista implements Elemento {

    public int ID;
    public ArrayList<String> lista;

    public Lista() {
    }

    public Lista(int ID, ArrayList<String> lista) {
        this.ID = ID;
        this.lista = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Lista{ID=%d, lista=%s}", ID, lista);
    }

    @Override
    public int getID() {
        return 0;
    }
}
