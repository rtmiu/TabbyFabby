fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No arguments given. Aborting.")
        return
    }
    val bot = TabbyBot(args[0]) // TODO: Add argument parsing (and more arguments)
    bot.start()
}