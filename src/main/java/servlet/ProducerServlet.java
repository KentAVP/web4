package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(403);
        String model = req.getParameter("model");
        String brand = req.getParameter("brand");
        String licensePlate = req.getParameter("licensePlate");
        String price = req.getParameter("price");
        CarService cs = CarService.getInstance();
        if(!brand.equals("")&&!model.equals("")&&!licensePlate.equals("")&&!price.equals("")){
            Long pric = Long.parseLong(price);
            if(cs.getNumBrand(brand)<10){
                Car car = new Car(brand, model, licensePlate, pric);
                cs.addCar(car);
                resp.setStatus(200);
            }
        }

    }
}
