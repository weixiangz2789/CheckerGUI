public class Checker{
    String color;
    int piecePositionX;
    int piecePositionY;

    public Checker(String color, int piecePositionX, int piecePositionY){
        this.color = color;
        this.piecePositionX = piecePositionX;
        this.piecePositionY = piecePositionY;
    }

    public int getPiecePositionX(){
        return piecePositionX;
    }

    public int getPiecePositionY(){
        return piecePositionY;
    }

    public String getColor(){
        return color;
    }

    public void setPiecePosition(int newX, int newY){
        piecePositionX = newX;
        piecePositionY = newY;
    }

}