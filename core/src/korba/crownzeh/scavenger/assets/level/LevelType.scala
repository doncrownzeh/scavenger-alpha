package korba.crownzeh.scavenger.assets.level

import com.badlogic.gdx.audio.Music
import korba.crownzeh.scavenger.assets.SoundTrackManager

sealed abstract class LevelType(val name: String,
                                val path: String,
                                val theme: Music,
                                val numberOfMaps: Int)

case object City extends LevelType("city", "levels/city", SoundTrackManager.cityTheme, 3)

case object Desert extends LevelType("desert", "levels/desert", SoundTrackManager.desertTheme, 3)

case object Dungeon extends LevelType("dungeon", "levels/dungeon", SoundTrackManager.dungeonTheme, 3)