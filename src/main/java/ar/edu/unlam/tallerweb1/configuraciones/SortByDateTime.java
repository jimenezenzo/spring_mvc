package ar.edu.unlam.tallerweb1.configuraciones;

import ar.edu.unlam.tallerweb1.modelo.CitaDomicilio;

import java.util.Comparator;

public class SortByDateTime implements Comparator<CitaDomicilio> {
    // Se utiliza para ordenar en forma ascendente
    public int compare(CitaDomicilio a, CitaDomicilio b)
    {
        return a.getFechaRegistro().compareTo(b.getFechaRegistro());
    }
}
