package util;

import gui.AutoClickerFrame;
import util.data.Operation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Robot extends java.awt.Robot implements Runnable {

    private int loopAmount;
    private static boolean stopLooping = false;

    private AutoClickerFrame autoClickerFrame;
    private ArrayList<Operation> actionsList;

    public Robot(AutoClickerFrame autoClickerFrame, ArrayList<Operation> actionsList, int loopAmount, int autoDelay) throws AWTException {
        super();

        this.setAutoDelay(autoDelay);

        this.autoClickerFrame = autoClickerFrame;
        this.actionsList = actionsList;
        this.loopAmount = loopAmount;
    }

    public void createScreenCapture(int startX, int startY, int endX, int endY) {
        saveImage(super.createScreenCapture(new Rectangle(startX, startY, (endX - startX), (endY - startY))));
    }

    public void createScreenCapture() {
        saveImage(super.createScreenCapture(getFullScreenSize()));
    }

    private boolean saveImage(BufferedImage image) {

        Calendar calendar = Calendar.getInstance();

        File file = new File(calendar.get(Calendar.DAY_OF_MONTH)
                + "-" + calendar.get(Calendar.MONTH)
                + "-" + calendar.get(Calendar.YEAR)
                + "_" + calendar.get(Calendar.HOUR_OF_DAY)
                + "-" + calendar.get(Calendar.MINUTE)
                + "-" + calendar.get(Calendar.SECOND)
                + ".png");

        try {
            ImageIO.write(image, "png", file);
            return true;

        } catch (IOException e) {

            return false;
        }
    }

    private Rectangle getFullScreenSize() {

        int width = 0;
        int height = 0;

        System.out.println();
        for (GraphicsDevice graphicsDevice : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            for (GraphicsConfiguration graphicsConfiguration : graphicsDevice.getConfigurations()) {

                int x = graphicsConfiguration.getBounds().width;
                int y = graphicsConfiguration.getBounds().height;

                if (width != x) {
                    width = +x;
                }

                if (height != y) {
                    height = +y;
                }
            }
        }

        return new Rectangle(0, 0, width, height);
    }

    @Override
    public void run() {
        stopLooping = false;

        for (int i = 0; i < loopAmount && !stopLooping; i++) {
            for (int y = 0; y < actionsList.size() && !stopLooping; y++) {
                autoClickerFrame.selectListAction(y);
                executeOperation(actionsList.get(y));
            }
        }
    }

    private void executeOperation(Operation operation) {
        switch (operation.getOperationTypeEnum()) {
            case DELAY:
                delay(operation.getDelay());
                break;
            case MOUSEMOVE:
                mouseMove(operation.getX(), operation.getY());
                break;
            case MOUSECLICK:

                mousePress(operation.getButtons());
                mouseRelease(operation.getButtons());
                break;
            case MOUSEPRESS:
                mousePress(operation.getButtons());
                break;
            case MOUSERELEASE:
                mouseRelease(operation.getButtons());
                break;
            case MOUSEWHEEL:
                mouseWheel(operation.getWheelAmt());
                break;
            case KEYCLICK:
                keyPress(operation.getKeyCode());
                keyRelease(operation.getKeyCode());
                break;
            case KEYPRESS:
                keyPress(operation.getKeyCode());
                break;
            case KEYRELEASE:
                keyRelease(operation.getKeyCode());
                break;
            case SCREENCAPTURE:

                if (operation.isCaptureFullScreen()) {
                    createScreenCapture();
                } else {
                    createScreenCapture(operation.getStartX(), operation.getStartY(), operation.getEndX(), operation.getEndY());
                }
                break;
            default:
                System.out.println("Error: Unknown operation!");
                break;
        }
    }

    public static void stopLooping() {
        stopLooping = true;
    }
}
