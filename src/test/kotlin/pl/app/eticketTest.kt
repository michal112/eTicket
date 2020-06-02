package pl.app

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class eticketTest {

    @Test
    fun testHelloEndpoint() {
//        given()
//          .`when`().get("/ticket")
//          .then()
//             .statusCode(200)
//             .body(`is`("hello"))
    }

}