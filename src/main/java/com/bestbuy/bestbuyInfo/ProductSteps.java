package com.bestbuy.bestbuyInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class ProductSteps {

    @Step("Create a new product")
    public ValidatableResponse createProduct(String name, String type, double price, String upc, double shipping, String description, String manufacturer, String model, String image, String url) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, upc, shipping, description, manufacturer, model, image, url);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.CREATE_PRODUCT).then();
    }

    @Step
    public HashMap<String, Object> getProductInfoById(int productId) {

        return SerenityRest.given().log().all()
                .when()
                .pathParam("productID", productId)
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

    }

    @Step("update a new product")
    public ValidatableResponse updateProduct(int productId, String name, String type, double price, String upc, double shipping, String description, String manufacturer, String model, String image, String url) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, upc, shipping, description, manufacturer, model, image, url);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productID", productId)
                .when()
                .body(productPojo)
                .patch(EndPoints.PATCH_PRODUCT_BY_ID)
                .then();
    }
    @Step("Delete product")
    public ValidatableResponse deleteProduct(int productId)  {

        return SerenityRest.given().log().all()
                .pathParam("productID",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID).then();

    }

}
