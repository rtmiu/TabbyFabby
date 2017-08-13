import java.util.concurrent.TimeUnit

import net.dv8tion.jda.core.events.message.MessageReceivedEvent


val CMD_START_CHAR = '&'

fun getCommand(cmdName: String)
        : ((MessageReceivedEvent?, Array<String>?) -> Unit)? = when (cmdName) {
    "ping" -> fun(event: MessageReceivedEvent?, _: Array<String>?) {
        val channel = event?.textChannel ?: return
        val sender = event.author

        val action = channel.sendMessage("pong ${sender.asMention}")
        action.queue()
    }
    else -> null
}