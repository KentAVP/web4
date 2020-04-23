package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }


    public List<Car> getAllCars() {
        List<Car> car = null;
        CarDao dao = new CarDao((sessionFactory.openSession()));
        car = dao.getAllCars();
        return car;

    }

    public Car getCarbyParam(String brand, String model, String licensePlate){
        CarDao cd = new CarDao(sessionFactory.openSession());
        return cd.getCarbyParametr(brand,model,licensePlate);
    }
    public void deleteCar(Car car){
        CarDao cd = new CarDao(sessionFactory.openSession());
        cd.deleteCar(car);
    }
    public void addCar(Car car){
        CarDao cd = new CarDao(sessionFactory.openSession());
        cd.addCar(car);
    }
    public int getNumBrand(String brand){
        List<Car> list = null;
        int number = 0;
        CarDao cd = new CarDao(sessionFactory.openSession());
        list=cd.getCarByBrand(brand);
        if(list!=null){
            number = list.size();
        }
        return number;
    }

    public void deleteAllCar() {
        CarDao cd = new CarDao(sessionFactory.openSession());
        cd.deleteAllCars();
    }
}
