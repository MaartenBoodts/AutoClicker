package util.data;

public enum  OperationTypeEnum {

    DELAY(0),
    MOUSEMOVE(1),
    MOUSECLICK(2),
    MOUSEPRESS(3),
    MOUSERELEASE(4),
    MOUSEWHEEL(5),
    KEYCLICK(6),
    KEYPRESS(7),
    KEYRELEASE(8),
    SCREENCAPTURE(9);

    private int typeId;

    OperationTypeEnum(int typeId){
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
