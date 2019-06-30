package korba.crownzeh.scavenger.assets.level

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import korba.crownzeh.scavenger.assets.SoundTrackManager

sealed abstract class LevelType(val name: String,
                                val path: String,
                                val theme: Music,
                                val numberOfMaps: Int,
                                val ambientColor: Color,
                                val ambientAlpha: Float)

case object City extends LevelType("city", "levels/city", SoundTrackManager.cityTheme, 3, Color.BLACK, 0.8f)

case object Desert extends LevelType("desert", "levels/desert", SoundTrackManager.desertTheme, 3, Color.BLACK, 0.6f)

case object Dungeon extends LevelType("dungeon", "levels/dungeon", SoundTrackManager.dungeonTheme, 3, Color.BLACK, 0.4f)