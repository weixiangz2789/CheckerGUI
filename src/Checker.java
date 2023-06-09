public class Checker {
    private String color;
    private int piecePositionX;
    private int piecePositionY;
    private boolean isPromoted;

    public Checker(String color, int piecePositionX, int piecePositionY, boolean isPromoted) {
        this.color = color;
        this.piecePositionX = piecePositionX;
        this.piecePositionY = piecePositionY;
        this.isPromoted = isPromoted;
    }

    public int getPiecePositionX() {
        return piecePositionX;
    }

    public int getPiecePositionY() {
        return piecePositionY;
    }

    public String getColor() {
        return color;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean n) {
        isPromoted = n;
    }

    public void setColor(String color) {
        this.color = color;
    }

}