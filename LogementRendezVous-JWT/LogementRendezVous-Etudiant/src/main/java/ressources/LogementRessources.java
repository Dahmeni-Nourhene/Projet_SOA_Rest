package ressources;

import entities.Logement;
import entities.RendezVous;
import filtres.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("logements")
public class LogementRessources {
    public static LogementBusiness logementMetier = new LogementBusiness();
    private List<Logement> logements ;
@POST
@Consumes(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_XML)

    public Response addLogement(Logement l) {
    //     if(logements.add(l))
     if(logementMetier.addLogement(l))
         return  Response.status(Status.CREATED).build();
     return  Response.status(Status.NOT_FOUND).build();
    }
@Secured
@GET
@Path("/projet")
@Produces(MediaType.APPLICATION_JSON)
    public Response getLogements() {
        List<Logement> liste=logementMetier.getLogements();
    if(liste.size()==0)
        return  Response.status(Status.NOT_FOUND).build();
    return  Response.status(Status.OK).entity(liste).build();


    }
//@Secured
    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getlogementReference(@PathParam("reference") int reference){
        Logement logement = logementMetier.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(Response.Status.OK).entity(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }








//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//
//    public Response getLogementsByDeleguation(@QueryParam("delegation") String delegation) {
//        List<Logement> logementsByDeleguation = logementMetier.getLogementsByDeleguation(delegation);
//
//        if (logementsByDeleguation.isEmpty()) {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//
//        return Response.status(Status.OK).entity(logementsByDeleguation).build();
//    }
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getLogementByReference(@QueryParam("reference") int reference) {
//        Logement logement = logementMetier.getLogementsByReference(reference);
//
//        if (logement == null) {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//
//        return Response.status(Status.OK).entity(logement).build();
//    }

/////////////he4a shiha put
    @PUT
@Consumes(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_XML)
@Path("{reference}")

    public Response updateLogement(Logement updatedLogement, @PathParam("reference") int  reference) {


        if (logementMetier.updateLogement(reference,updatedLogement)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

////////////////////////he4y khedmty ena o eya //
//@PUT
//@Consumes(MediaType.APPLICATION_JSON)
//
//@Path("{reference}")
//
//public Response updateLogement(Logement logement, @PathParam("reference") int  reference) {
//    for(Logement l  :logements){
//       if( l.getReference()== reference){
//           l.setAdresse(logement.getAdresse());
//           l.setDelegation(logement.getDelegation());
//           return Response.status(Status.OK).build();
//       }
//    }
//        return Response.status(Status.NOT_FOUND).build();
//    }
//

///////////////////////////////////////////////////////

@DELETE
@Path("{reference}")

        public  Response deleteLogement(@PathParam("reference") int reference){
           if(logementMetier.deleteLogement(reference))
                    return Response.status(Status.OK).build();


            return Response.status(Status.NOT_FOUND).build();

        }

}
