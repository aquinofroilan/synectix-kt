import java.io.File

val file = File("src/main/kotlin/com/aquinofroilan/tessera/config/RabbitMqConfig.kt")
var content = file.readText()

// Add constants
val constants = """        const val DEAD_LETTER_EXCHANGE = "notification.dlx"
        const val DEAD_LETTER_QUEUE = "notification.dlq"

        const val DOMAIN_EVENT_EXCHANGE = "domain.event.exchange"
        const val DOMAIN_EVENT_WEBHOOK_QUEUE = "domain.event.webhook.queue"
        const val DOMAIN_EVENT_WEBHOOK_ROUTING_KEY = "domain.event.webhook""""

content =
    content.replace(
        "        const val DEAD_LETTER_EXCHANGE = \"notification.dlx\"\n        const val DEAD_LETTER_QUEUE = \"notification.dlq\"",
        constants,
    )

// Add beans
val beans = """    @Bean
    fun webhookBinding(
        webhookQueue: Queue,
        notificationExchange: DirectExchange,
    ): Binding = BindingBuilder.bind(webhookQueue).to(notificationExchange).with(WEBHOOK_ROUTING_KEY)

    @Bean
    fun domainEventExchange(): org.springframework.amqp.core.TopicExchange = org.springframework.amqp.core.TopicExchange(DOMAIN_EVENT_EXCHANGE)

    @Bean
    fun domainEventWebhookQueue(): Queue =
        QueueBuilder
            .durable(DOMAIN_EVENT_WEBHOOK_QUEUE)
            .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
            .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE)
            .build()

    @Bean
    fun domainEventWebhookBinding(
        domainEventWebhookQueue: Queue,
        notificationExchange: DirectExchange,
    ): Binding = BindingBuilder.bind(domainEventWebhookQueue).to(notificationExchange).with(DOMAIN_EVENT_WEBHOOK_ROUTING_KEY)"""

content =
    content.replace(
        "    @Bean\n    fun webhookBinding(\n        webhookQueue: Queue,\n        notificationExchange: DirectExchange,\n    ): Binding = BindingBuilder.bind(webhookQueue).to(notificationExchange).with(WEBHOOK_ROUTING_KEY)",
        beans,
    )

file.writeText(content)
