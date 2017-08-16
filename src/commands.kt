import java.util.concurrent.TimeUnit
import java.time.ZonedDateTime

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent


val CMD_START_CHAR = '&'


fun getCommand(cmdName: String)
        : ((MessageReceivedEvent?, Array<String>?) -> Unit)? = when (cmdName.toLowerCase()) {
    "help" -> fun(event: MessageReceivedEvent?, _: Array<String>?) {
        val channel = event?.textChannel ?: return
        val me = event.jda.selfUser

        val embed = EmbedBuilder().setAuthor("${me.name}#${me.discriminator}", null, null)
        embed.setTitle("Command Help").setDescription("All commands must be prepended with '$CMD_START_CHAR'")
        embed.addField("botsnack", "Feed the bot so it won't kill you and the other humans :cookie:", false)
        embed.addField("ping", "Test if the bot is alive and get an immediate response", false)
        embed.addField("status", "Display info about the bot", false)

        embed.setColor(channel.guild.getMemberById(me.id).color).setTimestamp(ZonedDateTime.now())

        channel.sendMessage(embed.build()).queue()
    }
    "botsnack" -> fun(event: MessageReceivedEvent?, _: Array<String>?) {
        event?.textChannel?.sendMessage(":smile:")?.queue()
    }
    "ping" -> fun(event: MessageReceivedEvent?, _: Array<String>?) {
        val channel = event?.textChannel ?: return
        val sender = event.author

        channel.sendMessage("pong ${sender.asMention}").queue()
    }
    "status" -> fun(event: MessageReceivedEvent?, _: Array<String>?) {
        val channel = event?.textChannel ?: return
        val me = event.jda.selfUser

        val embed = EmbedBuilder().setAuthor("${me.name}#${me.discriminator}", null, null)
        embed.setDescription(me.jda.asBot().applicationInfo.complete().description)
        embed.setColor(channel.guild.getMemberById(me.id).color).setTimestamp(ZonedDateTime.now())
        embed.setThumbnail(me.avatarUrl).addField("Ping", "${me.jda.ping}ms", true)
        embed.addField("Status Report", "Running a-okay! :ok_hand: :triumph:", false)

        channel.sendMessage(embed.build()).queue()
    }
    else -> null
}