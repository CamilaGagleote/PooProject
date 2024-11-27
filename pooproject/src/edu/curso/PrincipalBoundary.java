package src.edu.curso;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {
   private Map<String, Tela> telas = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        telas.put("equipamento", new EquipamentosBoundary() );
        telas.put("manutencao", new ManutencaoBoundary() );
        BorderPane panePrincipal = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu mnuCadastro = new Menu("Cadastro");
        MenuItem mnuItemEquipamento = new MenuItem("Equipamento");
        mnuItemEquipamento.setOnAction ( e -> 
            panePrincipal.setCenter( telas.get("equipamento").render() )
        );
        MenuItem mnuItemManutencao = new MenuItem("Manutencao");
        mnuItemManutencao.setOnAction( e-> 
            panePrincipal.setCenter( telas.get("manutencao").render() )
        );
        mnuCadastro.getItems().addAll( mnuItemEquipamento );
        mnuCadastro.getItems().addAll( mnuItemManutencao );
        menuBar.getMenus().addAll( mnuCadastro);
        panePrincipal.setTop( menuBar );
        Scene scn = new Scene(panePrincipal, 800, 600);
        stage.setScene(scn);
        stage.setTitle("Manutencao de Equipamentos");
        stage.show();        
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
    
}