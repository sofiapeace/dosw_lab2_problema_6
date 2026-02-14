package edu.dosw.lab.comportamiento.Reto6;

import java.util.*;
import java.util.stream.Collectors;

class Ticket {
    String descripcion;
    String nivel; // basico, intermedio, avanzado
    int prioridad; // 1: baja, 2: media, 3: alta

    public Ticket(String d, String n, int p) {
        this.descripcion = d; this.nivel = n; this.prioridad = p;
    }
}

abstract class SoporteHandler {
    protected SoporteHandler siguiente;
    protected String nivelEspecialidad;
    protected int maxPrioridad;

    public void setSiguiente(SoporteHandler siguiente) { this.siguiente = siguiente; }

    public String procesar(Ticket ticket) {
        if (puedeResolver(ticket)) {
            return "Técnico " + nivelEspecialidad + " resolvió el problema.";
        } else if (siguiente != null) {
            return siguiente.procesar(ticket);
        }
        return "Ningún técnico disponible. Ticket pendiente de escalamiento.";
    }

    private boolean puedeResolver(Ticket t) {
        // Lógica: Debe coincidir el nivel y no exceder su capacidad de prioridad
        return t.nivel.equalsIgnoreCase(this.nivelEspecialidad) && t.prioridad <= this.maxPrioridad;
    }
}
class SoporteBasico extends SoporteHandler {
    public SoporteBasico() { this.nivelEspecialidad = "basico"; this.maxPrioridad = 1; }
}

class SoporteIntermedio extends SoporteHandler {
    public SoporteIntermedio() { this.nivelEspecialidad = "intermedio"; this.maxPrioridad = 2; }
}

class SoporteAvanzado extends SoporteHandler {
    public SoporteAvanzado() { this.nivelEspecialidad = "avanzado"; this.maxPrioridad = 3; }
}
public class SoporteApp {
    public static void main(String[] args) {
        // Configurar la cadena
        SoporteHandler basico = new SoporteBasico();
        SoporteHandler intermedio = new SoporteIntermedio();
        SoporteHandler avanzado = new SoporteAvanzado();
        
        basico.setSiguiente(intermedio);
        intermedio.setSiguiente(avanzado);

        List<Ticket> tickets = Arrays.asList(
            new Ticket("Login", "basico", 1),
            new Ticket("Instalación", "intermedio", 3),
            new Ticket("Caída servidor", "avanzado", 2),
            new Ticket("Facturación", "basico", 3)
        );

        List<String> resultados = tickets.stream()
            .map(t -> basico.procesar(t))
            .collect(Collectors.toList());

        resultados.forEach(System.out::println);

        // ESTADÍSTICAS CON STREAMS
        long resueltos = resultados.stream().filter(r -> r.contains("resolvió")).count();
        long pendientes = resultados.stream().filter(r -> r.contains("pendiente")).count();
        
        System.out.println("\n--- Estadísticas ---");
        System.out.println("Tickets resueltos: " + resueltos);
        System.out.println("Tickets pendientes: " + pendientes);
    }
}