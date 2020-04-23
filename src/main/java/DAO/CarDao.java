package DAO;

import model.Car;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars(){
        session.beginTransaction();
        List<Car>list = new ArrayList<>(session.createQuery("from Car").list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Car getCarbyParametr (String brand, String model, String licensePlate){
        session.beginTransaction();
        List list = session.createCriteria(Car.class).add(Restrictions.like("brand",brand))
                .add(Restrictions.like("model",model))
                .add(Restrictions.like("licensePlate",licensePlate)).list();
        session.getTransaction().commit();
        session.close();
        return (Car) list.get(0);

    }
    public void deleteCar(Car car){
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }
    public void addCar(Car car){
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }
    public List<Car> getCarByBrand (String brand) {
        Criteria criteria = session.createCriteria(Car.class).add(Restrictions.eq("brand",brand));
        List<Car> list = criteria.list();
        return list;
    }
    public void deleteAllCars() {
        session.beginTransaction();
        session.createQuery("DELETE from Car").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
