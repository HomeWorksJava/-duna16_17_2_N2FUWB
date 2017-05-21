package com.mycompany.fegyverapi;


import fegyverek.Weapons;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import genericdaoservice.*;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;


@Path("weapons")
@RequestScoped
public class FegyverResource 
{
    @Inject
            GenericDaoService gds;
    
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
       
    public FegyverResource()
    {
        log.info("fegyverresource peldany letrejott");
    }
    
    @GET
    @Path("osszes")
    @Produces(MediaType.APPLICATION_JSON)
      public List<Weapons> getWeapons()
      {
         return gds.getEntities("Weapons.OsszesWeapons", new HashMap());
      }
      
    @GET
    @Path("weapons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
        public Weapons getWeaponsById(@PathParam("id")Long id)
        {
            Map<String,Object> params = new HashMap<>();
            params.put("id", id);
            
            return (Weapons) gds.getEntity("Weapons.getWeaponsById", params);
        }
    
    @POST
    @Path("hozzaad")
    @Produces(MediaType.TEXT_PLAIN)
      public String insertWeapons(@FormParam("gyarto")String gyarto,
                                 @FormParam("kaliber")String kaliber,
                                 @FormParam("tipus")String tipus,
                                 @FormParam("tar")String tar)
      {
         Weapons weapons = new Weapons();
         
         try
         {
             weapons.setKaliber(Double.parseDouble(kaliber));
         }
         catch(NumberFormatException ex)
         {
             log.error("Nem szam",ex);
         }        
            weapons.setGyarto(gyarto);
            weapons.setTipus(tipus);
            weapons.setTar(tar);
            
//            em.getTransaction().begin();
            gds.save(weapons);
//            em.getTransaction().commit();
          
          return " ok ";
      }
}












