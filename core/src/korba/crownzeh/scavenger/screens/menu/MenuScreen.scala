package korba.crownzeh.scavenger.screens.menu

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.ImagePath.PLAY_BUTTON
import korba.crownzeh.scavenger.assets.ImagePath.EXIT_BUTTON
import korba.crownzeh.scavenger.assets.ImagePath.SETTINGS_BUTTON
import korba.crownzeh.scavenger.assets.{ImagePath, SoundTrackManager}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.screens.button.ButtonDefinition
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
    val table = new Table()
    table.add(new Image(new Texture(ImagePath.KORBA_LOGO)).addOnClick(() => {}))
    table.setFillParent(true)
    table.left().bottom().pad(10f)
    table
  }

  private def createTopMenu(): Table = {
    val table = createTableWithButtons().left().top().pad(10f).padLeft(20f)
    val settings = ButtonDefinition[Unit](new Texture(SETTINGS_BUTTON),() => {})
    table.add(new Image(new Texture(ImagePath.INFO_BUTTON)).addOnClick(enterInfoScreen(game, spriteBatch, this)))
    table.setFillParent(true)
    table.left().top().pad(10f).padLeft(20f)
    table
  }

  private def createTopLogo(): Table = {
    val table = new Table()
    table.add(new Image(new Texture(ImagePath.LOGO)))
    table.setFillParent(true)
    table.center().top().pad(10f)
    table
  }

  private def createExitTable(): Table = {
    val exitButton = ButtonDefinition[Unit](new Texture(EXIT_BUTTON), exitGame)
    createTableWithButtons(exitButton).right().bottom().pad(10f)
  }

  private def createPlayButtonTable(): Table = {
    val playButton = ButtonDefinition[Unit](new Texture(PLAY_BUTTON), () => enterLevelSelectionScreen(game, spriteBatch, this, theme))
    createTableWithButtons(playButton).center()
  }

  private def createTableWithButtons(buttons: ButtonDefinition[Unit]*): Table = {
    val table = new Table()
    buttons.map(toImage).foreach(button => {
      table.add(button)
    })
    table.setFillParent(true)
    table
  }

  private def toImage(button: ButtonDefinition[Unit]): Image = {
    new Image(button.texture).addOnClick(button.function)
  }

}
