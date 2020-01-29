/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingCochesCamiones;

import java.util.LinkedList;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Parking {

    private final Vehiculo[] plazas;
    private final LinkedList<Vehiculo> cola = new LinkedList<>();

    public Parking(int numeroPlazas) {
        plazas = new Vehiculo[numeroPlazas];
    }

    private boolean esMiTurno(Vehiculo vehiculo) {
        return cola.peek() == vehiculo;
    }

    private boolean hayPlazasLibres(Vehiculo vehiculo) {
        if (vehiculo instanceof Coche) {
            for (int i = 0; i < plazas.length; i++) {
                if (plazas[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < plazas.length; i++) {
                if (plazas[i] == null && plazas[i + 1] == null && i != plazas.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private void aparcar(Vehiculo vehiculo) {
        int plaza = 0;

        while (plazas[plaza] != null) {
            plaza++;
        }

        plazas[plaza] = vehiculo;
        vehiculo.setPlaza(plaza);

        if (vehiculo instanceof Camion) {
            plazas[plaza] = vehiculo;
            plazas[plaza + 1] = vehiculo;
        } else {
            plazas[plaza] = vehiculo;
        }
    }

    public synchronized void entrar(Vehiculo vehiculo) throws InterruptedException {
        cola.add(vehiculo);

        System.out.println(vehiculo.toString() + " intentando entrar en el parking");

        while (!hayPlazasLibres(vehiculo) || !esMiTurno(vehiculo)) {
            wait();
        }

        cola.poll();

        aparcar(vehiculo);

        System.out.println(vehiculo.toString() + " aparcado en " + vehiculo.getPlaza());

        imprimir();
    }

    public synchronized void salir(Vehiculo vehiculo) {
        System.out.println(vehiculo.toString() + " saliendo del parking");

        plazas[vehiculo.getPlaza()] = null;
        if (vehiculo instanceof Camion) {
            plazas[vehiculo.getPlaza() + 1] = null;
        }

        imprimir();

        notifyAll();
    }

    private synchronized void imprimir() {
        System.out.print("Parking: ");
        String cochesAparcados = new String();

        for (int i = 0; i < plazas.length; i++) {
            cochesAparcados += (plazas[i] != null) ? plazas[i].getID() + " " : "0 ";
        }

        System.out.println(cochesAparcados);

        if (cola.size() != 0) {
            System.out.print("Cola de espera: ");
            for (int i = 0; i < cola.size(); i++) {
                System.out.print(cola.get(i).getID() + " ");
                if (i == cola.size() - 1) {
                    System.out.println("");
                }
            }
        }
    }

}
