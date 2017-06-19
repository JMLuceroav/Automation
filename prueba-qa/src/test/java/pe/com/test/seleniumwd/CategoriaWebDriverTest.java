package pe.com.test.seleniumwd;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CategoriaWebDriverTest {

	private WebDriver driver;//permite comenzar obtener ,los valores del navegador

	@BeforeTest//por que se debe colocar antes que comience los test
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Programas\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//por cada cambio que haga espere 30 seg. Eso hace ese metodo.
	}

	@Test
	public void insertarCategoria_FlujoBasico() throws Exception {
		try {
			String mensajeEsperado = "Se guardó de manera correcta la Categoría";
			//TODO Completar
			//solo debe haber un get como buena practica
			driver.get("http://localhost:8080/TuBodeguitaWeb/");
			//Escribiendo la admin
			driver.findElement(By.id("txtUsuario")).clear();                    //clear te limpia el campo
			driver.findElement(By.id("txtUsuario")).sendKeys("admin");
			//Escribiendo la clave
			driver.findElement(By.id("txtClave")).clear();
			driver.findElement(By.id("txtClave")).sendKeys("clave");
			//Clic en el boton iniciar sesion
			driver.findElement(By.id("btnIniciarSesion")).click();
			
			//Se pone una espera
			Thread.sleep(3000);
			//Obteniendo valor del componente mantenimiento categoria.
			driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/form/div/ul/li[2]/a/span")).click();
			
			//Recomendacion poner espera cuando te direcciona a una nueva pantalla
			Thread.sleep(3000);
			//ahora se da clic en boton nuevo
			driver.findElement(By.id("btnNuevo")).click();
			Thread.sleep(3000);//Tiempo de espera
			
			//Obteniendo valores
			driver.findElement(By.id("txtNombre")).clear();
			driver.findElement(By.id("txtNombre")).sendKeys("CAFE");
			driver.findElement(By.id("btnGuardar")).click();
			Thread.sleep(3000);
						
			String mensajeObtenido = driver.findElement(By.id("messages")).getText();
			Assert.assertEquals(mensajeEsperado, mensajeObtenido);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods = {"insertarCategoria_FlujoBasico"})
	public void insertarCategoria_FlujoAlternativo() throws Exception {
		try {
			String mensajeEsperado = "Nombre: Error de validación: se necesita un valor.";
			//TODO Completar
			
			//solo debe haber un get como buena practica
			driver.get("http://localhost:8080/TuBodeguitaWeb/");
			//Escribiendo la admin
			driver.findElement(By.id("txtUsuario")).clear();                    //clear te limpia el campo
			driver.findElement(By.id("txtUsuario")).sendKeys("admin");
			//Escribiendo la clave
			driver.findElement(By.id("txtClave")).clear();
			driver.findElement(By.id("txtClave")).sendKeys("clave");
			//Clic en el boton iniciar sesion
			driver.findElement(By.id("btnIniciarSesion")).click();
			
			//Se pone una espera
			Thread.sleep(3000);
			//Obteniendo valor del componente mantenimiento categoria.
			driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/form/div/ul/li[2]/a/span")).click();
			
			//Recomendacion poner espera cuando te direcciona a una nueva pantalla
			Thread.sleep(3000);
			//ahora se da clic en boton nuevo
			driver.findElement(By.id("btnNuevo")).click();
			Thread.sleep(3000);//Tiempo de espera
			
			//Obteniendo valores
			driver.findElement(By.id("txtNombre")).clear();
			//driver.findElement(By.id("txtNombre")).sendKeys("Lacteos");
			driver.findElement(By.id("btnGuardar")).click();
			Thread.sleep(3000);						
			
			String mensajeObtenido = driver.findElement(By.id("messages")).getText();
			Assert.assertEquals(mensajeEsperado, mensajeObtenido);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}
