package korba.crownzeh.scavenger.screens.levelselect

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.screens.Backable



class LevelSelectionScreen (game: Game, spriteBatch: SpriteBatch, previousScreen: Screen, theme: Music) extends Screen with Backable{

  private val camera = new OrthographicCamera
  private val viewport = new FitViewport(Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT, camera)
  private val background = new Texture(ImagePath.BACKGROUND)
  private val stage = new Stage(viewport, spriteBatch)
  private val selectionTable = new LevelSelectionTable
  stage.addActor(selectionTable.createSelectionTable(game, spriteBatch, theme))
  stage.addActor(createBackTable(game, previousScreen))

  override def show(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    camera.update()
    spriteBatch.setProjectionMatrix(camera.combined)
    spriteBatch.begin()
    spriteBatch.draw(background, 0, 0, Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT)
    spriteBatch.end()
    stage.draw()
  }

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {
  }

  override def dispose(): Unit = {}
}
