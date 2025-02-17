import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ContatoBoundary extends Application {

    private Label lblId = new Label("");
    private TextField txtNome = new TextField("");
    private TextField txtTelefone = new TextField("");
    private TextField txtEmail = new TextField("");
    private DatePicker dateNascimento = new DatePicker(LocalDate.now());

    private TableView<Contato> tableView = new TableView<>();

    private ContatoControl control = new ContatoControl();

    @Override
    public void start(Stage stage) {
        BorderPane panePrincipal = new BorderPane();

        GridPane paneForm = new GridPane();
        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(new Label("Telefone: "), 0, 2);
        paneForm.add(txtEmail, 1, 2);
        paneForm.add(new Label("Email: "), 0, 3);
        paneForm.add(txtTelefone, 1, 3);
        paneForm.add(new Label("Nascimento: "), 0, 4);
        paneForm.add(dateNascimento, 1, 4);

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction(e -> {
            control.gravar();
        });

        Button btnPesq = new Button("Pesquisar");
        btnPesq.setOnAction(e -> {
            control.pesquisarPorNome();
        });
        paneForm.add(btnGravar, 0, 5);
        paneForm.add(btnPesq, 1, 5);

        generateColumns();
        vincularPropriedades();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

        Scene scn = new Scene(panePrincipal, 800, 600);
        stage.setTitle("Agenda de contato");
        stage.setScene(scn);
        stage.show();

    }

    public void generateColumns() {
        TableColumn<Contato, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Contato, Integer>("id"));

        TableColumn<Contato, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<Contato, String>("nome"));

        TableColumn<Contato, String> col3 = new TableColumn<>("Email");
        col3.setCellValueFactory(new PropertyValueFactory<Contato, String>("email"));

        TableColumn<Contato, String> col4 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<Contato, String>("telefone"));

        TableColumn<Contato, LocalDate> col5 = new TableColumn<>("Nascimento");
        col5.setCellValueFactory(new PropertyValueFactory<Contato, LocalDate>("nascimento"));

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);

        tableView.setItems(control.getLista());
    }

    public void vincularPropriedades() {
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(dateNascimento.valueProperty(), control.nascimentoProperty());
    }

    public static void main(String[] args) {
        launch(ContatoBoundary.class, args);
    }
}