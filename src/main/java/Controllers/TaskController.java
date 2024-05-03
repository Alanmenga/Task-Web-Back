package Controllers;

import Entity.Tasks;
import Entity.Users;
import Repository.TaskRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/task")
@Transactional
public class TaskController {

    @Inject
    private TaskRepository taskRepository;

    @GET
    public List<Tasks> index() {
        return taskRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Response retrieve(@PathParam("id") Long id) {
        var task = taskRepository.findById(id);
        if(task != null) {
            return Response.ok(task).build();
        }
        return Response.status(404).entity("Not found").build();
    }

    @POST
    public Tasks insert(Tasks insertedTask) {
        taskRepository.persist(insertedTask);
        return insertedTask;
    }

    @DELETE
    @Path("{id}")
    public Response  delete(@PathParam("id") Long id) {
        if(taskRepository.deleteById(id)) {
            return Response.ok("Se borro correctamente!").build();
        }
        return Response.status(404).entity("Not found").build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Tasks task) {
        System.out.println("el id es: " + id);
        System.out.println("el user es: " + task);
        Tasks updateTask = taskRepository.findById(id);
        System.out.println("el updateUser es: " + updateTask);
        if(updateTask != null) {
            updateTask.setTitle(task.getTitle());
            updateTask.setDescription(task.getDescription());

            taskRepository.persist(updateTask);
            return Response.ok(updateTask).build();
        }
        return Response.status(404).entity("Not update").build();
    }
}
