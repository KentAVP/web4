package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.getWriter().write(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String model = req.getParameter("model");
        String brand = req.getParameter("brand");
        String licensePlate = req.getParameter("licensePlate");

        CarService cs = CarService.getInstance();
        DailyReportService drs = DailyReportService.getInstance();
        Car car = cs.getCarbyParam(brand, model, licensePlate);
        if(car.getId()!=null&& car.getId()>0){
            drs.addSoldCar(car);
            cs.deleteCar(car);

        }

    }
}
