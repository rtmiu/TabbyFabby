import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.Game

class TabbyBot(token: String) {

    val builder: JDABuilder = JDABuilder(AccountType.BOT).setToken(token)

    fun start() {
        println("Starting up TabbyBot...")
        val jda = builder.buildBlocking()
        jda.addEventListener(TabbyListener())
        jda.presence.game = Game.of("with other kitties | ${CMD_START_CHAR}help")
    }

}