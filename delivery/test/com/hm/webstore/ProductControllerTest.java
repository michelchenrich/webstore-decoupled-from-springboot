package com.hm.webstore;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hm.webstore.persistence.jpa.repository.JPAProductRepository;
import com.hm.webstore.persistence.jpa.repository.JPAStorageUnitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
    @LocalServerPort private int port;
    @Autowired private JPAProductRepository productRepository;
    @Autowired private JPAStorageUnitRepository storageUnitRepository;
    @Autowired private TestRestTemplate restTemplate;
    private JsonParser jsonParser = new JsonParser();
    
    @Before
    public void setUp() {
        productRepository.deleteAll();
        storageUnitRepository.deleteAll();
    }
    
    @Test
    public void testProducts() {
        long shelfAId = createStorageUnitAndGetId("Shelf A");
        long shelfBId = createStorageUnitAndGetId("Shelf B");
        long productId = createProductAndGetId("Banana", "From SAP's Coffee Corner");
        addProductUnit(productId, shelfAId, 10, 100.0);
        addProductUnit(productId, shelfBId, 5, 110.0);
        JsonArray array = getArray("/products");
        assertEquals(1, array.size());
        assertProductDetails(productId,
                             "Banana",
                             "From SAP's Coffee Corner",
                             15,
                             "USD 103.33",
                             "USD 1550.00");
    }
    
    private JsonObject getObject(String endpoint) {
        return get(endpoint).getAsJsonObject();
    }
    
    private void addProductUnit(long productId,
                                long storageUnitId,
                                long quantity,
                                double purchasePriceAmount) {
        post(format("/products/%d/%d", productId, storageUnitId),
             format("quantity=%d&purchasePriceAmount=%f", quantity, purchasePriceAmount));
    }
    
    private long createProductAndGetId(String name, String description) {
        JsonObject result = createProduct(name, description);
        return result.get("id").getAsLong();
    }
    
    private long createStorageUnitAndGetId(String name) {
        JsonObject result = createStorageUnit(name);
        return result.get("id").getAsLong();
    }
    
    private JsonObject createStorageUnit(String name) {
        return post("/storageUnits", format("name=%s", name)).getAsJsonObject();
    }
    
    private JsonObject createProduct(String name, String description) {
        return post("/products",
                    format("name=%s&description=%s",
                           name,
                           description)).getAsJsonObject();
    }
    
    private JsonElement post(String endpoint, String requestBody) {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + endpoint,
                                                                HttpMethod.POST,
                                                                makeHttpEntity(requestBody),
                                                                String.class);
        return jsonParser.parse(response.getBody());
        
    }
    
    private JsonArray getArray(String endpoint) {
        JsonElement result = get(endpoint);
        return result.getAsJsonArray();
    }
    
    private JsonElement get(String endpoint) {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + endpoint,
                                                                HttpMethod.GET,
                                                                makeHttpEntity(),
                                                                String.class);
        return jsonParser.parse(response.getBody());
    }
    
    private HttpEntity makeHttpEntity() {
        return new HttpEntity(makeHttpHeaders());
    }
    
    private HttpEntity<String> makeHttpEntity(String requestBody) {
        return new HttpEntity<>(requestBody, makeHttpHeaders());
    }
    
    private HttpHeaders makeHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }
    
    private void assertProductDetails(long productId,
                                      String name,
                                      String description,
                                      long numberOfUnits,
                                      String averageCostUnit, String totalCostOfStock) {
        JsonObject json = getObject(format("/products/%d", productId));
        assertEquals(name, json.get("name").getAsString());
        assertEquals(description, json.get("description").getAsString());
        assertEquals(numberOfUnits, json.get("numberOfUnits").getAsLong());
        assertEquals(averageCostUnit, json.get("averageUnitCost").getAsString());
        assertEquals(totalCostOfStock, json.get("totalCostOfStock").getAsString());
    }
}
