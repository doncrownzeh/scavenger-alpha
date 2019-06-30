package korba.crownzeh.scavenger.screens.levelselect

import com.badlogic.gdx.Game
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.assets.level.{Level, LevelLoader}
import korba.crownzeh.scavenger.screens.button.{ButtonAction, ButtonCreator}

class LevelSelectionTable {

  private[levelselect] def createSelectionTable(game: Game, spriteBatch: SpriteBatch, theme: Music): Table = {

    val levelTable = new Table
    levelTable.center.top.padTop(15)
    val levels: IndexedSeq[Level] = LevelLoader.loadLevels()

    for (level <- levels) {
      val i = level.id
      import ButtonCreator._, ImagePath._
      val button = if (level.isUnlocked) {
        createButton(LEVEL_BUTTON_DIR + level.name + ".png", () => ButtonAction.startLevel(game, spriteBatch, level, theme)) // TODO button action
      }
      else {
        new Image(new Texture(LEVEL_BUTTON_DIR + level.name + "_locked.png"))
      }
      levelTable.add(button)

      if (i % 3 == 0) {
        levelTable.row.pad(10, 10, 10, 10)
      }
    }
    levelTable.setFillParent(true)
    levelTable
  }
}
