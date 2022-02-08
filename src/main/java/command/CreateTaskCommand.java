package command;

import main.Storage;
import main.TaskList;
import main.TessUi;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Represent a command to create a new task.
 * @author Fan Jue
 * @version 0.2.0
 * @since 0.1.0
 */
public class CreateTaskCommand extends Command {
    /** The type of task to be created */
    protected String taskType;
    /** The full command in an array */
    protected String[] cmdArr;

    CreateTaskCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.taskType = cmdArr[0];
        this.cmdArr = cmdArr;
    }

    /**
     * Execute the command on the system to create a new task.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        Task newTask;
        String description = generateDescription(cmdArr);
        int cmdLen = cmdArr.length;
        switch (taskType) {
        case "event":
            newTask = new Event(description, cmdArr[cmdLen - 1]);
            break;
        case "deadline":
            newTask = new Deadline(description, cmdArr[cmdLen - 1]);
            break;
        default: // "todo"
            newTask = new Todo(description);
        }

        taskList.addTask(newTask);
        storage.needUpdate();
        return ui.addTaskRes(newTask.toString(), taskList.size());
    }

    /** Generate the description of the task from the command String[] */
    String generateDescription(String[] arr) {
        int cmdLen = arr.length;
        String description = "";
        for (int i = 1; i < cmdLen - 1; i++) {
            if (arr[i].equals("/at") || arr[i].equals("/by")) {
                return description.substring(0, description.length() - 1);
            }
            description += arr[i] + " ";
        }
        description += arr[cmdLen - 1];
        return description;
    }
}
