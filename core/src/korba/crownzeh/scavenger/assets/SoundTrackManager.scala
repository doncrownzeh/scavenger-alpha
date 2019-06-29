package korba.crownzeh.scavenger.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.{Music, Sound}

object SoundTrackManager {
  private val manager: AssetManager = loadAssets()
  val menuTheme: Music = manager.get("soundtrack/looped/menu_theme.mp3")
  val cityTheme: Music = manager.get("soundtrack/looped/city_theme.mp3")
  val desertTheme: Music = manager.get("soundtrack/looped/desert_theme.mp3")
  val deathTheme: Music = manager.get("soundtrack/looped/death_theme.mp3")
  val winningTheme: Music = manager.get("soundtrack/looped/win_theme.mp3")
  val dungeonTheme: Music = manager.get("soundtrack/looped/dungeon_theme.mp3")
  val endGameTheme: Music = manager.get("soundtrack/looped/end_theme.mp3")
  val coinSound: Sound = manager.get("soundtrack/clips/pickup_coin.wav")
  val jumpSound: Sound = manager.get("soundtrack/clips/jump.wav")
  val pickupSound: Sound = manager.get("soundtrack/clips/got_item.mp3")
  val buttonSound: Sound = manager.get("soundtrack/clips/button.wav")
  val hurtSound: Sound = manager.get("soundtrack/clips/hurt.wav")

  private def loadAssets(): AssetManager = {
    val assetManager = new AssetManager
    assetManager.load("soundtrack/looped/menu_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/city_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/desert_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/death_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/win_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/dungeon_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/looped/end_theme.mp3", classOf[Music])
    assetManager.load("soundtrack/clips/pickup_coin.wav", classOf[Sound])
    assetManager.load("soundtrack/clips/jump.wav", classOf[Sound])
    assetManager.load("soundtrack/clips/got_item.mp3", classOf[Sound])
    assetManager.load("soundtrack/clips/button.wav", classOf[Sound])
    assetManager.load("soundtrack/clips/hurt.wav", classOf[Sound])
    assetManager.finishLoading()
    assetManager
  }
}
