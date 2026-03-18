package BT3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoteControl {
    private List<Command> commands = new ArrayList<>();
    private Stack<Command> histories = new Stack<>();

    public void addCommand(int slot, Command command){
        commands.add(slot - 1,command);
    }
    public void pressButton(int slot){
        Command command = commands.get(slot - 1);
        if (command != null){
            command.execute();
            histories.push(command);
        }else{
            System.err.println("Bấm sai nút");
        }
    }
    public void pressBack(){
        if (!histories.isEmpty()){
            Command old = histories.pop();
            old.undo();
        }else {
            System.err.println("Không có thao tác nào trở lại");
        }
    }
}
