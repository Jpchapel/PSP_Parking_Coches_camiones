/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parkingCochesCamiones;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Vehiculo extends Thread{
    protected final int id;
    protected int plaza;
    protected final Parking parking;

    public Vehiculo(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public int getID() {
        return id;
    }

    public int getPlaza() {
        return plaza;
    }

    public void setPlaza(int plaza) {
        this.plaza = plaza;
    }

    @Override
    public void run() {
        try {
            
            Thread.sleep((long) (Math.random()*200));
            parking.entrar(this);
            Thread.sleep((long) (Math.random()*200));
            parking.salir(this);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getID();
    }
    
    
}
