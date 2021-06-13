import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lsg.graphics.CSSFactory;
import lsg.graphics.ImageFactory;
import lsg.graphics.panes.AnimationPane;
import lsg.graphics.panes.CreationPane;
import lsg.graphics.panes.TitlePane;

public class LearningSoulsGameApplication extends Application {

    private Scene scene;
    private AnchorPane root;
    private TitlePane gameTitle;
    private CreationPane creationPane;
    private AnimationPane animationPane;
    private String heroName;

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

        /*                      childrens of root                      */
        root.getChildren().addAll(gameTitle);
        root.getChildren().addAll(creationPane);
    }

    private void addListeners() {
        creationPane.getNameInput().setOnAction((event -> {
            heroName = creationPane.getNameInput().getText();
            System.out.println("Nom du héro : "+ heroName);
            if (!heroName.isEmpty()){
                root.getChildren().removeAll(creationPane);
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
        root.getChildren().addAll(this.animationPane);
        animationPane.startDemo();
    }
}
