package data;

import java.sql.Date;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Cat;
import model.Compra;
import model.Estado;
import model.Instanciaoferta;
import model.Juego;
import model.Oferta;
import model.Tag;
import model.Usuario;;

@Stateless
public class InterfazImpl implements Interfaz{

	@PersistenceContext (unitName = "SteamIndie")
	EntityManager em;	
	
	///// Control Usuarios
    @Override
    public Usuario Login(String nick , String pass) throws Exception {
    			
    	TypedQuery<Usuario> Qusu = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = :nick", Usuario.class);
    	Qusu.setParameter("nick", nick);
    	try {
    		Usuario Rusu = Qusu.getSingleResult();
    	    int idUsu = Rusu.getIdUsu();
        	Usuario U = new Usuario(idUsu);
        	Usuario Usu = encontrarUsuario(U);
    		if(Usu != null){
    			if(Usu.getPass().equals(pass)){
    				return Usu;
    			}else {
    				System.out.println("Pass no Igual");
    				}    			
    		}
    		return null;
    	}catch (Exception e) {
    	    System.out.println("El Usuario no existe");
    	 }
	return null;    			
	}

    @Override	
    public void Registro(String nom, String apell, Date fnac,byte[] img , String nick , String email, String pass, String sexo, String rol) throws Exception {

       	Query Qusu = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = :nick OR u.email = :email ");
    	Qusu.setParameter("nick", nick);
    	Qusu.setParameter("email", email);
    	int Usu = Qusu.getResultList().size();
    	try {

    		if(Usu == 0 ){
    	        Usuario UInsert	= new Usuario(apell,email,fnac,img,nick,nom,pass,sexo,rol);
    	    	em.persist(UInsert);
    	    	em.flush();
	        }else {
	        	System.out.println("Un usuario con esas credenciales ya existe");
	    	}
	

    	}catch (Exception e) {
    	    System.out.println("Error");
    	}   			
	}

    //SearchBar
 
    @Override
    public List<Usuario> BusqUsu(String Q) {

    	Query Qusu = em.createQuery("SELECT u FROM Usuario u WHERE u.nick LIKE :Q ");
    	Qusu.setParameter("Q", "%" + Q + "%");
    	List<Usuario> Usu = Qusu.getResultList();
		return Usu;
    	
    }

    @Override
    public List<Juego> BusqJue(String Q ,int B) {

    	if(B == 1) {
         	Query Qusu = em.createQuery("SELECT j FROM Juego j WHERE j.nomjuego LIKE :Q ");
        	Qusu.setParameter("Q", "%" + Q + "%");
        	List<Juego> Jue = Qusu.getResultList();
          return Jue;        	
        }else if (B == 2) {	
        	Query Qusu = em.createQuery("SELECT j FROM Juego j WHERE j.categoria LIKE :Q ");
        	Qusu.setParameter("Q", "%" + Q + "%");
        	List<Juego> Jue = Qusu.getResultList();
    	  return Jue;    		
    	}else if(B == 3) {		
    		Query Qusu = em.createQuery("SELECT j FROM Juego j WHERE j.tags LIKE :Q ");
        	Qusu.setParameter("Q", "%" + Q + "%");
        	List<Juego> Jue = Qusu.getResultList();
    	  return Jue;  
    	}
		return null;		
    }    
    
	@Override
	public List<Tag> encontrarTodosTags() {
        return em.createNamedQuery("Tag.findAll", Tag.class ).getResultList();
    }    
    
	@Override
	public List<Cat> encontrarTodosCats() {
        return em.createNamedQuery("Cat.findAll", Cat.class ).getResultList();
    }
	
	//	USUARIOS
	
	@Override
	public List<Usuario> encontrarTodosUsuarios() {
        return em.createNamedQuery("Usuario.encontrarTodosUsuarios", Usuario.class ).getResultList();
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {  	
       return em.find(Usuario.class, usuario.getIdUsu());
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        em.persist(usuario);
        em.flush();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        em.remove(em.merge(usuario));
    }
    //Estados
    
    @Override
    public List<Estado> listarEstados(int idUsu) {
    	Query q;
    	q = em.createQuery("SELECT e FROM Estado e WHERE e.perfil.idperf = :IdUsu ");
    	q.setParameter("IdUsu", idUsu);
    	List<Estado> Estados = q.getResultList();  
		return Estados;
    }
    
    public void insertarEstado(Estado estado) {
    	em.persist(estado);
        em.flush();
    }
    
    // Juegos
    
    public List<Juego> encontrarTodosJuegos() {
        return em.createNamedQuery("Juego.findAll", Juego.class ).getResultList();
    }

    public Juego encontrarJuego(Juego juego) {
    	return em.find(Juego.class, juego.getIdJuego());
    }
    
    public void insertarJuego(Juego juego) {
    	em.persist(juego);
        em.flush();
    }

    public void reportarJuego(Juego juegooriginal) {
    	String enrevision= String.valueOf(2);
    	juegooriginal.setBan(enrevision);
    	em.merge(juegooriginal);
    }

    public Juego detalleJuego(Juego juego) {
    	Juego j = em.find(Juego.class, juego.getIdJuego());
    	Juego juegoretornar = new Juego(j.getIdJuego(),j.getBan(), j.getCalificacion(), j.getCategoria(), j.getFechaL(),
    	j.getImagen(), j.getNomjuego(), j.getPrecio(), j.getDescripcion(),j.getRequisitos(),
    	j.getTags(), j.getUrl(),j.getVideo(), j.getUsuario());
    
    	return juegoretornar;
    }
    
    public void dereportarJuego(Juego juegooriginal) {
    	String normal= String.valueOf(1);
    	juegooriginal.setBan(normal);
    	em.merge(juegooriginal);
    }
    
    public void banearJuego(Juego juegooriginal) {
    	String baneado= String.valueOf(3);
    	juegooriginal.setBan(baneado);
    	em.merge(juegooriginal);
    }
	//	OFERTAS
    
	@Override
	public List<Oferta> encontrarTodasOfertas() {
        return em.createNamedQuery("Oferta.encontrarTodasOfertas", Oferta.class ).getResultList();
	}
	
	@Override
    public Oferta encontrarOferta(Oferta oferta) {
       return em.find(Oferta.class, oferta.getIdoferta());
    }

    @Override
    public void insertarOferta(Oferta oferta) {
        em.persist(oferta);
        em.flush();
    }
	
    @Override
    public void actualizarOferta(Oferta oferta) {
        em.merge(oferta);
    }
    
    @Override
    public void eliminarOferta(Oferta oferta) {
        em.remove(em.merge(oferta));
    }
    
    // INSTANCIA OFERTA
    
    @Override
    public List<Instanciaoferta> listarJuegosEnOferta(){    	
    	
    	Query q;
    	q = em.createQuery("SELECT i FROM Instanciaoferta i WHERE i.oferta.activa = ?1");
    	q.setParameter(1,1);
    	List<Instanciaoferta> juegosoferta = q.getResultList();     	
    	return juegosoferta;	
    }
    
    @Override
    public void insertarJuegosAoferta(int idoferta, String[] arrayidjuegos){
    	
    	int idjuego;

    	for (int i = 0; i < arrayidjuegos.length; i++){
    		idjuego = Integer.parseInt(arrayidjuegos[i]); 
    		Juego j = new Juego(idjuego);
        	Oferta o = new Oferta(idoferta);
        	Instanciaoferta io = new Instanciaoferta(j, o);
        	em.persist(io);
        	em.flush();
    	}
    }
    
    // COMPRAS
    
    @Override
    public Compra encontrarCompra(Compra compra) {
    	Compra c ; 
    	
    	if(em.find(Compra.class, compra.getIdCompra()) != null) {
    		c = em.find(Compra.class, compra.getIdCompra());
    	}else {
    		int idjue = compra.getJuego().getIdJuego();
    		int idusu = compra.getUsuario().getIdUsu();
    		Query q;
        	q = em.createQuery("SELECT c FROM Compra c WHERE c.juego.idJuego = ?1 AND c.usuario.idUsu = ?2");
        	q.setParameter(1, idjue);
        	q.setParameter(2, idusu);
        	
        	c = (Compra) q.getSingleResult();
    	}
    	
    	return c;
    }
    
    @Override
    public void comprarJuego(int idjuego, int idUsu ) {
    	Juego j = new Juego(idjuego);
    	Usuario u = new Usuario (idUsu);
//    	
//    	Date date = new Date();
//    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	
    	java.util.Date dt = new java.util.Date();
    	java.text.SimpleDateFormat sdf = 
    	     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String currentTime = sdf.format(dt);
    	String isban = String.valueOf(1);
    		
    	Compra c = new Compra(currentTime,isban, j, u);
    	em.persist(c);
    	em.flush();
    }
    
    @Override
    public void calificarComentarJuego(Compra compraOriginal, String comcom, String calcom) {
    	compraOriginal.setCalcom(calcom);
    	compraOriginal.setComcom(comcom);
    	em.merge(compraOriginal);
    }
    
    @Override
    public List<Compra> listarComprasUsuario(int idUsu){
    	Query q;
    	q = em.createQuery("SELECT c FROM Compra c WHERE c.usuario.idUsu = ?1");
    	q.setParameter(1, idUsu);
    	List<Compra> comprasUsuario = q.getResultList();     	
    	return comprasUsuario;	
    }
    
    public List<String> listaComentariosJuego(int idJuego){
    	Query q;
    	q = em.createQuery("SELECT c.comcom FROM Compra c WHERE c.juego.idJuego = ?1 AND c.isban = ?2");
    	q.setParameter(1, idJuego);
    	String normal= String.valueOf(1);
    	q.setParameter(2, normal); 
    	List<String> listaComentariosJuego = q.getResultList(); 
    	return listaComentariosJuego;
    }
    
    public List<String> listaCalificacionesJuego(int idJuego){
    	Query q;
    	q = em.createQuery("SELECT c.calcom FROM Compra c WHERE c.juego.idJuego = ?1");
    	q.setParameter(1, idJuego);
    	List<String> listaCalificacionesJuego = q.getResultList(); 
    	return listaCalificacionesJuego;
    }
    
    public void reportarComentario(Compra compraOriginal) {
    	String enrevision= String.valueOf(2);
    	compraOriginal.setIsban(enrevision);
    	em.merge(compraOriginal);
    }
    
    public List<Compra> listaComentCalifJuego(int idJuego){
    	Query q;
    	q = em.createQuery("SELECT c FROM Compra c WHERE c.juego.idJuego = ?1 AND c.isban = ?2");
    	q.setParameter(1, idJuego);
    	String normal= String.valueOf(1);
    	q.setParameter(2, normal); 
    	List<Compra> listaComentCalifJuego = q.getResultList(); 
    	return listaComentCalifJuego;
    }
    
    // ESTADISTICAS
    
    public String estadisticaVendedor(int idUsu) {
    	
    	String stringJuegosCantComprados = "";
    	
    	Query q, q2;
    	q = em.createQuery("SELECT j.idJuego FROM Juego j WHERE j.usuario.idUsu = ?1");
    	q.setParameter(1, idUsu);
    	List<Integer> idjuegosVendedor = q.getResultList();   
    	
    	for (Integer idjuego: idjuegosVendedor) {
	    	q2 = em.createQuery("SELECT COUNT(c.idCompra) AS NumberOfCompras FROM Compra c WHERE c.juego.idJuego = ?2");
	    	q2.setParameter(2, idjuego);	
	    	Long cantCompras = (Long) q2.getSingleResult();
	    	System.out.println("cantCompras: " + cantCompras);
	    	stringJuegosCantComprados = stringJuegosCantComprados + idjuego + "," + cantCompras + ",";
	    	
    	}
    	System.out.println("stringJuegosCantComprados: " + stringJuegosCantComprados);
    	
    	return stringJuegosCantComprados;
    	
    }
}
