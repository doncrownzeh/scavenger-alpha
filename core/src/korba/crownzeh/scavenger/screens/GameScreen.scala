package korba.crownzeh.scavenger.screens

import box2dLight.RayHandler
import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureAtlas}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Box2DDebugRenderer, World}
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.{Game, Gdx, Screen}
import korba.crownzeh.scavenger.assets.level.Level
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.gameplay.control.{KeyboardProcessor, MobileOverlay}
import korba.crownzeh.scavenger.gameplay.player.{PlayerAction, PlayerAspect}
import korba.crownzeh.scavenger.gameplay.scene.InGameHud
import korba.crownzeh.scavenger.gameplay.world.{Box2dWorldLoader, WorldContactListener}


class GameScreen(game: Game, spriteBatch: SpriteBatch, level: Level) extends Screen {

  private val atlas = new TextureAtlas("characters/sprite_sheet.txt")
  private val gameCamera = new OrthographicCamera

  import Properties._

  private val viewport = new FitViewport(VIRTUAL_WIDTH / PIXELS_PER_METER, VIRTUAL_HEIGHT / PIXELS_PER_METER, gameCamera)
  private val mapLoader = new TmxMapLoader
  private val map = mapLoader.load(level.getLevelPath)
  private val mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PIXELS_PER_METER)
  gameCamera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0)
  private val world = new World(new Vector2(0, -10), true)
  private val b2ddr = new Box2DDebugRenderer
  private val player = new PlayerAspect(world)
  private val playerAction = new PlayerAction(player)
  private val hud = new InGameHud(game, spriteBatch, player)
  // val controlOverlay = MobileOverlay(playerAction) TODO
  private val rayHandler = setLights(world)
  val b2world = new Box2dWorldLoader(game, map, world, this, rayHandler, level)
  b2world.load()
  hud.update() // TODO check if it's needed here
  world.setContactListener(new WorldContactListener(player))
  setPhysicsIndicators()
  prepareTheme(level)

  override def show(): Unit = {
    Gdx.input.setInputProcessor(Properties.device match {
      case ApplicationType.Desktop => KeyboardProcessor(playerAction)
      case ApplicationType.Android => MobileOverlay(playerAction).getStage
    })
  }

  override def render(delta: Float): Unit = {
    world.step(1 / 60f, 6, 2)
    update(delta)
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    mapRenderer.render()
    b2ddr.render(world, gameCamera.combined)
    spriteBatch.setProjectionMatrix(gameCamera.combined)
    spriteBatch.begin()
    // player.draw(spriteBatch) TODO
    b2world.enemies.foreach(_.sprite.draw(spriteBatch))
    spriteBatch.end()
    // spriteBatch.setProjectionMatrix(hud.stage.getCamera.combined) TODO
    // hud.stage.draw() TODO
    // controlOverlay.draw() TODO
    rayHandler.updateAndRender()
    rayHandler.setCombinedMatrix(gameCamera)
  }

  def update(delta: Float): Unit = {
    player.update(delta)
    b2world.enemies.foreach(_.update(delta))
    //gameCamera.position.x = player.body.getPosition.x TODO
    //gameCamera.position.y = player.body.getPosition.y + 3 TODO
    gameCamera.position.x = 300 / PIXELS_PER_METER
    gameCamera.position.y = 200 / PIXELS_PER_METER
    hud.update()
    gameCamera.update()
    mapRenderer.setView(gameCamera)
    // controlOverlay.update(delta) TODO
    // Gdx.input.setInputProcessor(controlOverlay.getStage) TODO
  }

  override def resize(width: Int, height: Int): Unit = {
    viewport.update(width, height)
    // controlOverlay.resize(width, height) TODO
  }

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def hide(): Unit = {}

  override def dispose(): Unit = {
    map.dispose()
    mapRenderer.dispose()
    world.dispose()
    b2ddr.dispose()
    hud.dispose()
    // controlOverlay.dispose() TODO
  }

  private def setPhysicsIndicators(): Unit = {
    b2ddr.setDrawAABBs(false)
    b2ddr.setDrawContacts(false)
    b2ddr.setDrawInactiveBodies(false)
    b2ddr.setDrawJoints(false)
    b2ddr.setDrawVelocities(devMode)
    b2ddr.setDrawBodies(devMode)
  }

  private def setLights(world: World): RayHandler = {
    val rayHandler = new RayHandler(world)
    val ambientColor = level.levelType.ambientColor
    ambientColor.a = level.levelType.ambientAlpha
    rayHandler.setAmbientLight(ambientColor)
    rayHandler
  }

  private def prepareTheme (level: Level): Unit = {
    val theme = level.levelType.theme
    theme.setLooping(true)
    theme.setVolume(if (Properties.devMode) 0.05f else 1f)
    theme.play()
  }

}
