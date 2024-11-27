package src.edu.curso;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class EquipamentosBoundary implements Tela {

    private Label lblId = new Label("");
    private TextField txtNome = new TextField();
    private TextField txtTipo = new TextField();
    private TextField txtStatus = new TextField();
    private TextField txtModelo = new TextField();
    private TextField txtQnt = new TextField();
    private DatePicker dataAquisicao = new DatePicker();

    private EquipamentosControl control = null;

    private TableView<Equipamentos> tableView = new TableView<>();
    
    
    @Override
    public Pane render() {
        try{
            control = new EquipamentosControl();
        }catch(TechException e){
            new Alert(AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> {
            try{
                control.gravar();
            }catch(TechException err){
                new Alert(AlertType.ERROR, "Erro ao gravar o equipamento", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> {
            try{
                control.pesquisar();
            }catch(TechException err){
                new Alert(AlertType.ERROR, "Erro ao pesquisar por nome", ButtonType.OK).showAndWait();
            
            }
        });

        Button btnNovo = new Button("*");
        btnNovo.setOnAction(e -> control.limparTudo());

        //coluna, linha
        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(btnNovo, 2, 1);
        paneForm.add(new Label("Tipo: "), 0, 2);
        paneForm.add(txtTipo, 1, 2);
        paneForm.add(new Label("Status: "), 0, 3);
        paneForm.add(txtStatus, 1, 3);
        paneForm.add(new Label("Modelo: "), 0, 4);
        paneForm.add(txtModelo, 1, 4);
        paneForm.add(new Label("Quantidade: "), 0, 5);
        paneForm.add(txtQnt, 1, 5);
        paneForm.add(new Label("Data aquisicao: "), 0, 6);
        paneForm.add(dataAquisicao, 1, 6);

        paneForm.add(btnGravar, 0, 7);
        paneForm.add(btnPesquisar, 1, 7);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

        return panePrincipal;
    }

    public void gerarColunas(){
        TableColumn<Equipamentos, Integer> col1 = new TableColumn<>("IdEq");
        col1.setCellValueFactory(new PropertyValueFactory<Equipamentos, Integer>("idEq"));

        TableColumn<Equipamentos, String> col2 = new TableColumn<>("NomeEq");
        col2.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("nomeEq"));

        TableColumn<Equipamentos, String> col3 = new TableColumn<>("TipoEq");
        col3.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("tipoEq"));

        TableColumn<Equipamentos, String> col4 = new TableColumn<>("StatusEq");
        col4.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("statusEq"));

        TableColumn<Equipamentos, String> col5 = new TableColumn<>("ModeloEq");
        col5.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("modeloEq"));

        TableColumn<Equipamentos, Integer> col6 = new TableColumn<>("QuantidadeEq");
        col6.setCellValueFactory(new PropertyValueFactory<Equipamentos, Integer>("qntEq"));

        TableColumn<Equipamentos, LocalDate> col7 = new TableColumn<>("DataAquisicao");
        col7.setCellValueFactory(new PropertyValueFactory<Equipamentos, LocalDate>("dataAquisicao"));

        tableView.getSelectionModel().selectedItemProperty()
        .addListener((obs, antigo, novo) -> {
            if(novo != null){
                System.out.println("Nome: " + novo.getNomeEq());
                control.paraTela(novo);
            }
        });
        Callback<TableColumn<Equipamentos, Void>, TableCell<Equipamentos, Void>> cb = 
            new Callback<>() {

                @Override
                public TableCell<Equipamentos, Void> call(
                    TableColumn<Equipamentos, Void> param) {
                        TableCell<Equipamentos, Void> celula = new TableCell<>(){
                            final Button btnApagar  = new Button("Apagar");

                            {
                                btnApagar.setOnAction(e -> {
                                    Equipamentos equipamentos = tableView.getItems().get(getIndex());
                                    try{
                                        control.excluir(equipamentos);
                                    }catch(TechException err){
                                        new Alert(AlertType.ERROR, "Erro ao excluir o equipamento", ButtonType.OK).showAndWait();

                                    }
                                });
                            }

                            @Override
                            public void updateItem(Void item, boolean empty){
                                if(!empty){
                                    setGraphic(btnApagar);
                                }else{
                                    setGraphic(null);
                                }
                            }    

                        };
                        return celula;
                    }
            };
        TableColumn<Equipamentos, Void> col8 = new TableColumn<>("Ação");
        col8.setCellFactory(cb);

        tableView.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7,col8);
        tableView.setItems(control.getLista());
    }

    public void ligacoes(){
        control.idEqProperty().addListener((obs, antigo, novo) -> {
            lblId.setText(String.valueOf(novo));
        });

        IntegerStringConverter conv = new IntegerStringConverter();

        Bindings.bindBidirectional(control.nomeEqProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.tipoEqProperty(), txtTipo.textProperty());
        Bindings.bindBidirectional(control.statusEqProperty(), txtStatus.textProperty());
        Bindings.bindBidirectional(control.modeloEqProperty(), txtModelo.textProperty());
        Bindings.bindBidirectional(txtQnt.textProperty(), control.qntEqProperty(),(StringConverter) conv);
        Bindings.bindBidirectional(control.dataAquisicaoProperty(), dataAquisicao.valueProperty());
    }
    


    
}
