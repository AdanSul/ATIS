package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import il.cshaifasweng.OCSFMediatorExample.entities.UserControl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class VolunterControl {

    @FXML
    private Button PICK_TASK_BTN;

    @FXML
    private Button Task_Details_btn;

    @FXML
    private ListView<String> TasksList;

    @FXML
    private Button homepagebtn;

    public static List<Task> tasks =new ArrayList<>();
    private Task selectedTask = null;

    public void initialize() {
        EventBus.getDefault().register(this);
        if (tasks.isEmpty()) {
            Platform.runLater(() -> {
                showCompletionMessage("Empty", "There is no requests accepted to volunteer.");
                try {
                    App.setRoot("secondary");
                }catch (IOException e){
                    throw new RuntimeException(e);
                }

            });
            return;
        }

        // Add items to the ListView
        for (Task task : tasks) {
            this.TasksList.getItems().addAll(task.getServiceType());
        }

        // Set event handler for mouse click on ListView
        this.TasksList.setOnMouseClicked(event -> {
            String selectedTaskName = this.TasksList.getSelectionModel().getSelectedItem();
            if (selectedTaskName != null) {
                // Find the selected task in the tasks list
                for (Task task : tasks) {
                    if (task.getServiceType().equals(selectedTaskName)) {
                        selectedTask = task;
                        break;
                    }
                }
            }
        });
    }
    private void showCompletionMessage(String title, String message) {
        // Display an alert dialog to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Use INFORMATION type for completion message
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert(String task){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task details");
        alert.setHeaderText("Task Details: " );
        alert.setContentText(task);
        alert.showAndWait();
    }
    @Subscribe
    public void updateList(List<Task> listOfTasks){
        Platform.runLater(() -> {
            this.TasksList.getItems().removeAll();
            for(Task task: tasks){
                this.TasksList.getItems().remove(task.getServiceType());
            }
            tasks.clear();
            tasks = listOfTasks;
            for (Task task : tasks) {
                this.TasksList.getItems().addAll(task.getServiceType());
            }
            this.TasksList.setOnMouseClicked(event -> {
                String selectedTaskName = this.TasksList.getSelectionModel().getSelectedItem();
                if (selectedTaskName != null) {
                    // Find the selected task in the tasks list
                    for (Task task : tasks) {
                        if (task.getServiceType().equals(selectedTaskName)) {
                            selectedTask = task;
                            break;
                        }
                    }
                }
            });
        });
    }

    @FXML
    void PICK_TASK(ActionEvent event)throws IOException {
        if(selectedTask != null) {
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Task Volunteering");
            alert.setHeaderText(selectedTask.getServiceType());
            if (selectedTask.getStatus() == 0 && (!selectedTask.getUser().getID().equals(SecondaryController.getUserLogIn().getID()))) {
                alert.setContentText(selectedTask.getServiceType() + " was picked");
                SimpleClient.getClient().sendToServer("myModify " + selectedTask.getIdNum());
                alert.showAndWait();
            } else {
                Platform.runLater(() -> {
                    showCompletionMessage("Error", "You cannot volunteer to the task you asked!");
                });
            }
        }else{
            Platform.runLater(() -> {
                showCompletionMessage("Empty", "There is no tasks selected, please select one.");
            });
        }
    }

    @FXML
    void TASKDETAILS(ActionEvent event) throws IOException {
        if(selectedTask != null){
            long id= selectedTask.getIdNum();
            String serviceType= selectedTask.getServiceType();
            String fitst=selectedTask.getUser().getFirstName();
            String userid=selectedTask.getUser().getID();
            int status=selectedTask.getStatus();
            String x = String.format("Task ID: %d\nTask Description: %s\nUser Name: %s\nUser ID: %s\nState: %d", id, serviceType, fitst, userid, status);
            showAlert(x);
        }
        else{
            Platform.runLater(() -> {
                showCompletionMessage("Empty", "There is no tasks selected, please select one.");
            });
        }
    }

    @FXML
    void homepage(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

}