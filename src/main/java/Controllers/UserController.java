package Controllers;

import Entity.Users;
import Repository.UserRepository;
import Util.LoginRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/user")
@Transactional
public class UserController {

    @Inject
    private UserRepository userRepository;

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
        if(user != null && (request.getPass().equals(user.getPass()))) {
            return Response.ok().entity("usuario-logueado").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("credenciales-invalidas").build();
        }
    }


}
