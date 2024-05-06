package Controllers;

import Entity.Users;
import Repository.UserRepository;
import Services.SessionService;
import Services.UserService;
import Util.LoginRequest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

@Path("/user")
@Transactional
public class UserController {

    @Inject
    private UserRepository userRepository;
    @Inject
    private UserService userService;
    @Inject
    private SessionService sessionService;


    @GET
    public List<Users> index() {
        return userRepository.listAll();
    }


    @GET
    @Path("{id}")
    public Response  retrieve(@PathParam("id") Long id) {
        var user = userRepository.findById(id);
        if(user != null) {
            return Response.ok(user).build();
        }
        return Response.status(404).entity("Not found").build();
    }

    @POST
    public Users insert(Users insertedUser) {
        String passInserted = insertedUser.getPass();

        try {
            KeyPair keyPair = userService.getKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            String encryptedPassword = userService.encryptPassword(passInserted, publicKey);
            insertedUser.setPass(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userRepository.persist(insertedUser);
        return insertedUser;
    }

    @DELETE
    @Path("{id}")
    public Response  delete(@PathParam("id") Long id) {
        if(userRepository.deleteById(id)) {
            return Response.ok("Se borro correctamente!").build();
        }
        return Response.status(404).entity("Not found").build();
    }

    @GET
    @Path("/exists/{username}")
    public Response checkUserExistsByUsername(@PathParam("username") String username) {
        Users user = userRepository.find("username", username).firstResult();
        System.out.println("Datos recibidos del find por user: " + user);
        if(user != null) {
            return Response.ok("existing-username").build();
        } else {
            //return Response.status(404).entity("no-existing-username " + username).build();
            return Response.ok("no-existing-username").build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Users user) {
        Users updateUser = userRepository.findById(id);
        if(updateUser != null) {
            updateUser.setUsername(user.getUsername());
            updateUser.setPass(user.getPass());
            updateUser.setPhone(user.getPhone());
            updateUser.setAge(user.getAge());
            updateUser.setGender(user.getGender());
            updateUser.setUpdateDate(LocalDate.now());

            userRepository.persist(updateUser);
            return Response.ok(updateUser).build();
        }
        return Response.status(404).entity("Not update").build();
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        Users user = userRepository.find("username", request.getUsername()).firstResult();
        String encryptedPassword = user.getPass();


        if (user != null) {
            try {
                KeyPair keyPair = userService.getKeyPair();
                PrivateKey privateKey = keyPair.getPrivate();
                String desencryptedPassword = userService.decryptPassword(encryptedPassword, privateKey);

                if(user != null && (request.getPass().equals(desencryptedPassword))) {
                    Long userId = user.getId(); // Obtén el ID de usuario
                    String sessionId = sessionService.createSession(userId);
                    String csrfToken = sessionService.generateCSRFToken(sessionId);
                    JsonObject responseJson = Json.createObjectBuilder()
                            .add("success", true)
                            .add("message", "usuario-logueado")
                            .add("sessionId", sessionId)
                            .add("csrfToken", csrfToken)
                            .add("user_id", userId)
                            .build();
                    String jsonResponse = responseJson.toString();
                    return Response.ok().entity(jsonResponse).build();
                } else {
                    JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
                    responseBuilder.add("success", false);
                    responseBuilder.add("message", "credenciales-invalidas");
                    JsonObject responseJson = responseBuilder.build();
                    return Response.status(Response.Status.UNAUTHORIZED).entity(responseJson).build();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }

        // Si no se encuentra el usuario, devolver un mensaje de credenciales inválidas
        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
        responseBuilder.add("success", false);
        responseBuilder.add("message", "credenciales-invalidas");
        JsonObject responseJson = responseBuilder.build();
        return Response.status(Response.Status.UNAUTHORIZED).entity(responseJson).build();


    }


}
