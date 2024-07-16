package com.bestbuy.crudtest;

import com.bestbuy.bestbuyInfo.StoreSteps;
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
public class StoreCURDTest extends TestBase {

    static String name = "John" + TestUtils.getRandomValue();
    static String type = "sendBox" + TestUtils.getRandomValue();
    static String address = "12345 16Th Street" + TestUtils.getRandomValue();
    static String address2 = "Bixby" + TestUtils.getRandomValue();
    static String city = "Tulsa";
    static String state = "Oklahoma";
    static String zip = "742000";

    static int storeId;


    @Steps
    StoreSteps storeSteps;

    @Title("Create a store id ")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createStoreID(name, type, address, address2, city, state, zip);

        response.log().all();
        response.statusCode(201);
        storeId = response.extract().path("id");

    }
    @Title("Read the product")
    @Test
    public void test002() {
        HashMap<String, Object> storeReading = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeReading, hasValue(name));


    }
    @Title("Update the product")
    @Test
    public void test003() {
        ValidatableResponse response = storeSteps.updateTheStoreId(storeId, name, type, address, address2, city, state, zip);
        response.log().all();
        response.statusCode(200);

    }
    @Title("Delete the product")
    @Test
    public void test004() {
        ValidatableResponse response=storeSteps.deleteStoreId(storeId);
        response.log().all();
        response.statusCode(200);

    }


}
