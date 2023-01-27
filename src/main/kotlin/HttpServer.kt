import com.google.gson.Gson
import io.activej.http.*
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher
import models.Contact
import models.Presentation
import java.nio.charset.StandardCharsets

class HttpServer: HttpServerLauncher() {
    private val gson = Gson()
    private val contacts = arrayListOf<Contact>()

    @Provides
    fun servlet(): AsyncServlet {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/") {
                HttpResponse.ok200()
                    .withJson(gson.toJson(Presentation()))
            }

            .map(HttpMethod.GET, "/getAllContacts") {
                HttpResponse.ok200()
                    .withJson(gson.toJson(contacts))
            }

            .map(HttpMethod.POST, "/addContact") {
                it.loadBody().map { _ ->
                    val body = it.body.asString(StandardCharsets.UTF_8)
                    val newContact = gson.fromJson(body, Contact::class.java)
                    contacts.add(newContact)

                    HttpResponse.ok200()
                        .withJson("{ status: \"Done\" }")
                }
            }
    }
}

fun main(args: Array<String>) {
    val httpServer = HttpServer()
    httpServer.launch(args)
}