package api;

import domain.Kweet;
import dto.KweetDTO;
import filters.interfaces.JWTTokenNeeded;
import service.KweetService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("kweets")
public class KweetAPI {
    @Inject
    private KweetService kweetService;

    public KweetAPI(){
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAllKweets(){
        try {
            List<Kweet> Kweets = kweetService.getAllKweets();
            return Response.ok(convertKweetListToKweetDTOList(Kweets)).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getPaginatedKweets(@PathParam("page") int page){
        try {
            List<Kweet> Kweets = kweetService.getPaginatedKweets(page, 10);
            return Response.ok(convertKweetListToKweetDTOList(Kweets)).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/user/{userId}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getLatestKweetsForUser(@PathParam("userId") UUID id, @PathParam("page") int page){
        try{
            List<Kweet> userKweets = kweetService.getKweetsForUserId(id, page, 10);
            return Response.ok(convertKweetListToKweetDTOList(userKweets)).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/search/{searchString}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getKweetsBySearchString(@PathParam("searchString") String search, @PathParam("page") int page){
        try {
            List<Kweet> Kweets = kweetService.getKweetsBySearchString(search, page, 10);
            return Response.ok(convertKweetListToKweetDTOList(Kweets)).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/user/follow/{id}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @JWTTokenNeeded
    public Response getKweetsByUserIdWithFollowing(@PathParam("id") UUID id, @PathParam("page") int page){
        try {
            List<Kweet> kweets = kweetService.getKweetsByUserIdWithFollowing(id, page, 10);
            return Response.ok(convertKweetListToKweetDTOList(kweets)).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getKweetById(@PathParam("id") UUID id){
        try {
            Kweet Kweet = kweetService.getKweetById(id);
            if (Kweet != null){
                return Response.ok(new KweetDTO(Kweet)).build();
            }
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @DELETE
    @Path("/{authorId}/{kweetId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response removeKweetById(@PathParam("authorId") UUID authorId, @PathParam("kweetId") UUID kweetId){
        try {
            Kweet k = kweetService.getKweetById(kweetId);
            if (k.getAuthor().getId().equals(authorId) && (k = kweetService.removeKweetById(kweetId)) != null) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(500).entity(k).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @JWTTokenNeeded
    public Response createKweet(KweetDTO kweetDTO){
        try {
            if (kweetDTO != null){
                KweetDTO result = new KweetDTO(kweetService.createKweet(kweetDTO.getMessage(), kweetDTO.getAuthor().getId()));
                return Response.ok(result).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    private Response createErrorResponse(Exception e) {
        return Response.status(500).entity(e).build();
    }

    private List<KweetDTO> convertKweetListToKweetDTOList(List<Kweet> kweets){
        if (kweets.size() > 0){
            List<KweetDTO> results = new ArrayList<>();
            for (Kweet t : kweets){
                results.add(new KweetDTO(t));
            }
            return results;
        }
        return new ArrayList<>();
    }
}
