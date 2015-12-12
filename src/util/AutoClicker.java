package util;

import gui.AutoClickerFrame;
import org.jnativehook.GlobalScreen;
import util.data.Operation;
import util.data.OperationTypeEnum;

import java.awt.*;
import java.util.ArrayList;

public class AutoClicker {

    private ArrayList<Operation> commandList;

    private Thread robotThread;
    private Robot robot;

    public AutoClicker() {

        AutoClickerFrame frame =  new AutoClickerFrame(this);
        GlobalScreen.addNativeKeyListener(frame);  //Key listener

        commandList = new ArrayList<>();
    }

    public void stopLooping() {
        Robot.stopLooping();
    }

    public void addDelay(int ms) {
        Operation operation = new Operation(OperationTypeEnum.DELAY);
        operation.setDelay(ms);

        commandList.add(operation);
    }

    public void addMouseMove(int x, int y) {
        Operation operation = new Operation(OperationTypeEnum.MOUSEMOVE);
        operation.setX(x);
        operation.setY(y);

        commandList.add(operation);
    }

    public void addMouseClick(int buttons) {
        Operation operation = new Operation(OperationTypeEnum.MOUSECLICK);
        operation.setButtons(buttons);

        commandList.add(operation);
    }

    public void addMousePress(int buttons) {
        Operation operation = new Operation(OperationTypeEnum.MOUSEPRESS);
        operation.setButtons(buttons);

        commandList.add(operation);
    }

    public void addMouseRelease(int buttons) {
        Operation operation = new Operation(OperationTypeEnum.MOUSERELEASE);
        operation.setButtons(buttons);

        commandList.add(operation);
    }

    public void addMouseWheel(int wheelAmt) {
        Operation operation = new Operation(OperationTypeEnum.MOUSEWHEEL);
        operation.setWheelAmt(wheelAmt);

        commandList.add(operation);
    }

    public void addKeyClick(int keyCode) {
        Operation operation = new Operation(OperationTypeEnum.KEYCLICK);
        operation.setKeyCode(keyCode);

        commandList.add(operation);
    }

    public void addKeyPress(int keyCode) {
        Operation operation = new Operation(OperationTypeEnum.KEYPRESS);
        operation.setKeyCode(keyCode);

        commandList.add(operation);
    }

    public void addKeyRelease(int keyCode) {
        Operation operation = new Operation(OperationTypeEnum.KEYRELEASE);
        operation.setKeyCode(keyCode);

        commandList.add(operation);
    }

    public void addScreenCapture() {
        Operation operation = new Operation(OperationTypeEnum.SCREENCAPTURE);

        commandList.add(operation);
    }

    public void addScreenCapture(int startX, int startY, int endX, int endY) {
        Operation operation = new Operation(OperationTypeEnum.SCREENCAPTURE);

        operation.setStartX(startX);
        operation.setStartY(startY);
        operation.setEndX(endX);
        operation.setEndY(endY);

        commandList.add(operation);
    }

    public OperationTypeEnum getOperationType(int index) {
        Operation operation = getOperation(index);

        if (operation != null) {
            return operation.getOperationTypeEnum();
        } else {
            return null;
        }
    }

    public void updateDelay(int index, int ms) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.DELAY);

        operation.setDelay(ms);

        commandList.remove(index);
        commandList.add(index, operation);
    }

    public void updateMouseMove(int index, int x, int y) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.MOUSEMOVE);

        operation.setX(x);
        operation.setY(y);

        commandList.remove(index);
        commandList.add(index, operation);
    }

    public void updateMouseClick(int index, int buttons) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.MOUSECLICK);

        operation.setButtons(buttons);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateMousePress(int index, int buttons) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.MOUSEPRESS);

        operation.setButtons(buttons);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateMouseRelease(int index, int buttons) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.MOUSERELEASE);

        operation.setButtons(buttons);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateMouseWheel(int index, int wheelAmt) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.MOUSEWHEEL);

        operation.setWheelAmt(wheelAmt);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateKeyClick(int index, int keyCode) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.KEYCLICK);

        operation.setKeyCode(keyCode);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateKeyPress(int index, int keyCode) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.KEYPRESS);

        operation.setKeyCode(keyCode);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateKeyRelease(int index, int keyCode) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.KEYRELEASE);

        operation.setKeyCode(keyCode);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateScreenCapture(int index) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.SCREENCAPTURE);

        operation.setCaptureFullScreen(true);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void updateScreenCapture(int index, int startX, int startY, int endX, int endY) {
        Operation operation = commandList.get(index);
        operation.setOperationTypeEnum(OperationTypeEnum.SCREENCAPTURE);

        operation.setStartX(startX);
        operation.setStartY(startY);
        operation.setEndX(endX);
        operation.setEndY(endY);

        commandList.remove(index);
        commandList.add(index, operation);

    }

    public void switchOperations(int startIndex, int destinationIndex) {
        Operation operation = getOperation(startIndex);

        commandList.add(destinationIndex, operation);
        commandList.remove(startIndex + 1);
    }

    public void start(AutoClickerFrame autoClickerFrame, int loopAmount, int autoDelay) {

        try {
            robot = new Robot(autoClickerFrame, commandList, loopAmount, autoDelay);

            robotThread = new Thread(robot);
            robotThread.start();

        } catch (AWTException e) {
            System.out.println("Error: Unable to start robot thread!");
        }
    }

    public ArrayList<Operation> getCommandList() {
        return commandList;
    }

    public void setCommandList(ArrayList<Operation> commandList) {
        this.commandList = commandList;
    }

    private Operation getOperation(int index) {
        try {
            return commandList.get(index);
        } catch (NullPointerException e) {
            System.out.println("Operation index not found!");
            return null;
        }
    }
}
