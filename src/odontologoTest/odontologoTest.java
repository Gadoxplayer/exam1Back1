package odontologoTest;

import com.company.dao.ConfiguracionJDBC;
import com.company.dao.OdontologoDaoH2;
import com.company.model.Odontologo;
import com.company.service.OdontologoService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class odontologoTest {
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2(new ConfiguracionJDBC()));

    @Test
    public void guardar(){
        Odontologo odontologo1 = new Odontologo(12345, "Peter", "Bauman");
        Odontologo odontologo1Guardado = odontologoService.guardar(odontologo1);
        Assert.assertTrue(odontologo1Guardado.getId() != null);

        Odontologo odontologo2 = new Odontologo(54238, "Nicolas", "Lopez Lopi");
        Odontologo odontologo2Guardado = odontologoService.guardar(odontologo2);
        Assert.assertTrue(odontologo2Guardado.getId() != null);

        Odontologo odontologo3 = new Odontologo(45871, "Mariano", "Martinez");
        Odontologo odontologo3Guardado = odontologoService.guardar(odontologo3);
        Assert.assertTrue(odontologo3Guardado.getId() != null);

    }

    @Test
    public void listar(){
        List<Odontologo> odontologos = odontologoService.listar();
        Assert.assertTrue(odontologos.size() != 0);
    }

}
