package korba.crownzeh.scavenger.screens.menu


import com.badlogic.gdx.Game
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.screens.button.{ButtonAction, ButtonCreator}


class MenuTable(game: Game, spriteBatch: SpriteBatch, menuScreen: MenuScreen, theme: Music) {

  private[menu] def createMenuTable(): Table = {
    val logo = new Image(new Texture(ImagePath.LOGO))
    val table = new Table
    table.center.top.padTop(10)
    logo.setSize(logo.getWidth * 2, logo.getHeight * 2)
    table.add(logo).size(logo.getWidth, logo.getHeight)
    import ButtonCreator._, ButtonAction._, ImagePath._
    addRowWithButton(table, createButton(PLAY_BUTTON, () => enterLevelSelectionScreen(game, spriteBatch, menuScreen, theme)))
    addRowWithButton(table, createButton(INFO_BUTTON, () => enterInfoScreen(game, spriteBatch, menuScreen)))
    addRowWithButton(table, createButton(EXIT_BUTTON, () => exitGame()))
    table.setFillParent(true)
    table
  }

  private def addRowWithButton(table: Table, button: Image): Unit = {
    table.row.pad(10, 10, 10, 10)
    table.add(button).size(button.getWidth, button.getHeight)
  }
}
