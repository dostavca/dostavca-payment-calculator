import com.codahale.metrics.Counter;
import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.eclipse.microprofile.health.Health;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Aljaz on 14/12/2017.
 */
@Log
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("payment-calculator")
@Health
public class PaymentCalculatorResource {

    @POST
    @Metered(name = "calculate-payment-meter")
    @Path("calculate-payment")
    public Response calculatePayment(Transport transport) {
        double payment = transport.getDistance() * 0.02 + transport.getPacket().getWeight() * 0.3;
        return Response.ok(payment).build();
    }
}
