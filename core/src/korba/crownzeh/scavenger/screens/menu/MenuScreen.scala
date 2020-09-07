package korba.crownzeh.scavenger.screens.menu

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.{ImagePath, SoundTrackManager}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.screens.button.ButtonAction.{enterInfoScreen, enterLevelSelectionScreen, exitGame}
import korba.crownzeh.scavenger.screens.menu.Action._

class MenuScreen(game: Game, spriteBatch: SpriteBatch) extends Screen {

  val theme: Music = SoundTrackManager.menuTheme
  private val camera: OrthographicCamera = new OrthographicCamera
  private val viewport: FitViewport = new FitViewport(Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT, camera)
  private val background: Texture = new Texture(ImagePath.BACKGROUND)
  private val stage: Stage = new Stage(viewport, spriteBatch)
  theme.setLooping(true)
  theme.setVolume(if (Properties.devMode) 0.05f else 1f)
  theme.play()
  stage.addActor(createKorbaLogo())
  stage.addActor(createTopMenu())
  stage.addActor(createTopLogo())
  stage.addActor(createExitTable())
  stage.addActor(createPlayButtonTable())

  override def show(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0f, 0f, 0f, 0.5f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    camera.update()
    spriteBatch.setProjectionMatrix(camera.combined)
    spriteBatch.begin()
    spriteBatch.draw(background, 0, 0, Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT)
    spriteBatch.end()
    stage.draw()
  }

  override def resize(width: Int, height: Int): Unit = {
    viewport.update(width, height)
  }

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {
    theme.dispose()
    background.dispose()
    stage.dispose()
  }

  private def createKorbaLogo(): Table = {
    val table = new Table().left().bottom().pad(10f)
    table.add(new Image(new Texture(ImagePath.KORBA_LOGO)).addOnClick(() => {}))
    table.setFillParent(true)
    table
  }

  private def createTopMenu(): Table = {
    val table = new Table().left().top().pad(10f).padLeft(20f)
    table.add(new Image(new Texture(ImagePath.SETTINGS_BUTTON)).addOnClick(() => enterInfoScreen(game, spriteBatch, this)))
    table.add(new Image(new Texture(ImagePath.INFO_BUTTON)).addOnClick(() => enterInfoScreen(game, spriteBatch, this)))
    table.setFillParent(true)
    table
  }

  private def createTopLogo(): Table = {
    val table = new Table().center().top().pad(10f)
    table.add(new Image(new Texture(ImagePath.LOGO)))
    table.setFillParent(true)
    table
  }

  private def createExitTable(): Table = {
    val table = new Table().right().bottom().pad(10f)
    table.add(new Image(new Texture(ImagePath.EXIT_BUTTON)).addOnClick(() => exitGame()))
    table.setFillParent(true)
    table
  }

  private def createPlayButtonTable(): Table = {
    val table = new Table().center()
    table.add(new Image(new Texture(ImagePath.PLAY_BUTTON)).addOnClick(() => enterLevelSelectionScreen(game, spriteBatch, this, theme)))
    table.setFillParent(true)
    table
  }
}
