import org.springframework.web.client.RestTemplate
import org.springframework.http.client.SimpleClientHttpRequestFactory

fun main() {
    val factory = SimpleClientHttpRequestFactory()
    factory.setConnectTimeout(10000)
    factory.setReadTimeout(10000)
    val rt = RestTemplate(factory)
}
