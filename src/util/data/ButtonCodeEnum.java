package util.data;

public enum  ButtonCodeEnum {

    LEFT(1 << 10),
    WHEEL(1 << 11),
    RIGHT(1 << 12);

    private int buttonCode;

    ButtonCodeEnum(int buttonCode) {
        this.buttonCode = buttonCode;
    }

    public int getButtonCode() {
        return buttonCode;
    }

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
