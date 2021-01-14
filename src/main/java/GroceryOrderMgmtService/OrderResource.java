package GroceryOrderMgmtService;

import GroceryOrderMgmtService.dto.OrderRequest;
import GroceryOrderMgmtService.services.IOrderFulfilmentService;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final IOrderFulfilmentService orderFulfilmentService;

    public OrderResource(IOrderFulfilmentService orderFulfilmentService) {
        this.orderFulfilmentService = orderFulfilmentService;
    }

    @POST
    @Path("/availability")
    @Timed
    public Response checkAvailability(OrderRequest request){
        return Response.ok().entity(orderFulfilmentService.canFulfilOrder(request)).build();
    }

    @POST
    @Path("/reserve")
    @Timed
    public Response reserveOrder(OrderRequest request){
        return Response.ok().entity(orderFulfilmentService.reserveOrder(request)).build();
    }
}
