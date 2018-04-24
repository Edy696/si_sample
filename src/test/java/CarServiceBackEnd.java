import java.util.List;

import org.springframework.context.ApplicationContext;

import com.toyota.carservice.config.config;
import com.toyota.carservice.interfaces.CarDao;
import com.toyota.carservice.interfaces.PartDao;
import com.toyota.carservice.interfaces.ServiceDao;
import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Part;
import com.toyota.carservice.model.Service;

public class CarServiceBackEnd {

	public static void main(String[] args) {
		CarDao carDao;
		PartDao partDao;
		ServiceDao serviceDao;
		ApplicationContext appContex = config.getInstance()
				.getApplicationContext();
		carDao = appContex.getBean(CarDao.class);
		partDao = appContex.getBean(PartDao.class);
		serviceDao = appContex.getBean(ServiceDao.class);

		Car xenia = new Car(null, "Xenia", "xenia.jpg");
		xenia = carDao.save(xenia);

		Part OLI_MESIN = new Part(null, xenia, "23300-75160", "TOP ONE");
		Part MINYAK_REM = new Part(null, xenia, "23300-75160", "MINYAK REM");
		Part MINYAK_P = new Part(null, xenia, "23300-75160","MINYAK P/S (ATF D-II)");
		Part AIR_WIPER = new Part(null, xenia, "23300-75160", "AIR WIPER");
		Part SLLC = new Part(null, xenia, "23300-75160", "SLLC");
		Part AirCleaner = new Part(null, xenia, "23300-75160", "Air Cleaner");
		Part AirSoap = new Part(null, xenia, "23300-75160", "Air Soap");
		
		// xenia.getPart().add(AirSoap);
		OLI_MESIN = partDao.save(OLI_MESIN); 
		MINYAK_REM = partDao.save(MINYAK_REM);
		MINYAK_P = partDao.save(MINYAK_P);
		AIR_WIPER = partDao.save(AIR_WIPER);
		SLLC = partDao.save(SLLC);
		AirCleaner = partDao.save(AirCleaner);
		AirSoap = partDao.save(AirSoap);

		Service gantiOli = new Service(null,"Ganti Oli",xenia,OLI_MESIN);
		serviceDao.save(gantiOli);
		
		List<Car> listCar = carDao.findAll();
		System.out.println("=============================");
		for (Car c : listCar) {
			System.out.println(c.getCarId());
			System.out.println(c.getName());
			System.out.println(c.getPhoto());
			System.out.println("===========Data Part==================");
			for (Part p : c.getPart()) {
				System.out.println(p.getPartName());
				System.out.println(p.getPartNumber());
			}
		}
	}
}