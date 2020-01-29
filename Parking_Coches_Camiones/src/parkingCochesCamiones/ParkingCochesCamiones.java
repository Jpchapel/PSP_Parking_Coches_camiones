/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parkingCochesCamiones;

/**
 *
 * @author Stream
 */
public class ParkingCochesCamiones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numeroPlazas = 15;
        Parking parking = new Parking(numeroPlazas);

        int numeroCoches = 20;
        int numeroCamiones = 10;        

        for (int i = 0; i < numeroCoches; i++) {
            new Coche(i + 1, parking).start();
        }
        
        for (int i = 0; i < numeroCamiones; i++) {
            new Camion(i + 100, parking).start();
        }
    }
    
}
