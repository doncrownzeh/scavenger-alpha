package korba.crownzeh.scavenger.screens.levelselect

import com.badlogic.gdx.Game
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.assets.level.{Level, LevelLoader}
import korba.crownzeh.scavenger.screens.button.ButtonAction.startLevel
import korba.crownzeh.scavenger.screens.menu.Action._

class LevelSelectionTable {

  private[levelselect] def createSelectionTable(game: Game, spriteBatch: SpriteBatch, theme: Music): Table = {

    val levelTable = new Table
    levelTable.center.top.padTop(15)
    val levels: IndexedSeq[Level] = LevelLoader.loadLevels()

    levels.foreach(level => {
      val i = level.id
      import ImagePath._
      val button = if (level.isUnlocked) {
        new Image(new Texture(LEVEL_BUTTON_DIR + level.levelType.name + ".png")).addOnClick(() => startLevel(game, spriteBatch, level, theme))
      }
      else {
        new Image(new Texture(LEVEL_BUTTON_DIR + level.levelType.name + "_locked.png"))
      }
      levelTable.add(button)

      if (i % 3 == 0) {
        levelTable.row.pad(10, 10, 10, 10)
      }
    })

    levelTable.setFillParent(true)
    levelTable
  }
}
