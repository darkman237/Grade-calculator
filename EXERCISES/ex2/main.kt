interface Logger {
    fun log(message: String)
}

class ConsoleLogger : Logger {
    override fun log(message: String) = println(message)
}

class FileLogger : Logger {
    override fun log(message: String) = println("File: $message")
}

class Application(logger: Logger) : Logger by logger {
    fun run() {
        log("App started")
        log("Processing data...")
        log("App finished")
    }
}

fun main() {
    println("=== Console Logger ===")
    Application(ConsoleLogger()).run()

    println("\n=== File Logger ===")
    Application(FileLogger()).run()
}