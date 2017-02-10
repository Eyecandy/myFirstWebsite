/**
 * Created by joakimnilfjord on 2/10/2017 AD.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Webapp
{
    public static void main( String[] args ) throws LifecycleException,
            InterruptedException, ServletException {

        //String docBase = "src/login/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
        //Adding servlet to the class
        Tomcat.addServlet(ctx, "Hello", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {

                Writer w = resp.getWriter();
                w.write("Hello\n");
                w.flush();
                w.close();
            }
        });

        Tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {

                Writer w = resp.getWriter();
                w.write("hi\n");
                w.flush();
                w.close();
            }
        });
        ctx.addServletMapping("/hellox", "Hello");
        ctx.addServletMapping("/*", "Embedded");


        tomcat.start();
        tomcat.getServer().await();
    }
}
