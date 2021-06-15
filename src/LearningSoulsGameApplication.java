import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lsg.characters.Hero;
import lsg.characters.Zombie;
import lsg.exceptions.StaminaEmptyException;
import lsg.exceptions.WeaponBrokenException;
import lsg.exceptions.WeaponNullException;
import lsg.graphics.CSSFactory;
import lsg.graphics.ImageFactory;
import lsg.graphics.panes.AnimationPane;
import lsg.graphics.panes.CreationPane;
import lsg.graphics.panes.HUDPane;
import lsg.graphics.panes.TitlePane;
import lsg.graphics.widgets.characters.renderers.HeroRenderer;
import lsg.graphics.widgets.characters.renderers.ZombieRenderer;
import lsg.weapons.Sword;

public class LearningSoulsGameApplication extends Application {

    private Scene scene;
    private AnchorPane root;
    private TitlePane gameTitle;
    private CreationPane creationPane;
    private AnimationPane animationPane;
    private String heroName;
    private Hero hero;
    private HeroRenderer heroRenderer;
    private Zombie zombie;
    private ZombieRenderer zombieRenderer;
    private HUDPane hudPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new AnchorPane();
        scene = new Scene(root,1200,800);
        primaryStage.setTitle("Learning Souls Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        buildUI();
        addListeners();
        primaryStage.show();
        startGame();
    }

    private void buildUI() {
        this.scene.getStylesheets().add(CSSFactory.getStyleSheet("LSG.css"));
        this.scene.getStylesheets().add(CSSFactory.getStyleSheet("LSGFont.css"));

        /*                      gameTitle                              */
        gameTitle = new TitlePane(this.scene,"Learning Souls Game");
        AnchorPane.setLeftAnchor(gameTitle,0.0);
        AnchorPane.setRightAnchor(gameTitle,0.0);
        AnchorPane.setTopAnchor(gameTitle,0.0);

        /*                      creationPane (include playerNameTitle)  */
        creationPane = new CreationPane();
        creationPane.setOpacity(0);
        AnchorPane.setLeftAnchor(creationPane,400.0);
        AnchorPane.setRightAnchor(creationPane,400.0);
        AnchorPane.setTopAnchor(creationPane,0.0);
        AnchorPane.setBottomAnchor(creationPane,0.0);

        /*                      animationPane                          */
        animationPane = new AnimationPane(root);

        /*                      HUDPane                                */
        hudPane = new HUDPane();
        AnchorPane.setLeftAnchor(hudPane,0.0);
        AnchorPane.setRightAnchor(hudPane,0.0);
        AnchorPane.setTopAnchor(hudPane,0.0);
        AnchorPane.setBottomAnchor(hudPane,0.0);

        /*                      childrens of root                      */
        root.getChildren().addAll(gameTitle,creationPane);
    }

    private void addListeners() {
        creationPane.getNameInput().setOnAction((event -> {
            heroName = creationPane.getNameInput().getText();
            System.out.println("Nom du héro : "+ heroName);
            if (!heroName.isEmpty()){
                root.getChildren().remove(creationPane);
            }
            gameTitle.zoomOut(event1 -> play());
        }));
    }

    private void startGame() {
        gameTitle.zoomIn(event -> creationPane.fadeIn(event1 -> {
            ImageFactory.preloadAll(() -> System.out.println("Pré-chargement des images terminés"));
        }));
        System.out.println("Animation lancée !");
    }

    private void play() {
        root.getChildren().addAll(this.animationPane,hudPane);
        //animationPane.startDemo();
        createHero();
        createZombie((
                event -> {
                    System.out.println("Fini");
                    try {
                        this.hero.attack();
                    } catch (WeaponNullException | StaminaEmptyException | WeaponBrokenException e) {
                        e.printStackTrace();
                    }
                    hudPane.getMessagePane().showMessage("FIGHT !");
                }
        ));
    }

    private void createHero(){
        hero = new Hero(heroName);
        Sword sword = new Sword();
        hero.setWeapon(sword);
        heroRenderer = animationPane.createHeroRenderer();
        heroRenderer.goTo(animationPane.getPrefWidth()*0.5 - heroRenderer.getFitWidth()*0.65, null);
        hudPane.getHeroStatBar().getName().setText(heroName);
        hudPane.getZombieStatBar().getAvatar().setImage(ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_HEAD)[0]);
        hudPane.getHeroStatBar().getLifeBar().progressProperty().bind(hero.lifeRateProperty());
        hudPane.getHeroStatBar().getStamBar().progressProperty().bind(hero.staminaRateProperty());
    }

    private void createZombie(EventHandler<ActionEvent> finishedHandler){
        zombie = new Zombie();
        zombieRenderer = animationPane.createZombieRenderer();
        zombieRenderer.goTo(animationPane.getPrefWidth()*0.5 - zombieRenderer.getBoundsInLocal().getWidth() * 0.15, finishedHandler);
        hudPane.getZombieStatBar().getName().setText(zombie.getName());
        hudPane.getZombieStatBar().getLifeBar().progressProperty().bind(zombie.lifeRateProperty());
        hudPane.getZombieStatBar().getStamBar().progressProperty().bind(zombie.staminaRateProperty());
    }
}
