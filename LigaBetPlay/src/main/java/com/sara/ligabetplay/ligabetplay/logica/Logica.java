/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sara.ligabetplay.ligabetplay.logica;

import com.sara.ligabetplay.ligabetplay.models.Enfrentamientos;
import com.sara.ligabetplay.ligabetplay.models.Equipos;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author camper
 */
public class Logica {

    public static ArrayList<Equipos> equipos = new ArrayList<>();
    public static ArrayList<Enfrentamientos> partidos = new ArrayList();

    public static void Logica() {
        Scanner sc = new Scanner(System.in);

        int rta;
        do {

            System.out.println("");
            System.out.println("-   MENU DE OPCIONES   -");
            System.out.println("");
            System.out.println("1. Registro de equipos\n2. Registro de fecha\n3. Reportes\n4. Salir\nIngresa la selección");
            rta = sc.nextInt();

            switch (rta) {

                case 1 -> {
                    System.out.println("");
                    System.out.println("-----------------------------");
                    System.out.println("-----REGISTRO DE EQUIPOS-----");
                    System.out.println("-----------------------------");
                    System.out.println("");

                    String opcion;
                    do {
                        System.out.println("Desea ingresar un equipo SI o NO");
                        opcion = sc.next();

                        if (opcion.equalsIgnoreCase("si")) {
                            Equipos myEquipo = new Equipos();

                            System.out.println("Ingrese nombre del equipo: ");
                            myEquipo.setNombreE(sc.next());

                            equipos.add(myEquipo);

                        }
                    } while (!opcion.equalsIgnoreCase("NO"));

                }

                case 2 -> {
                    System.out.println("Ingrese la fecha del partido (DD/MM/YYYY):");
                    String fecha = sc.next();

                    Enfrentamientos partido = new Enfrentamientos();
                    partido.setFecha(fecha);
                    partidos.add(partido);

                    System.out.println("Determina los equipos");
                    System.out.println("Nombre del equipo local: ");
                    String local = sc.next();
                    System.out.println("Nombre del equipo visitante");
                    String visit = sc.next();

                    if (Validar(local) && Validar(visit)) {
                        System.out.println("Realizando validacion de equipos...");
                        System.out.println("");
                        System.out.println("");
                    } else {
                        if (!Validar(local)) {
                            System.out.println("Equipo local no existente");
                        }
                        if (!Validar(visit)) {
                            System.out.println("Equipo visitante no existante");
                        }
                    }

                    int marc1;
                    int marc2;
                    
                    System.out.println("Marcador final de " + local);
                    marc1 = sc.nextInt();
                    partido.setMarc1(marc1);

                    System.out.println("Marcador final de " + visit);
                    marc2 = sc.nextInt();
                    partido.setMarc2(marc2);

                    registrarFecha(local, visit, marc1, marc2);
                }

                case 3 -> {
                    equipos.forEach(equipo -> {
                        System.out.println(MessageFormat.format("\nNombre equipo: {0}\nPartidos jugados: {1}\nPartidos ganados: {2}\nPartidos perdidos: {3}\nPartidos empatados: {4}\nGoles a favor: {5}\nGoles en contra: {6}\nTotal de puntos: {7}", equipo.getNombreE(), equipo.getPJ(), equipo.getPG(), equipo.getPP(), equipo.getPE(), equipo.getGF(), equipo.getGC(), equipo.getTP()));
                    });

                }
                case 4 ->
                    System.out.println("Gracias por elegir siempre nuestro sistema");
                default -> {
                    if (rta > 4) {
                        System.out.println("Por favor ingresa un numero valido");
                    }
                }
            }
        } while (rta != 4);
    }

    public static boolean Validar(String nombreE) {
        for (Equipos equipo : equipos) {
            if (equipo.getNombreE().equalsIgnoreCase(nombreE)) {
                return true;
            }
        }
        return false;
    }

    public static void registrarFecha(String local, String visit, int marc1, int marc2) {
        Equipos equipoLocal = Encontrar(local);
        Equipos equipoVisitante = Encontrar(visit);

        if (marc1 > marc2) {
        
            equipoLocal.setPJ(+1);
            equipoLocal.setPG(+1);
            equipoLocal.setGF(marc1);
            equipoLocal.setGC(marc2);
            equipoLocal.setTP(+3);

            equipoVisitante.setPP(+1);
            equipoVisitante.setPJ(+1);
            equipoVisitante.setGF(marc2);
            equipoVisitante.setGC(marc1);

        } else if (marc2 > marc1) {

            equipoVisitante.setPJ(+1);
            equipoVisitante.setPG(+1);
            equipoVisitante.setGF(marc2);
            equipoVisitante.setGC(marc1);
            equipoVisitante.setTP(+3);

            equipoLocal.setPP(+1);
            equipoLocal.setPJ(+1);
            equipoLocal.setGF(marc1);
            equipoLocal.setGC(marc2);
            

        } else if (marc1 == marc2) {
            System.out.println("EMPATE");
            equipoLocal.setPE(+1);
            equipoVisitante.setPE(+1);
            equipoLocal.setTP(+1);
            equipoVisitante.setTP(+1);
        }
    }

    public static Equipos Encontrar(String nombreE) {
        for (Equipos equipo : equipos) {
            if (equipo.getNombreE().equals(nombreE)) {
                return equipo;
            }
        }
        return null;
    }

}
