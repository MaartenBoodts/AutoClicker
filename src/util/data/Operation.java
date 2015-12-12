package util.data;

public class Operation {

    private OperationTypeEnum operationTypeEnum;

    private int x, y, delay;
    private int startX, startY, endX, endY;
    private int keyCode, buttons, wheelAmt;

    private boolean captureFullScreen = true;

    public Operation(OperationTypeEnum operationTypeEnum) {
        this.operationTypeEnum = operationTypeEnum;
    }

    public OperationTypeEnum getOperationTypeEnum() {
        return operationTypeEnum;
    }

    public void setOperationTypeEnum(OperationTypeEnum operationTypeEnum) {
        this.operationTypeEnum = operationTypeEnum;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {

        setCaptureFullScreen(false);

        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {

        setCaptureFullScreen(false);

        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {

        setCaptureFullScreen(false);

        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {

        setCaptureFullScreen(false);

        this.endY = endY;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getButtons() {
        return buttons;
    }

    public void setButtons(int buttons) {
        this.buttons = buttons;
    }

    public int getWheelAmt() {
        return wheelAmt;
    }

    public void setWheelAmt(int wheelAmt) {
        this.wheelAmt = wheelAmt;
    }

    public boolean isCaptureFullScreen() {
        return captureFullScreen;
    }

    public void setCaptureFullScreen(boolean captureFullScreen) {
        this.captureFullScreen = captureFullScreen;
    }

    @Override
    public String toString() {
        switch (getOperationTypeEnum()) {
            case DELAY:
                return String.format("%-25s | %d ms", getOperationTypeEnum(), getDelay());
            case MOUSEMOVE:
                return String.format("%-25s | X: %d Y: %d", getOperationTypeEnum(), getX(), getY());
            case MOUSECLICK:
                return String.format("%-25s | Button: %d", getOperationTypeEnum(), getButtons());
            case MOUSEPRESS:
                return String.format("%-25s | Button: %d", getOperationTypeEnum(), getButtons());
            case MOUSERELEASE:
                return String.format("%-25s | Button: %d", getOperationTypeEnum(), getButtons());
            case MOUSEWHEEL:
                return String.format("%-25s | Wheel Amt: %d", getOperationTypeEnum(), getWheelAmt());
            case KEYCLICK:
                return String.format("%-25s | Key: %d", getOperationTypeEnum(), getKeyCode());
            case KEYPRESS:
                return String.format("%-25s | Key: %d", getOperationTypeEnum(), getKeyCode());
            case KEYRELEASE:
                return String.format("%-25s | Key: %d", getOperationTypeEnum(), getKeyCode());
            case SCREENCAPTURE:

                if (isCaptureFullScreen()) {
                    return String.format("%-25s | Fullscreen", getOperationTypeEnum());
                } else {
                    return String.format("%-25s | startX: %d startY: %d endX: %d endY: %d", getOperationTypeEnum(), getStartX(), getStartY(), getEndX(), getEndY());
                }
            default:
                return "Error: Unknown operation!";
        }
    }
}
