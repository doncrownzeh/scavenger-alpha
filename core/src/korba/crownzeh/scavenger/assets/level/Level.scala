package korba.crownzeh.scavenger.assets.level

import com.badlogic.gdx.{Gdx, Preferences}
import com.badlogic.gdx.audio.Music

case class Level (id: Int, name: String, path: String, theme: Music) {
  val prefs: Preferences = Gdx.app.getPreferences("level.data")
  val highScore: Int = prefs.getInteger(name + id + "hs", 0)

  def unlock(): Unit = {
    prefs.putBoolean(name + id, true)
    prefs.flush()
  }

  def isUnlocked: Boolean = {
    prefs.getBoolean(name + id, false)
  }
}
