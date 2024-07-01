package nassekine.spartak.examen;

import java.io.Serializable;

public class Participante implements Serializable {

    private int id;
    private String nombre;
    private String imagen;
    private int gastosPagados;

    /**
     * Enlace a la API de Emoji
     * https://raw.githubusercontent.com/nataliainformatica/2024/main/emojis.json
     */

    /**
     * Enlace a la imagen que se  muestra por defecto al arrancar la aplicación
     //https://www.blendernation.com/wp-content/uploads/2024/02/Header-1-4.jpg*/


    public Participante(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.imagen ="https://www.blendernation.com/wp-content/uploads/2024/02/Header-1-4.jpg"  ;

    }

    public Participante() {
        this.nombre = ""; // mediante el setter
        this.id = 0;// mediante el setter
        this.imagen ="https://www.blendernation.com/wp-content/uploads/2024/02/Header-1-4.jpg"  ;

    }

    public Participante(int id, String nombre, String imagen, int gastosPagados) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.gastosPagados = gastosPagados;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getGastosPagados() {
        return gastosPagados;
    }

    public void setGastosPagados(int gastosPagados) {
        this.gastosPagados = gastosPagados;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }





}
