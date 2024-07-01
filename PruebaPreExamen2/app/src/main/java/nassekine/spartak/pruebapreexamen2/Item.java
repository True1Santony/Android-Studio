package nassekine.spartak.pruebapreexamen2;

public class Item {

    private String rutaImagen;
    private String titulo;
    private String descripcion;
    private String estreno;
    private int id;
    private boolean favorito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Item(){

    }
    public Item(String rutaImagen, String titulo, String descripcion, String estreno, int id) {
        this.rutaImagen = rutaImagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estreno = estreno;
        this.favorito = false;
        this.id = id;

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
