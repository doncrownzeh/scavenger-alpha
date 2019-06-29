package korba.crownzeh.scavenger.screens.info

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.screens.Backable

class InfoScreen (game: Game, spriteBatch: SpriteBatch, previousScreen: Screen) extends Screen with Backable{

  val camera = new OrthographicCamera
  val viewport = new FitViewport(Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT, camera)
  val stage = new Stage(viewport, spriteBatch)


  val resolution: String = Gdx.graphics.getWidth + "/" + Gdx.graphics.getHeight

  val deviceLabel = new Label("Device", new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val deviceVersionLabel = new Label(Properties.device.toString, new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val resolutionLabel = new Label("Resolution", new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val currentResolutionLabel = new Label(resolution, new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val versionLabel = new Label("Version", new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val currentVersionLabel = new Label("ALPHA", new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val authorLabel = new Label("Author", new Label.LabelStyle(new BitmapFont, Color.WHITE))
  val authorTextLabel = new Label("Jakub Krzetowski", new Label.LabelStyle(new BitmapFont, Color.WHITE))

  val table: InfoTable = new InfoTable()
  table.addLabelRow(deviceLabel, deviceVersionLabel)
  table.addLabelRow(resolutionLabel, currentResolutionLabel)
  table.addLabelRow(versionLabel, currentVersionLabel)
  table.addLabelRow(authorLabel, authorTextLabel)

  stage.addActor(table.getTable)
  stage.addActor(createBackTable(game, previousScreen))


  override def show(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    spriteBatch.end()
    spriteBatch.setProjectionMatrix(this.stage.getCamera.combined)
    this.stage.draw()
  }

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {}

}
