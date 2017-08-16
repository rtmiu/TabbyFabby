import net.dv8tion.jda.core.hooks.ListenerAdapter
import net.dv8tion.jda.core.events.message.MessageReceivedEvent


val ARGSPLIT = Regex("\\s+")

class TabbyListener : ListenerAdapter() {

    fun <T> List<T>.toLast(startIndex: Int)
            : List<T>? = if (startIndex < this.size) this.subList(startIndex, this.size - 1) else null

    inline fun <reified T> List<T>.toLastAsArray(startIndex: Int): Array<T>? = this.toLast(startIndex)?.toTypedArray()


    fun handleCommand(cmdName: String, args: Array<String>? = null)
            : ((MessageReceivedEvent?) -> Unit)? {

        val cmd = getCommand(cmdName) ?: return null

        return fun(event: MessageReceivedEvent?) {
            cmd(event, args)
        }

    }

    override fun onMessageReceived(event: MessageReceivedEvent?) {

        val message = event?.message?.content ?: return

        val cmd = if (message[0] == CMD_START_CHAR) {
            val args = message.substring(1).split(ARGSPLIT)

            handleCommand(args[0], args.toLastAsArray(1))
        } else return

        try {
            cmd?.invoke(event) ?: return
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}