import io.activej.http.AsyncServlet
import io.activej.http.HttpMethod
import io.activej.http.HttpResponse
import io.activej.http.RoutingServlet
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher

class HttpServer: HttpServerLauncher() {
    @Provides
    fun servlet(): AsyncServlet {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/") {
                HttpResponse.ok200()
                    .withJson("{ status: \"Ok\" }")
            }
    }
}

fun main(args: Array<String>) {
    val httpServer = HttpServer()
    httpServer.launch(args)
}