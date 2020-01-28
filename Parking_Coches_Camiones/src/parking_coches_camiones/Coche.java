/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parking_coches_camiones;

/**
 *
 * @author Stream
 */
public class Coche extends Vehiculo{

    public Coche(int id, Parking parking) {
        super(id, parking);
    }

    @Override
    public String toString() {
        return "Coche " + this.id;
    }
}
