package korba.crownzeh.scavenger.assets.level

import korba.crownzeh.scavenger.config.Properties

object LevelLoader {

  def loadLevels(): IndexedSeq[Level] = {
    var levels: Vector[Level] = Vector[Level]()
    val levelTypes: Vector[LevelType] = Vector(City, Desert, Dungeon)
    levelTypes.foreach({levels ++= loadForType(_)})
    if (Properties.devMode) {unlockAllLevels(levels)}
    levels
  }

  private def loadForType (levelType: LevelType): Vector[Level] =  {
    var levels = Vector[Level]()
    for ( i <- 1 to levelType.numberOfMaps) {
      levels = levels.:+(Level(i, levelType.name, levelType.path + i + ".tmx", levelType.theme, levelType.ambientColor, levelType.ambientAlpha))
    }
    levels
  }

  def unlockAllLevels(levels: Vector[Level]): IndexedSeq[Level] = {
    levels.foreach(_.unlock())
    levels
  }
}
