package korba.crownzeh.scavenger.screens.button

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.level.Level
import korba.crownzeh.scavenger.screens.GameScreen
import korba.crownzeh.scavenger.screens.info.InfoScreen
import korba.crownzeh.scavenger.screens.levelselect.LevelSelectionScreen

object ButtonAction {

  private[screens] def exitGame(): Unit = {
    Gdx.app.exit()
  }

  private[screens] def enterInfoScreen(game: Game, spriteBatch: SpriteBatch, previousScreen: Screen): Unit = {
    game.setScreen(new InfoScreen(game, spriteBatch, previousScreen))
  }

  private[screens] def enterLevelSelectionScreen(game: Game, spriteBatch: SpriteBatch, previousScreen: Screen): Unit = {
    game.setScreen(new LevelSelectionScreen(game, spriteBatch, previousScreen))
  }

  private[screens] def goBack(game: Game, previousScreen: Screen): Unit = {
    game.setScreen(previousScreen)
  }

  private[screens] def startLevel(game: Game, spriteBatch: SpriteBatch, level: Level): Unit = {
    game.setScreen(new GameScreen(game, spriteBatch, level))
  }
}
