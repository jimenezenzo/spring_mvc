package ar.edu.unlam.tallerweb1.configuraciones;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import java.util.Comparator;

public class SortByDateTimeHistorias implements Comparator<CitaHistoria> {
    // Se utiliza para ordenar en forma ascendente
    public int compare(CitaHistoria a, CitaHistoria b)
    {
        return a.getFechaRegistro().compareTo(b.getFechaRegistro());
    }
}