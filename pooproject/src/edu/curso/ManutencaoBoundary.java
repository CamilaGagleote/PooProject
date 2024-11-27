package src.edu.curso;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

public class ManutencaoBoundary implements Tela {

    private Label lblId = new Label("");
    private TextField txtTipo = new TextField();
    private TextField txtStatus = new TextField();
    private TextField txtEq = new TextField();
    private TextField txtCusto = new TextField();
    private DatePicker dataManutencao = new DatePicker();

    private ManutencaoControl control = null;

    private TableView<Manutencao> tableView = new TableView<>();


    @Override
    public Pane render() {
        try{
            control = new ManutencaoControl();
        }catch(TechException e){
            new Alert(AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> { 
            try { 
                control.gravar();
            }catch (TechException err) { 
                new Alert(AlertType.ERROR, "Erro ao gravar a manutencao", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> { 
            try { 
                control.pesquisar();
            } catch (TechException err) { 
                new Alert(AlertType.ERROR, "Erro ao pesquisar por tipo", ButtonType.OK).showAndWait();
            }});

        Button btnNovo = new Button("*");
        btnNovo.setOnAction( e -> control.limparTudo() );
            
        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);
        paneForm.add(new Label("Tipo: "), 0, 1);
        paneForm.add(txtTipo, 1, 1);
        paneForm.add(btnNovo, 2, 1);
        paneForm.add(new Label("Status: "), 0, 2);
        paneForm.add(txtStatus, 1, 2);
        paneForm.add(new Label("Equipamento: "), 0, 3);
        paneForm.add(txtEq, 1, 3);
        paneForm.add(new Label("Custo: "), 0, 4);
        paneForm.add(txtCusto, 1, 4);
        paneForm.add(new Label("Manutencao: "), 0, 5);
        paneForm.add(dataManutencao, 1, 5);
       
        paneForm.add(btnGravar, 0, 6);
        paneForm.add(btnPesquisar, 1, 6);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter(tableView);

        return panePrincipal;

    }
     public void gerarColunas() { 
        TableColumn<Manutencao, Integer> col1 = new TableColumn<>("IdMan");
        col1.setCellValueFactory( new PropertyValueFactory<Manutencao, Integer>("idMan") );

        TableColumn<Manutencao, String> col2 = new TableColumn<>("TipoMan");
        col2.setCellValueFactory( new PropertyValueFactory<Manutencao, String>("tipoMan"));

        TableColumn<Manutencao, String> col3 = new TableColumn<>("StatusMan");
        col3.setCellValueFactory( new PropertyValueFactory<Manutencao, String>("statusMan"));

        TableColumn<Manutencao, String> col4 = new TableColumn<>("EqMan");
        col4.setCellValueFactory( new PropertyValueFactory<Manutencao, String>("eqMan"));

        TableColumn<Manutencao, Float> col5 = new TableColumn<>("CustoMan");
        col5.setCellValueFactory( new PropertyValueFactory<Manutencao, Float>("custoMan"));

        TableColumn<Manutencao, LocalDate> col6 = new TableColumn<>("DataManutencao");
        col6.setCellValueFactory( new PropertyValueFactory<Manutencao, LocalDate>("dataManutencao"));

        tableView.getSelectionModel().selectedItemProperty()
            .addListener( (obs, antigo, novo) -> {
                if (novo != null) { 
                    System.out.println("Tipo: " + novo.getTipoMan());
                    control.paraTela(novo);
                }
            });
        Callback<TableColumn<Manutencao, Void>, TableCell<Manutencao, Void>> cb = 
            new Callback<>() {
                @Override
                public TableCell<Manutencao, Void> call(
                    TableColumn<Manutencao, Void> param) {
                    TableCell<Manutencao, Void> celula = new TableCell<>() { 
                        final Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction( e -> {
                                Manutencao manutencao = tableView.getItems().get( getIndex() );
                                try { 
                                    control.excluir( manutencao ); 
                                } catch (TechException err) { 
                                    new Alert(AlertType.ERROR, "Erro ao excluir o registro da manutencao", ButtonType.OK).showAndWait();
                                }
                            });
                        }

                        @Override
                        public void updateItem( Void item, boolean empty) {                             
                            if (!empty) { 
                                setGraphic(btnApagar);
                            } else { 
                                setGraphic(null);
                            }
                        }
                        
                    };
                    return celula;            
                } 
            };

        TableColumn<Manutencao, Void> col7 = new TableColumn<>("Ação");
        col7.setCellFactory(cb);

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
        tableView.setItems( control.getLista() );
    }

    public void ligacoes() { 
        control.idManProperty().addListener( (obs, antigo, novo) -> {
            lblId.setText( String.valueOf(novo) );
        });

        FloatStringConverter fc = new FloatStringConverter();
        Bindings.bindBidirectional(control.tipoManProperty(), txtTipo.textProperty());
        Bindings.bindBidirectional(control.statusManProperty(), txtStatus.textProperty());
        Bindings.bindBidirectional(control.eqManProperty(), txtEq.textProperty());
        Bindings.bindBidirectional( txtCusto.textProperty(), control.custoManProperty(), (StringConverter) fc);
        Bindings.bindBidirectional(control.dataManutencaProperty(), 
                dataManutencao.valueProperty());
    }
    
}
