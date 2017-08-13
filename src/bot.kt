import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder

class TabbyBot(token: String) {

    val builder: JDABuilder = JDABuilder(AccountType.BOT).setToken(token)

    fun start() {
        println("Starting up TabbyBot...")
        val jda = builder.buildBlocking()
        jda.addEventListener(TabbyListener())
    }

}