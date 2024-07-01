package nassekine.spartak.pruebapreexamen;

public class Item {

    private String rutaImagen;
    private String titulo;
    private String descripcion;
    private String estreno;
    private boolean favorito;

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Item(){

    }
    public Item(String rutaImagen, String titulo, String descripcion, String estreno) {
        this.rutaImagen = rutaImagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estreno = estreno;

    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }
}

