package com.bestbuy.crudtest;

import com.bestbuy.bestbuyInfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    static String name = "Battery Portable" + TestUtils.getRandomValue();
    static String type = "Chargable" + TestUtils.getRandomValue();
    static double price = 5.50;
    static String upc = "123456789";
    static double shipping = 5;
    static String description = "Long Life Guarantee";
    static String manufacturer = "Duracell";
    static String model = "AK 47";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;
    @Steps
    ProductSteps productSteps;


    @Title("Create a product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, image, url);
        response.log().all();
        response.statusCode(201);
        productId = response.extract().path("id");


    }
    @Title("Read the product")
    @Test
    public void test002() {

        HashMap<String, Object> getProductByIdReading = productSteps.getProductInfoById(productId);
        Assert.assertThat(getProductByIdReading, hasValue(name));

    }
    @Title("Update th product")
    @Test
    public void test003() {

        ValidatableResponse response = productSteps.updateProduct(productId, name, type, price, upc, shipping, description, manufacturer, model, image, url);
        response.log().all();
        response.statusCode(200);

    }
    @Title("Delete the product")
    @Test
    public void test004() {
        ValidatableResponse response = productSteps.deleteProduct(productId);
        response.log().all();
        response.statusCode(200);
    }


}
