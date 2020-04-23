import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        CustomerServlet cs = new CustomerServlet();
        DailyReportServlet drs = new DailyReportServlet();
        NewDayServlet nds = new NewDayServlet();
        ProducerServlet ps = new ProducerServlet();

        context.addServlet(new ServletHolder(cs), "/customer");
        context.addServlet(new ServletHolder(drs), "/report/*");
        context.addServlet(new ServletHolder(nds), "/newday");
        context.addServlet(new ServletHolder(ps), "/producer");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();


    }
}
