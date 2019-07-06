package korba.crownzeh.scavenger.assets.level

import com.badlogic.gdx.{Gdx, Preferences}

case class Level (levelType: LevelType, id: Int) {
  val prefs: Preferences = Gdx.app.getPreferences("level.data")
  val highScore: Int = prefs.getInteger(levelType.name + id + "hs", 0)

  def unlock(): Unit = {
    prefs.putBoolean(levelType.name + id, true)
    prefs.flush()
  }

  def isUnlocked: Boolean = {
    prefs.getBoolean(levelType.name + id, false)
  }

  def getLevelPath: String = {
    levelType.path + id + ".tmx"
  }
}
