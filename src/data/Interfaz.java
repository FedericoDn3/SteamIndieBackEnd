package data;

import java.sql.Date;
import java.util.List;

import model.Cat;
import model.Compra;
import model.Estado;
import model.Instanciaoferta;
import model.Juego;
import model.Oferta;
import model.Tag;
import model.Usuario;

public interface Interfaz {

	//Control Usuario
    
    public Usuario Login(String nick , String pass) throws Exception;
    
    public void Registro(String nom, String apell,Date fnac,byte[] img, String nick ,String email, String pass,String sexo,String rol) throws Exception;

    //SearchBar
    
    public List<Usuario> BusqUsu(String Q);    
    
    public List<Juego> BusqJue(String Q ,int B);    
    
    public List<Tag> encontrarTodosTags();
    
    public List<Cat> encontrarTodosCats();
    
	// USUARIOS
	
	public List<Usuario> encontrarTodosUsuarios();
    
    public Usuario encontrarUsuario(Usuario usuario);
    
    public void insertarUsuario(Usuario usuario);
    
    public void actualizarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Usuario usuario);

//  Estados  
  
    public List<Estado> listarEstados(int idUsu); 
    
    public void insertarEstado(Estado estado);
    
// Juegos    
      
    public List<Juego> encontrarTodosJuegos();

    public Juego encontrarJuego(Juego juego);
    
    public void insertarJuego(Juego j);
        
    public void reportarJuego(Juego j);
    
    public Juego detalleJuego(Juego juego);
    
    public void dereportarJuego(Juego j);
    
    public void banearJuego(Juego juegooriginal);
// OFERTAS
    
    public List<Oferta> encontrarTodasOfertas();
    
    public Oferta encontrarOferta(Oferta oferta);
    
    public void insertarOferta(Oferta oferta);
    
    public void actualizarOferta(Oferta oferta);
    
    public void eliminarOferta(Oferta oferta);
    
//	INSTANCIA OFERTA
    
    public List<Instanciaoferta> listarJuegosEnOferta();
    
    public void insertarJuegosAoferta(int idoferta, String[] arrayidjuegos);
    
 // COMPRAS
    
    public Compra encontrarCompra(Compra compra);
    
    public void comprarJuego(int idjuego, int idUsu );
    
    public void calificarComentarJuego(Compra compraOriginal, String comcom, String calcom);
    
    public List<Compra> listarComprasUsuario(int idUsu);
        
    public List<String> listaComentariosJuego(int idJuego);
    
    public List<String> listaCalificacionesJuego(int idJuego);
    
    public void reportarComentario(Compra compraOriginal);
    
    public List<Compra> listaComentCalifJuego(int idJuego);
    
    // ESTADISTICAS
    
    public String estadisticaVendedor(int idUsu);
    
    
}
