package com.steamindie.webservice;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import data.Interfaz;
import model.Compra;
import model.Estado;
import model.Instanciaoferta;
import model.Juego;
import model.Oferta;
import model.Perfil;
import model.Usuario;
import model.Tag;
import model.Cat;

@Stateless
@LocalBean
@Path("/servicios")
public class Servicios {

	/**
     * Default constructor. 
     */
    public Servicios() {
        // TODO Auto-generated constructor stub
    }
 
    @Inject
	private Interfaz interfaz;

    
    //Control Usuario  ///
 
        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    	@Path("/Login")
        public Response Login(@FormParam("Nick") String nick, 
        							@FormParam("Pass") String pass) {
       	
        	System.out.println(nick);
        	System.out.println(pass);
            try {
           		//System.out.println("/////");
                // Authenticate the user using the credentials provided
                Usuario Usu = interfaz.Login(nick, pass);
                int w = Usu.getIdUsu();
                String x = Usu.getRol() ;
                BigDecimal y = Usu.getBilletera().getSaldo() ;
                String z = Usu.getNick() ;
                // Issue a token for the user
                String token = Token(w,x,y,z);

                // Return the token on the response
                return Response.ok(token).build();

            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }      
        }
        
        private String Token(int w,String x,BigDecimal y,String z) {
        	String W =String.valueOf(w);
        	String Y =String.valueOf(y);
        	String Validator = getAlphaNumericString(55);
        	String Pip = "%27";

        	String token = W+Pip+x+Pip+Y+Pip+z+Pip+Validator;
        	return token;
            // Issue a token (can be a random String persisted to a database or a JWT token)
            // The issued token must be associated to a user
            // Return the issued token
        }

        private String getAlphaNumericString(int n)
            {
          
                // chose a Character random from this String
                String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                            + "0123456789"
                                            + "abcdefghijklmnopqrstuvxyz";
          
                // create StringBuffer size of AlphaNumericString
                StringBuilder sb = new StringBuilder(n);
          
                for (int i = 0; i < n; i++) {
          
                    // generate a random number between
                    // 0 to AlphaNumericString variable length
                    int index
                        = (int)(AlphaNumericString.length()
                                * Math.random());
          
                    // add Character one by one in end of sb
                    sb.append(AlphaNumericString
                                  .charAt(index));
                }
                return sb.toString();
            }     
 
        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    	@Path("/Registro")
        public Response Registro(@FormParam("Nom") String nom, @FormParam("Apell") String apell,
        							@FormParam("Fnac") Date fnac,@FormParam("Img") String img, @FormParam("Nick") String nick,@FormParam("Email") String email,
        								@FormParam("Pass") String pass, @FormParam("Pass2") String pass2, @FormParam("Sexo") String sexo, @FormParam("Rol") String rol) {

            try {

                byte[] Img = Base64.getDecoder().decode(new String(img).getBytes("UTF-8"));          	
                interfaz.Registro(nom, apell, fnac,Img, nick, email, pass, sexo, rol);
                return Response.ok().build();

            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }      
        }
   
    //SearchBar    
        
    	@GET
    	@Produces(value=MediaType.APPLICATION_JSON)
    	@Path("/Search/Usu/{Q}")
        public List<Usuario> SearchUsu(@PathParam("Q") String q){
    		List<Usuario> Usuarios = interfaz.BusqUsu(q);
            return Usuarios;
        }        
        
    	@GET
    	@Produces(value=MediaType.APPLICATION_JSON)
    	@Path("/Search/Jue/{Q}/{B}")
        public List<Juego> SearchJue(@PathParam("Q") String q , @PathParam("B") int b){
    		List<Juego> Juegos = interfaz.BusqJue(q,b);
            return Juegos;
        }        
    	
    	@GET
    	@Produces(value=MediaType.APPLICATION_JSON)
    	@Path("/Tag")
        public List<Tag> Tag(){
    		List<Tag> Tags = interfaz.encontrarTodosTags();
            return Tags;
        }   
    	
    	@GET
    	@Produces(value=MediaType.APPLICATION_JSON)
    	@Path("/Cat")
        public List<Cat> Cat(){
    		List<Cat> Cats = interfaz.encontrarTodosCats();
            return Cats;
        }   
        
    //	USUARIOS
	
    @GET
	@Produces(value=MediaType.APPLICATION_JSON)
	@Path("/usuarios/listarUsuarios")  //http://localhost:8080/SteamIndieBackend/rest/servicios/usuarios/listarUsuarios trae todos los users
	public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios =  interfaz.encontrarTodosUsuarios();
        System.out.println("Usuarios encontrados:" + usuarios);
        return usuarios;
    }
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/usuarios/buscarUsuario/{id}") //hace referencia al path: http://localhost:8080/SteamIndieBackend/rest/servicios/usuarios/buscarUsuario/2 
    public Usuario encontrarUsuario(@PathParam("id") int id){
        Usuario usuario = interfaz.encontrarUsuario(new Usuario(id));
        System.out.println("Usuario encontrado:" + usuario);
        return usuario;    
    }

	@POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/usuarios/agregarUsuario") 
    public Usuario agregarUsuario(Usuario usuario){
    	interfaz.insertarUsuario(usuario);
        System.out.println("Usuario agregado:" + usuario);
        return usuario;
    }
	
	@PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/usuarios/editarUsuario{id}")
    public Response modificarUsuario(@PathParam("id") int id, Usuario usuarioModificado){
        Usuario usuario = interfaz.encontrarUsuario( new Usuario(id));
        if(usuario != null){
        	interfaz.actualizarUsuario(usuarioModificado);
            System.out.println("Usuario modificado:" + usuarioModificado);
            return Response.ok().entity(usuarioModificado).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }
	
	@DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/usuarios/eliminarUsuario/{id}")
    public Response eliminarUsuario(@PathParam("id") int id){
    	interfaz.eliminarUsuario(new Usuario(id));
        System.out.println("Usuario eliminado con el id:" + id);
        return Response.ok().build();
    }

	// Estados

	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/usuarios/buscarEstados/{id}")
    public List<Estado> encontrarEstados(@PathParam("id") int id){
        List<Estado> estados = interfaz.listarEstados(id);
        System.out.println("Estados encontrados:" + estados);
        return estados;
        
    }	

	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/estados/agregarEstado")
	public Response crearEstado(@FormParam("idUsu") int idUsu, @FormParam("imgestado") String imgestado, 
			@FormParam("comestado") String comestado, @FormParam("festado") Date festado) {
				
		try {
			
			Perfil perfil = new Perfil(idUsu); 
			byte[] Img = Base64.getDecoder().decode(new String(imgestado).getBytes("UTF-8"));
			Estado estado = new Estado(comestado, festado, Img, perfil);
			
			interfaz.insertarEstado(estado);
			 System.out.println("Nuevo estado agregado satisfactoriamente !");
			 return Response.ok().build();
		} catch (Exception e) {
			System.out.println("Error al agregar nuevo estado.");
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
	}
	
	// Juegos
	
	@GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/juegos/listarJuegos")  //http://localhost:8080/SteamIndieBackend/rest/servicios
    public List<Juego> listarJuegos(){
        List<Juego> juegos =  interfaz.encontrarTodosJuegos();
        System.out.println("Juegos encontrados:" + juegos);
        return juegos;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/juegos/buscarJuego/{id}") 
    public Juego encontrarJuego(@PathParam("id") int id){
        Juego juego = interfaz.encontrarJuego(new Juego(id));
        System.out.println("Juego encontrado:" + juego);
        return juego;
    }
	
	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/juegos/agregarJuego") 
	public Response agregarJuego(@FormParam("nomjuego") String nomjuego, @FormParam("precio") String precio,
			@FormParam("descripcion") String descripcion, @FormParam("imagen") String imagen, @FormParam("video") String video,
			@FormParam("categoria") String categoria, @FormParam("tags") String tags, @FormParam("fechaL") Date fechaL,
			@FormParam("url") String url, @FormParam("requisitos") String requisitos, @FormParam("ban") String ban,
			@FormParam("calificacion") String calificacion, @FormParam("idUsu") int idUsu) {
		
		try {
			
			String stringcalif = calificacion;
			float calif = Float.parseFloat(stringcalif);
						
            byte[] Img = Base64.getDecoder().decode(new String(imagen).getBytes("UTF-8"));
                      
            Usuario usu = new Usuario(idUsu);
         
            BigDecimal preciojuego = new BigDecimal(precio);
            
            Juego j = new Juego(ban, calif, categoria, fechaL, Img, nomjuego, preciojuego, descripcion, requisitos, tags, url, video, usu);
            
            interfaz.insertarJuego(j);
            System.out.println("Nuevo Juego agregado satisfactoriamente !");

       
            return Response.ok().build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }	
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/juegos/reportarJuego") 
	public Response reportarJuego(@FormParam("IdJuego") int IdJuego) {
		Juego j = new Juego(IdJuego);
		
		Juego juegooriginal = interfaz.encontrarJuego(j);
		if(juegooriginal != null){
			interfaz.reportarJuego(juegooriginal);
			System.out.println("Juego reportado!");
            return Response.ok().entity(juegooriginal).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/juegos/dereportarJuego") 
	public Response dereportarJuego(@FormParam("IdJuego") int IdJuego) {
		Juego j = new Juego(IdJuego);
		
		Juego juegooriginal = interfaz.encontrarJuego(j);
		if(juegooriginal != null){
			interfaz.dereportarJuego(juegooriginal);
			System.out.println("Juego dereportado!");
            return Response.ok().entity(juegooriginal).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/juegos/banearJuego") 
	public Response banearJuego(@FormParam("IdJuego") int IdJuego) {
		Juego j = new Juego(IdJuego);
		
		Juego juegooriginal = interfaz.encontrarJuego(j);
		if(juegooriginal != null){
			interfaz.banearJuego(juegooriginal);
			System.out.println("Juego baneado!");
            return Response.ok().entity(juegooriginal).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
	} 
	
	//Ofertas

	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	@Path("/ofertas/listarOfertas")  
	public List<Oferta> listarOfertas(){
        List<Oferta> ofertas =  interfaz.encontrarTodasOfertas();
        System.out.println("Ofertas encontradas:" + ofertas);
        return ofertas;
    }
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/ofertas/buscarOferta/{id}")  
    public Oferta encontrarOferta(@PathParam("id") int id){
		Oferta oferta = interfaz.encontrarOferta(new Oferta(id));
        System.out.println("Oferta encontrada:" + oferta);
        return oferta;
    }

	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/ofertas/agregarOferta") 
    public Oferta agregarOferta(@FormParam("nominstof") String nominstof, @FormParam("porcentof") int porcentof, @FormParam("finiof") String finiof, @FormParam("ffinof") String ffinof, @FormParam("activa") int activa){
		
		Oferta oferta = new Oferta(activa, ffinof, finiof, nominstof, porcentof);
    	interfaz.insertarOferta(oferta);
        System.out.println("Oferta agregada:" + oferta);
        
        return oferta;
    }
	
	@PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/ofertas/editarOferta{id}")
    public Response modificarOferta(@PathParam("id") int id, Oferta ofertaModificada){
		Oferta oferta = interfaz.encontrarOferta( new Oferta(id));
        if(oferta != null){
        	interfaz.actualizarOferta(ofertaModificada);
            System.out.println("oferta modificada :" + ofertaModificada);
            return Response.ok().entity(ofertaModificada).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }

	@DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/ofertas/eliminarOferta/{id}")
    public Response eliminarOferta(@PathParam("id") int id){
    	interfaz.eliminarOferta(new Oferta(id));
        System.out.println("Oferta eliminada con el id:" + id);
        return Response.ok().build();
    }

	//Instancia Oferta
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/instanciaoferta/listarJuegosEnOferta") 
	public List<Juego> listarJuegosEnOferta(){

		List<Juego> listajuegos = new ArrayList<Juego>();
		List<Instanciaoferta> juegosoferta = interfaz.listarJuegosEnOferta();
		
		for(int i = 0; i < juegosoferta.size(); i++) {
			Juego j = new Juego(juegosoferta.get(i).getJuego().getIdJuego());
			Juego juegocompleto = interfaz.detalleJuego(j);
		
			listajuegos.add(juegocompleto);
		}
		
		System.out.println("juegos en oferta encontrados:" + listajuegos);
		return listajuegos;
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/instanciaoferta/agregarJuegosAoferta") 
    public Response agregarJuegosAoferta(@FormParam("Juegos") String juegos, @FormParam("idOferta") int idoferta){
		
		String stringidsjuegos = juegos;
		String[] arrayidjuegos = stringidsjuegos.split(",");
		
		
		// CHEQUEA QUE NO ESTÉ EL JUEGO REGISTRADO EN LA OFERTA
		/*List<Instanciaoferta> juegosoferta= interfaz.listarJuegosEnOferta();	
		for(Instanciaoferta io : juegosoferta) {
			  if(io.getJuego().getIdJuego() == idjuego) {
				  return Response.serverError()
				          .status(Response.Status.BAD_REQUEST)
				          .entity("Este juego ya existe en la oferta")
				          .build();
			  }
		}*/
				
    	interfaz.insertarJuegosAoferta(idoferta, arrayidjuegos);
        System.out.println("Juegos agregados a oferta !");
        return Response.ok().build();
    }
	
	//	Compras
	
	@GET
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/compras/buscarCompra")  
    public Compra encontrarCompra(@FormParam("IdJuego") int IdJuego, @FormParam("idUsu") int idUsu){
		
		Juego j = new Juego(IdJuego);
		Usuario u = new Usuario(idUsu);
		
		Compra c = new Compra(j,u);
		Compra compra = interfaz.encontrarCompra(c);
		
        System.out.println("Compra encontrada:" + compra);
        return compra;
    }
	
	@POST
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/compras/comprarJuego") 
	public Response comprarJuego(@FormParam("IdJuego") int IdJuego, @FormParam("idUsu") int idUsu){
		interfaz.comprarJuego(IdJuego, idUsu);
		System.out.println("Juego comprado!");
        return Response.ok().build();
	}
	
	@PATCH
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/compras/calificarComentarJuego") 
	public Response calificarComentarJuego(@FormParam("comcom") String comcom, @FormParam("calcom") String calcom, @FormParam("IdJuego") int IdJuego, @FormParam("idUsu") int idUsu){
	
		Juego j = new Juego(IdJuego);
		Usuario u = new Usuario(idUsu);
		
		Compra c = new Compra(j,u);
		Compra compraOriginal = interfaz.encontrarCompra(c);
		
		if(compraOriginal != null){
			interfaz.calificarComentarJuego(compraOriginal, comcom, calcom);
			System.out.println("Juego calificado y comentado!");
            return Response.ok().entity(compraOriginal).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
	}
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/compras/listarComprasUsuario/{idUsu}") 
	public List<Compra> listarComprasUsuario(@PathParam("idUsu") int idUsu){
		List<Compra> comprasUsuario = interfaz.listarComprasUsuario(idUsu);
		System.out.println("compras del usuario:" + comprasUsuario);
		return comprasUsuario;
	}

	@PATCH
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/compras/reportarComentario") 
	public Response reportarComentario(@FormParam("IdJuego") int IdJuego, @FormParam("idUsu") int idUsu){
	
		Juego j = new Juego(IdJuego);
		Usuario u = new Usuario(idUsu);
		
		Compra c = new Compra(j,u);
		Compra compraOriginal = interfaz.encontrarCompra(c);
		
		if(compraOriginal != null){
			interfaz.reportarComentario(compraOriginal);
			System.out.println("Comentario en revisión!");
            return Response.ok().entity(compraOriginal).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
	}

	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
	@Path("/compras/listaComentCalifJuego/{IdJuego}") 
	public List<Compra> listaComentCalifJuego(@PathParam("IdJuego") int IdJuego){
		
		List<Compra> listaComentCalifJuego = interfaz.listaComentCalifJuego(IdJuego);
		System.out.println("comentarios juegos: " + listaComentCalifJuego);
		return listaComentCalifJuego;
	}

	
	// ESTADISTICAS
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/estadisticas/estadisticaVendedor/{idUsu}") 
	public Response estadisticaVendedor(@PathParam("idUsu") int idUsu) {
		
		try {
			
			String stringJuegosCantComprados = interfaz.estadisticaVendedor(idUsu);
			
			return Response.ok(stringJuegosCantComprados).build();
			 
		} catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } 
		
	}
	
	
	
}