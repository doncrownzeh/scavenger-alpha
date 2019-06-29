package korba.crownzeh.scavenger.screens.menu

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.{ImagePath, SoundTrackManager}
import korba.crownzeh.scavenger.config.Properties

class MenuScreen (game: Game, spriteBatch: SpriteBatch) extends Screen{

  private val theme: Music = SoundTrackManager.menuTheme
  private val camera: OrthographicCamera = new OrthographicCamera
  private val viewport: FitViewport = new FitViewport(Properties.VIRTUAL_WIDTH, Properties.VIRTUAL_HEIGHT, camera)
  private val background: Texture = new Texture(ImagePath.BACKGROUND)
  private val stage: Stage = new Stage(viewport, spriteBatch)
  private val table = new MenuTable(game, spriteBatch, this).createMenuTable()
  theme.setLooping(true)
  theme.setVolume(if (Properties.devMode) 0.05f else 1f)
  theme.play()
  stage.addActor(table)

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
}
